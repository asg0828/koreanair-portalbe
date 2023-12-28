package com.cdp.portal.common.cleansing;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CleansingRule {

    /**
     * Cleansing Rule
     * 1. Text 는 모두 삭제하고 숫자만 남김
     * 2. 8210 또는 82010 으로 시작하는 번호는 한국지역으로 간주하여 010 으로 통일
     * 2. 10으로 시작하는 9~10자리 전화번호는 010으로 변경
     * 4. 아래 [오류유형]은 Null로 변환
     * [오류유형]
     * 1)한국 - 010 으로 시작되는 번호는 10자리, 11자리 이외 자리수는 모두 오류
     * 한국 - 10자리, 11자리일 경우 가운데 세자리/네자리가 연속된 0 일 경우 오류
     * 2)한국 -  그외번호로 시작되는 경우는 오류
     * 3)해외는 6자리 이하일 경우 오류 처리, max 자리수 지정하지 않음
     **/
    public String cleanseMobile(String inputMobileNo) {
        if (StringUtils.isBlank(inputMobileNo)) return null;

        String result = inputMobileNo;

        //1. 숫자만 남김
        result = result.replaceAll("[^0-9]", "");

        //2. 모든 번호가 0인 번호 null 반환
        if (Pattern.compile("^[0]*$").matcher(result).find()) {
            return null;
        }

        //3. 8210, 82010 로 시작하는 번호는 010 변환
        result = result.replaceAll("^8210|^82010", "010");

        //4. [오류유형]은 null 반환
        if (result.length() <= 6) {
            return null;
        }

        //5. 10으로 시작하는 번호 (Skypass 데이터에서 10으로 들어오는 경우가 있어서 추가)
        if (Pattern.compile("^10[0-9]{7,8}$").matcher(result).find()) {
            result = result.replaceAll("^10", "010");
        }

        //6. 010 으로 시작하는 번호 (= 한국번호)
        if (Pattern.compile("^010").matcher(result).find()) {
            if (!Pattern.compile("^010[0-9]{7,8}$").matcher(result).find()) {
                return null;
            }
            if (Pattern.compile("^010[0]{3}[0-9]{4}$|^010[0]{4}[0-9]{4}$").matcher(result).find()) {
                return null;
            }
        }

        return result;
    }

    /**
     * cleanseMobile 메서드 사용으로 변경
     * Cleansing Rule
     * Skypass Cell Phone Cleansing
     **/
    @Deprecated
    public String cleanseSkypassMobile(String inputMobileNo) {
        if (StringUtils.isBlank(inputMobileNo)) return null;

        String result = inputMobileNo;

        //1. 숫자만 남김
        result = result.replaceAll("[^0-9]", "");

        //2. 8210, 82010 로 시작하는 번호는 010 변환
        result = result.replaceAll("^8210|^82010", "010");

        //3. [오류유형]은 null 반환
        if (result.length() <= 6) {
            return null;
        }

        //4. 10으로 시작하는 번호
        if (Pattern.compile("^10[0-9]{7,8}$").matcher(result).find()) {
            result = result.replaceAll("^10", "010");
        }

        return result;
    }

    /**
     * cleanseMobile 메서드 사용으로 변경
     * Cleansing Rule
     * 집 전화번호
     * 문자 삭제, 숫자만 남김
     */
    @Deprecated
    public String cleansePhone(String inputPhoneNo) {
        if (StringUtils.isBlank(inputPhoneNo)) return null;

        String result = inputPhoneNo;

        //1. 숫자만 남김
        result = result.replaceAll("[^0-9]", "");

        return result;
    }

    /**
     * Cleansing Rule
     * 스카이패스 cleansing rule 적용함. 단 ALM 에 적용되는 추가/변경룰 확인 (박효의 kj)
     * 1. '//' -> '@' 변경
     * 2. '@asdf.com/sdfd' 에서 '/'이후 글자 삭제
     * 3. 문자와 숫자만 허용
     * 4. 길이 6 미만이면 Null 처리
     * 5. @xx.com , @xxx.com 은 Null 처리
     */
    public String cleanseEmail(String inputEmail) {
        if (StringUtils.isBlank(inputEmail)) return null;

        String result = inputEmail.toUpperCase();

        // 1. "//" -> "@" 변경
        result = result.replace("//", "@");

        // 2. 마지막 "/KR", "/" 이후 적용 안함
        if (result.indexOf("/") != -1) result = result.substring(0, result.indexOf("/"));

        // todo 아래 주석 확인 필요
        // 3. 문자와 숫자만 허용 -> 항목 내용은 문자와 숫자만이라고 하지만, 정규식은 특수문자도 포함 가능하게 되어있음
        String regex = "^([0-9a-zA-Z_\\.\\-?,;:|*~`!#$%^&+]+)@[0-9a-zA-Z가-힣]([-.]?[0-9a-zA-Z가-힣])*\\.[a-zA-Z]{2,4}$";
        if (!Pattern.compile(regex).matcher(result).find()) {
            return null;
        }

        // 4. 길이 6 미만이면 null (1에서 사용한 정규식을 사용한다면, 아래 조건은 체크할 필요가 없음)
        // "^([0-9a-zA-Z_\\.\\-?,;:|*~`!#$%^&+]+)@[0-9a-zA-Z가-힣]([-.]?[0-9a-zA-Z가-힣])*\\.[a-zA-Z]{2,4}$"
        if (result.length() < 6) {
            return null;
        }

        // 5. @xx.com , @xxx.com 은 Null 처리
        if (Pattern.compile("@[xX][xX].[cC][oO][mM]|@[xX][xX][xX].[cC][oO][mM]").matcher(result).find()) {
            return null;
        }

        return result;
    }

    /**
     * Cleansing Rule
     * 1. First Name 컬럼 맨 마지막의 데이터 클린징 Rule 적용 대상 title 삭제 (시작할 때도 추가)
     * 삭제 대상 title : MR, MS, MRS, MSTR, MISS, MRMR, MSMS, MSTRMSTR, MISSMISS
     * 시작 할때는 "MR "공백 하나 포함된 것만 삭제한다.
     * 2. 이름 사이, 양 끝의 space 는 없애고 저장  (sang beom --> sangbeom 으로 변경)
     */
    public String cleanseName(String inputName) {
        if (StringUtils.isBlank(inputName)) return null;

        String result = inputName.toUpperCase();
        result = result.replaceAll("^MR |^MS |^MRS |^MSTR |^MISS |^MRMR |^MSMS |^MSTRMSTR |^MISSMISS ", "");
        result = result.replaceAll("MR$|MS$|MRS$|MSTR$|MISS$|MRMR$|MSMS$|MSTRMSTR$|MISSMISS$| ", "");

        return result;
    }

    /**
     * 한글 이름 Rule : 한글만 남김
     */
    public String cleanseKoreanName(String inputName) {
        if (StringUtils.isBlank(inputName)) return null;

        String result = cleanseName(inputName);

        result = result.replaceAll("[^가-힣]", "");

        if (StringUtils.isBlank(result)) {
            return null;
        }
        return result;
    }

    /**
     * Cleansing Rule
     * convertSkypassBirthDate 메서드 바로 호출로 변경 (as-is: 8자리만 인정, to-be: '-' 허용)
     */
    public String cleanseDate(String inputBirthDate) {
        return convertSkypassBirthDate(inputBirthDate);
    }

    /**
     * Skypass date cleansing
     */
    public String convertSkypassBirthDate(String inputBirthDate) {
        if (StringUtils.isBlank(inputBirthDate)) return null;

        String result = null;

        if (Pattern.compile("^[0-9]{4}-[0-9]{2}-[0-9]{2}").matcher(inputBirthDate).find()) {
            inputBirthDate = inputBirthDate.substring(0, 10);
            result = inputBirthDate.replace("-", "");
        } else if (Pattern.compile("^[0-9]{8}").matcher(inputBirthDate).find()) {
            result = inputBirthDate.substring(0, 8);
        }

        return result;
    }

    /**
     * 영문 생년월일 변환
     * ddMMMyy -> yyyyMMdd 변환 조건
     * 1) yy가 올해와 같거나 이후인 경우 : 19yyMMdd
     * 2) yy가 올해보다 이전인 경우 : 20yyMMdd
     */
    public String convertOdsBirthDate(String inputDate) {
        if (StringUtils.isBlank(inputDate)) return null;
        try {
            // 1. 문자열의 중간 영문 월을 첫번째는 대문자, 나머지는 소문자로 변환
            char[] chars = inputDate.toCharArray();
            chars[2] = Character.toUpperCase(chars[2]);
            chars[3] = Character.toLowerCase(chars[3]);
            chars[4] = Character.toLowerCase(chars[4]);
            inputDate = new String(chars);

            // 2. yy 정보가 올해랑 같거나 큰 경우 19yy로 변환
            LocalDate inputLocalDate = LocalDate.parse(inputDate, DateTimeFormatter.ofPattern("ddMMMyy", Locale.US));
            LocalDate thisYear = LocalDate.of(LocalDate.now().getYear(), 1, 1);
            if (!inputLocalDate.isBefore(thisYear)) {
                inputLocalDate = inputLocalDate.plusYears(-100);
            }

            // 3. yyyyMMdd 포맷으로 변환
            String result = inputLocalDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"));

            return result;
        } catch (Exception e) {
            log.debug("String format is incorrect : {}", e.getMessage());
            return null;
        }
    }

    /**
     * 영문 여권 만료일자 변환
     * ddMMMyy -> 20yyMMdd 변환
     * 19Jan23 -> 20230119 변환
     */
    public String convertPassportExprDate(String inputDate) {
        if (StringUtils.isBlank(inputDate)) return null;
        try {
            // 1. 문자열의 중간 영문 월을 첫번째는 대문자, 나머지는 소문자로 변환
            char[] chars = inputDate.toCharArray();
            chars[2] = Character.toUpperCase(chars[2]);
            chars[3] = Character.toLowerCase(chars[3]);
            chars[4] = Character.toLowerCase(chars[4]);
            inputDate = new String(chars);

            // 2. yy 정보가 올해랑 같거나 큰 경우 19yy로 변환
            LocalDate inputLocalDate = LocalDate.parse(inputDate, DateTimeFormatter.ofPattern("ddMMMyy", Locale.US));

            // 3. yyyyMMdd 포맷으로 변환
            String result = inputLocalDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"));

            return result;
        } catch (Exception e) {
            log.debug("String format is incorrect : {}", e.getMessage());
            return null;
        }
    }
}
