package com.bdp.ap.common.tableauapi.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

/**
 * 서로 별로 관련 없는 유용한 메써드들의 모음.
 */
public class Utils {
	
	
	/**
	 * Session이 꼬이는 문제때문에 기본값 초기화를 위해 만듬.
	 * 
	 * enum 의 열거된 내용은 COMMON.xml 에 있는 id="retrieveMemberInfo" 의 쿼리의 컬럼 이름이다. 
	 * 
	 * 반영사유 : 상상타운 시스템에서는 Mybatis를 이용하여 값을 가져올때 모두 HashMap으로 가져온다. 이때 문제는 값이 null이면 key가 생성되지 않는다.
	 * 	Mybatis 설정에서 <setting name="callSettersOnNulls" value="true"/> 를 하게되면 값이 null인 컬럼도 HashMap에 key값으로
	 * 생성이되지만 모든 시스템에 영향이 간다. 값이 null일때 컬럼이름이 필요 없을 수도 있다. 
	 * 그래서 session이 꼬이고 있는 부분에만 처리한다.
	 * @author gosam
	 *
	 */
	static public enum defaultSessionMemberInfo{
		MEM_ID,
		MEM_NM,
		ORG_ID,
		ORG_NM,
		FULL_WORK_ORG_NM,
		ST_ORG_ID,
		HQ_ORG_ID,
		GP_ORG_ID,
		TM_ORG_ID,
		TM_ORG_NM,
		POSI_CD,
		POSI_NM,
		DUTY_CD,
		DUTY_NM,
		GENDER,
		MEM_PHOTO,
		EMAIL_ADDR,
		OFFICE_PHONE_NUM,
		JOIN_DAY,
		JOB_DUTY_CD,
		JOB_DUTY_NM,
		LVL_ID,
		LVL_NEW_ID,
		ADM_YN,
		ADM_KIND_CD,
		ADM_APPOINT_DT,
		FULL_TREE_ORG_ID,
		FULL_WORK_ORG_ID,
		USE_YN,
		RGST_DT,
		RGST_MEM_ID,
		MEM_LVL_STD_ID,
		MEM_LVL_NM,
		MEM_LVL,
		APPELLATION,
		APPELLATION_NM,
		JOB,
		JOB_NM,
		CHAR_MALE_FILE_NM,
		CHAR_FEMALE_FILE_NM,
		CHAR_MALE_FILE_REAL_NM,
		CHAR_MALE_SQUA_FILE_REAL_NM,
		CHAR_MALE_FILE_PATH,
		CHAR_FEMALE_FILE_REAL_NM,
		CHAR_FEMALE_SQUA_FILE_REAL_NM,
		CHAR_FEMALE_FILE_PATH,
		CLUB_ID,
		CLUB_NM,
		STAT,
		LVL_UP_WELCOME_YN,
		MONTH_HONOR_WELCOME_YN,
		TOTAL_MILE,
		GRP_ID,
		CURR_GRP_ID,
		CURR_GRP_NM,
		BUTTON_RWMD
	}
	
	public static boolean checkParam(String value) 	{
		String [] filter_word = new String[] {
				"|"
			,	"&"
			,	";"
			,	"$"
			,	"%"
			,	"@"
			,	"'"
			,	"\\"
			,	"<"
			,	">"
			,	"("
			,	")"
			,	"+"
			,	"\n"
			,	","
		} ;

		return checkParam(value, filter_word) ;
	}
	
	public static boolean checkParam(String value, String[] filter) {
		if ( value == null || "".equals(value)) {
			return true ;
		}
		
		boolean check = true ;
		
		for(int i=0 ; i<filter.length ; i++) {
			if(value.indexOf(filter[i]) >= 0) {
				check = false ;
			}
		} 
		return check ;
	}
	
	
	
	public static String fixNull(String str) {
		if (str == null) {
			return "" ;
		}else{
			return str.trim() ;
		}
	}
	
	
	/**
	 * 특정 파일을 읽어서 바이트 배열로 돌려준다.
	 *
	 * @param path 읽을 파일의 완전한 경로
	 * @param errMsg 에러 응답에 사용될 메시지
	 * @return 파일 내용이 담긴 바이트 배열
	 * @throws ErrorResponse 지정된 파일이 없거나, 파일을 읽는 중 오류
	 */
	public static byte[] getFileBytes(String path, String errMsg) {

		File f = new File(path);
		if (!f.exists()){
			return null;
		}

			
		int len;
		len= (int)f.length();
		byte[] buf = new byte[len];
		int read = 0;

		FileInputStream fis = null;

		try {
			fis = new FileInputStream(f);

			while (read < len) {
				int ret = fis.read(buf, read, len - read);
				if (ret == -1)
					break;
				read += ret;
			}

			return buf;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fis != null)
				try { fis.close(); } catch (IOException e) {};
		}
		return null;
	}


	/**
	 * 폰 번호를 정규화한다.
	 * 정규화된 폰 번호는 12자리(01X 00740 1234의 형태)이다.
	 */
	public static String normalizePhoneNumber(String number) {
		String strResult = "";
		
		if(number == null)
			return null;

		/*
		 * 폰 번호 정규화 유형
		 *
		 *       789 1234  (7) --> 019 00789 1234
		 *      6789 1234  (8) --> 019 06789 1234
		 * 01X   789 1234 (10) --> 01X 00789 1234
		 * 01X  6789 1234 (11) --> 01X 06789 1234
		 * 01X 56789 1234 (12) --> 01X 56789 1234
		 */

		try {
			if (!number.startsWith ("0"))
				number = "0" + number;

			switch (number.length()) {
				case 7:
					if (number.charAt(0) != '0')
						strResult = "01900" + number;
					break;

				case 8:
					if (number.charAt(0) != '0')
						strResult = "0190" + number;
					break;

				case 10:
					if (number.charAt(0) == '0' && number.charAt(1) == '1')
						strResult = number.substring(0, 3) + "00" + number.substring(3);
					break;

				case 11:
					if (number.charAt(0) == '0' && number.charAt(1) == '1')
						strResult = number.substring(0, 3) + "0" + number.substring(3);
					break;

				case 12:
					if (number.charAt(0) == '0' && number.charAt(1) == '1')
						strResult = number;
					break;
			}
		}catch(Exception e){
			e.printStackTrace();

			strResult = number;
		}

		return strResult;
	}


	/**
	 * 정규화된 폰 번호를 출력용으로 간결하게 한다.
	 *
	 * 예. (in) 019007402184 - (out) 0197402384
	 */
	public static String simplifyPhoneNumber(String number) {

		switch (number.length()) {
			case 11:
				if (number.charAt(0) == '0' && number.charAt(3) == '0')
					return number.substring(0, 3) + number.substring(4);
				break;

			case 12:
				if (number.charAt(0) == '0' && number.charAt(3) == '0') {
					if (number.charAt(4) == '0')
						return number.substring(0, 3) + number.substring(5);
					else
						return number.substring(0, 3) + number.substring(4);
				}
				break;
		}

		return number;
	}

	/**
	 * 주민등록 번호 앞자리와 뒷자리를 받아 주민번호 를 검증한다.
	 * 현재는 자리수만 체크한다. 나머지는 Altas에서 체크함.
	 * @param String jumin1
	 * @param String jumin2
	 * @return boolean
	 */
	public static boolean validateJumin(String jumin1, String jumin2) {
		
		if(jumin1==null || jumin2==null) return false;
		if(jumin1.length() != 6 || jumin2.length() != 7) return false;
		return true;
		
	}

	/*
	 * String 값을 NULL 체크와 ""체크
	 * 
	 */
	public static boolean StringCk(String check1, String check2){
		if(check2==null){
			if(check1 == null || check1.trim().equals("")){
				return true;
			}
		}else{
			if(check1.trim().equals(check2)){
				return true;
			}
		}
		return false;
	}
	
	// 주민등록번호를 기준으로 성인여부를 확인한다.ㅣ
	public static boolean isAdult(String juminNo) {
		boolean re = false;
		try {
			Calendar currTime = Calendar.getInstance();
			currTime.set(Calendar.YEAR, currTime.get(Calendar.YEAR) - 19);
			
			Calendar tempTime = Calendar.getInstance();
			tempTime.set(1900 + Integer.parseInt(juminNo.substring(0, 2)),
			Integer.parseInt(juminNo.substring(2, 4)) - 1,
			Integer.parseInt(juminNo.substring(4, 6)));
			re = currTime.after(tempTime);
		} catch (Exception nfe) {
		}
		return re;
	}
	
	public static boolean isEmpty(Object obj){
		boolean isEmpty = false;
		if( obj == null ){
			isEmpty = true;			
		}else{
			java.lang.String a = "";
			String clsName = obj.getClass().getName();
			if( clsName.equals("java.lang.String") && "".equals(obj) ){
				isEmpty = true;				
			}
		}
		
		return isEmpty;		
	}
	
	/**
	 * 파일확장자를 비교하여 유효한지 확인한다.
	 * filename = 파일확장자
	 * checkname = 유효성 체크할 파일확장자를 (구분자 [|])로 넣어주어야 한다.
	 * @param boolean
	 * @return
	 */
	public static boolean isFileVilidate(String filename, String checkname){
		boolean isFile = false;
		
		String[] checks = checkname.split("\\|");
		
		for(int i=0; i<checks.length; i++){
			if(filename.equals(checks[i])){
				isFile = true;
				break;
			}
		}
		return isFile;
	}
	
	private void fileUpload(HashMap map, MultipartFile uploadFile) throws IOException {
		String originFileName = uploadFile.getOriginalFilename().trim();	// 사용자가 올리는 원본 파일명
		
		String ext = "";
		String upFileName = "";
		String finalFnm = "";
		
		// 업로드 파일이 존재할시
		if(originFileName != ""){
			ResourceBundle file_Path = ResourceBundle.getBundle(map.get("file_upload").toString());
		//		ext = originFileName.substring(originFileName.lastIndexOf("."));	// 파일 확장자
		//		String posExt = file_Path.getString("upFileExt");	// 업로드 가능한 확장명들
		//		StringTokenizer st = new StringTokenizer(posExt,"|");
		//		String upExt = "";
		//		while (st.hasMoreElements()) {
		//			upExt = st.nextElement().toString();
		//			if(upExt.equals(ext));
		//				return 3;
		//		}
			
			upFileName = Long.toString(System.currentTimeMillis()) + ext;	// 새로운 파일명 + 파일 확장자
			String filePath = file_Path.getString("file");	//"D:\\upload";
			
			// 확장자 체크
			// 파일을 지정한 위치에 upload
			File f = new File(filePath);
		//	if(f.length() > 1024)
		//		return -1;
			
			if(!f.exists()) {
				f.mkdirs();         // 디렉토리 생성
			}
			//filePath + / + upFileName
			finalFnm = filePath  + File.separator + upFileName;
			
			map.put("ORIGIN_FILE", originFileName);
			map.put("UP_FILE", upFileName);
			uploadFile.transferTo(new File(finalFnm));
		}
	}
	
	/**
	 * 랜덤 키 생성(난수발생:정수형)
	 * value : len = 숫자 범위
	 * return String
	 */
	public static String randomInt(int len){
		String rnd = "";
		
		Random ran = new Random();
		
		if(len > 0){
			int i = ran.nextInt(len);
			rnd = i+"";
		}
		
		int rndLen = rnd.length();
		if(rndLen < 5){
			rndLen = 5 - rndLen;
			for(int j=0; j < rndLen; j++){
				rnd += rnd+"0";
			}
		}
		
		return rnd;
	}
	
	/**
	 * 코드리스트로 셀렉트박스 소스 생성
	 * @param list DB에서 조회한 코드리스트
	 * @return 셀렉트박스 소스 - 뷰에 전달해서 그대로 출력
	 */
	public static String getCodeSelectBox(List <HashMap> list) {
		String codeSelectBoxHTML = "";
		for(int i = 0; i < list.size(); i++) {
			codeSelectBoxHTML += "<option value=\"" + list.get(i).get("CD_ID").toString() + "\">" + list.get(i).get("CD_NM") + "</option>";
		}
		return codeSelectBoxHTML;
	}
	
	/**
	 * 조직리스트로 셀렉트박스 소스 생성
	 * @param list
	 * @return
	 */
	public static String getOrgSelectBox(List <HashMap> list, int k, String org_id) {
		String orgSelectBoxHTML = "";
		String selectBoxKind = "";
		String selectedFlg = "";
		
		if(k == 1) {
			selectBoxKind = "부문";
		} else if(k == 2) {
			selectBoxKind = "본부";
		} else if(k == 3) {
			selectBoxKind = "그룹";
		} else if(k == 4) {
			selectBoxKind = "팀";
		}
		
		orgSelectBoxHTML += "<option value=\"\">"+selectBoxKind+"전체</option>";
		for(int i = 0; i < list.size(); i++) {
			if(!"".equals(org_id)) {
				if(org_id.equals(list.get(i).get("ORG_ID").toString())) {
					selectedFlg = "selected=\"selected\"";
				}
			}
			orgSelectBoxHTML += "<option value=\"" + list.get(i).get("ORG_ID").toString() + "\" " + selectedFlg + ">" + list.get(i).get("ORG_NM") + "</option>";
			selectedFlg = "";
		}
		return orgSelectBoxHTML;
	}
	
	public static String getClubSelectBox(List <HashMap> list, String club_id) {
		String orgSelectBoxHTML = "";
		String selectBoxKind = "";
		String selectedFlg = "";
		
		selectBoxKind = "분임조";
		
		orgSelectBoxHTML += "<option value=\"\">"+selectBoxKind+"전체</option>";
		for(int i = 0; i < list.size(); i++) {
			if(!"".equals(club_id)) {
				if(club_id.equals(list.get(i).get("CLUB_ID").toString())) {
					selectedFlg = "selected=\"selected\"";
				}
			}
			orgSelectBoxHTML += "<option value=\"" + list.get(i).get("CLUB_ID").toString() + "\" " + selectedFlg + ">" + list.get(i).get("CLUB_NM") + "</option>";
			selectedFlg = "";
		}
		return orgSelectBoxHTML;
	}
	
	/**
	 * 년도 셀렉트박스 소스 생성
	 * @param status true(현재 년도 선택) / false(기본)
	 * @return 현재 년도 계산후 해당 년도 선택 상태
	 */
	public static String getYearSelectBox(boolean status) {
        Calendar now = Calendar.getInstance();
        int nowYear = now.get(Calendar.YEAR);
        
		String yearSelectBoxHTML = "";
		String selected = "";
		for(int i = 2014; i < 2025; i++) {
			if(status) {
				if(nowYear == i) {
					selected = "selected=\"selected\"";
				}
			}
			yearSelectBoxHTML += "<option value=\"" + i + "\" " + selected + ">" + i + "</option>";
			selected = "";
		}
		return yearSelectBoxHTML;
	}
	
	/**
	 * 년도 셀렉트박스 소스 생성
	 * @param status true(현재 년도 선택) / false(기본)
	 * @return 현재 년도 계산후 해당 년도 선택 상태
	 */
	public static String getYearSelectBox(boolean status, String selectedYear) {
		Calendar now = Calendar.getInstance();
		int nowYear = now.get(Calendar.YEAR);
		
		String yearSelectBoxHTML = "";
		String selected = "";
		for(int i = 2014; i < 2025; i++) {
			if(selectedYear != null && !selectedYear.equals("")){
				if(i == Integer.parseInt(selectedYear)){
					selected = "selected=\"selected\"";
				}
			}else{
				
				if(status) {
					if(nowYear == i) {
						selected = "selected=\"selected\"";
					}
				}
			}
			yearSelectBoxHTML += "<option value=\"" + i + "\" " + selected + ">" + i + "</option>";
			selected = "";
		}
		return yearSelectBoxHTML;
	}
	
	public static String getYearSelectBox(String year){
		String yearSelectBoxHTML = "";
		String selected = "";
		for(int i = 2014; i < 2025; i++) {
			if(Integer.parseInt(year) == i) {
				selected = "selected=\"selected\"";
			}
			yearSelectBoxHTML += "<option value=\"" + i + "\" " + selected + ">" + i + "</option>";
			selected = "";
		}
		return yearSelectBoxHTML;
	}
	
	/**
	 * 월 셀렉트박스 소스 생성
	 * @param status true(현재 월 선택) / false(기본)
	 * @return 현재 월 계산후 해당 년도 선택 상태
	 */
	public static String getMonthSelectBox(boolean status, String selectedMonth) {
		Calendar now = Calendar.getInstance();
        int nowMonth = now.get(Calendar.MONTH) + 1;
        
		String monthSelectBoxHTML = "";
		String selected = "";
		for(int i = 1; i < 13; i++) {
			if(selectedMonth != null && !selectedMonth.equals("")){
				if(i == Integer.parseInt(selectedMonth)){
					selected = "selected=\"selected\"";
				}
			}else{
				if(status) {
					if(nowMonth == i) {
						selected = "selected=\"selected\"";
					}
				}
			}
			
			if(i < 10) {
				monthSelectBoxHTML += "<option value=\"" + "0" + i + "\" " + selected + ">" + "0" + i + "</option>";
			} else {
				monthSelectBoxHTML += "<option value=\"" + i + "\" " + selected + ">" + i + "</option>";
			}
			selected = "";
		}
		return monthSelectBoxHTML;
	}
	
	/**
	 * 월 셀렉트박스 소스 생성
	 * @param status true(현재 월 선택) / false(기본)
	 * @return 현재 월 계산후 해당 년도 선택 상태
	 */
	public static String getMonthSelectBox(boolean status) {
		Calendar now = Calendar.getInstance();
        int nowMonth = now.get(Calendar.MONTH) + 1;
        
		String monthSelectBoxHTML = "";
		String selected = "";
		for(int i = 1; i < 13; i++) {
			if(status) {
				if(nowMonth == i) {
					selected = "selected=\"selected\"";
				}
			}
			if(i < 10) {
				monthSelectBoxHTML += "<option value=\"" + "0" + i + "\" " + selected + ">" + "0" + i + "</option>";
			} else {
				monthSelectBoxHTML += "<option value=\"" + i + "\" " + selected + ">" + i + "</option>";
			}
			selected = "";
		}
		return monthSelectBoxHTML;
	}

	/**
	 * 분기 셀렉트박스 소스 생성
	 * @param status true(현재 월에 해당하는 분기 선택) / false(기본)
	 * @return 현재 월 계산후 해당 분기 선택 상태
	 */
	public static String getQuarterSelectBox(boolean status, String quarter) {
		Calendar now = Calendar.getInstance();
		int nowMonth = now.get(Calendar.MONTH) + 1;
		int quarterArray[] ={1,2,3,4};
		
		String monthSelectBoxHTML = "";
		String selected = "";
		for(int i = 1; i <= quarterArray.length; i++) {
			if(quarter != null && !quarter.equals("")){
				if(i ==  Integer.parseInt(quarter)){
					selected = "selected=\"selected\"";
				}
			}else{
				if(status) {
					if(nowMonth >= (quarterArray[i-1] * 3) -2 && nowMonth <= (quarterArray[i-1] * 3)) {
						selected = "selected=\"selected\"";
					}
				}
			}
			if(i < 10) {
				monthSelectBoxHTML += "<option value=\"" + "0" + i + "\" " + selected + ">" + "0" + i + "</option>";
			} else {
				monthSelectBoxHTML += "<option value=\"" + i + "\" " + selected + ">" + i + "</option>";
			}
			selected = "";
		}
		return monthSelectBoxHTML;
	}
	
	public static String getMonthSelectBox(String month) {
		String monthSelectBoxHTML = "";
		String selected = "";
		for(int i = 1; i < 13; i++) {
			if(Integer.parseInt(month) == i) {
				selected = "selected=\"selected\"";
			}
			if(i < 10) {
				monthSelectBoxHTML += "<option value=\"" + "0" + i + "\" " + selected + ">" + "0" + i + "</option>";
			} else {
				monthSelectBoxHTML += "<option value=\"" + i + "\" " + selected + ">" + i + "</option>";
			}
			selected = "";
		}
		return monthSelectBoxHTML;
	}
	
	public static String getDaySelectBox(int year, int month, int day) {
		String daySelectBoxHTML = "";
		String selected = "";
		
		int maxDay = 0;
		if(month == 4 || month == 6 || month == 9 || month == 11) {
			maxDay = 30;
		} else if(month == 2) {
			if((year % 4) == 0) {
				maxDay = 29;
			} else {
				maxDay = 28;
			}
		} else {
			maxDay = 31;
		}
		
		for(int i = 1; i < (maxDay + 1); i++) {
			if(day != 0) {
				if(day == i) {
					selected = "selected=\"selected\"";
				}
			}
			if(i < 10) {
				daySelectBoxHTML += "<option value=\"" + "0" + i + "\" " + selected + ">" + "0" + i + "</option>";
			} else {
				daySelectBoxHTML += "<option value=\"" + i + "\" " + selected + ">" + i + "</option>";
			}
			selected = "";
		}
		return daySelectBoxHTML;
	}
	
    /** 
     * <p>입력된 년월의 마지막 일수를 return 한다.
     * 
     * @param year
     * @param month
     * @return 마지막 일수 
     * @see java.util.Calendar
     * <p><pre>
     *  - 사용 예
     * int date = DateUtil.getLastDayOfMon(2014 , 4)
     * </pre>
     */
    public static int getLastDayOfMon(int year, int month) { 
        Calendar cal = Calendar.getInstance();
        cal.set(year, month, 1);
        return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
    }
	
	/**
	 * 트리데이터 리스트로 데이터 테이블 소스 생성
	 * @param list 트리데이터
	 * @return 데이터 테이블 소스
	 */
	public static String getTreeNodeHTML(List<HashMap> list) {
		String tableHTML = "";
		for(int i = 0; i < list.size(); i++) {
			String orgNM = list.get(i).get("ORG_NM").toString();
			String orgId = list.get(i).get("ORG_ID").toString();
			String upTreeOrgId = list.get(i).get("UP_TREE_ORG_ID").toString();
			int lvl = Integer.parseInt(list.get(i).get("TREE_LVL").toString());
			int nLvl = 0;
			
			tableHTML += "<tr>";
			tableHTML += "	<td class=\"ac\"><input type=\"checkbox\" id=\"sector"+(i+1)+"\" name=\"myCheckBox\" value=\"Y\"/>";
			tableHTML += "	<input type=\"hidden\" name=\"org_id\" value=\""+orgId+"\" /></td>";
			tableHTML += "	<td><label for=\"sector"+(i+1)+"\">"+orgNM+"</label></td>";
			tableHTML += "</tr>";
		}
		return tableHTML;
	}
	
	public static String getTreeNodeHTML(List<HashMap> list, List<HashMap> orgIdList) {
		String tableHTML = "";
		
		for(int i = 0; i < list.size(); i++) {
			String orgNM = list.get(i).get("ORG_NM").toString();
			String orgId = list.get(i).get("ORG_ID").toString();
			String fullWorkOrgNm = list.get(i).get("FULL_WORK_ORG_NM").toString();
//			String upTreeOrgId = list.get(i).get("UP_WORK_ORG_ID").toString();
//			int lvl = Integer.parseInt(list.get(i).get("WORK_LVL").toString());
//			int nLvl = 0;
			
			String checkedFlg = "";
			if(orgIdList != null && orgIdList.size() > 0) {
				for(int j = 0; j < orgIdList.size(); j++) {
					if(orgId.equals(StringUtil.nvl(orgIdList.get(j).get("ORG_ID")).toString())) {
						checkedFlg = "checked=\"checked\"";
					}
				}
			}
			
			tableHTML += "<tr>";
			tableHTML += "	<td class=\"ac\"><input type=\"checkbox\" id=\""+(i+1)+"\" name=\"myCheckBox\" value=\""+orgId+"\" "+checkedFlg+"/>";
			tableHTML += "	<input type=\"hidden\" name=\"org_id\" id=\"org_id"+(i+1)+"\" value=\""+orgId+"\" /></td>";
			tableHTML += "	<td><label for=\"sector"+(i+1)+"\">"+fullWorkOrgNm+"</label></td>";
			tableHTML += "</tr>";
		}
		return tableHTML;
	}
	
	/**
	 * 심사자 목록 - 심사자 설정된 사원 리스트
	 * @param list 사원리스트 데이터
	 * @param orgNm 선택한 조직명
	 * @return 테이블에 들어갈 <tr>...</tr> 소스
	 */
	public static String getMemberListHTML(List<HashMap> list, String orgNm) {
		String memberListHTML = "";
		for(int i = 0; i < list.size(); i++) {
			String memOrnNm = list.get(i).get("ORG_NM").toString();
			String nm = list.get(i).get("EM_MEM_NM").toString();
			String memId = list.get(i).get("EM_MEM_ID").toString();
			String evalMemId = list.get(i).get("EVAL_MEM_ID").toString();
			String evalType = list.get(i).get("EVAL_TYPE").toString();
			String duty = "";
			if(StringUtil.bNvl(list.get(i).get("JOB_DUTY_NM"))) {
				duty = "직책없음";
			} else {
				duty = list.get(i).get("JOB_DUTY_NM").toString();
			}
			
			memberListHTML += "<tr id=\""+evalMemId+"\">";
			memberListHTML += "		<td class=\"id_detail\"><a href=\"#\" onclick=\"fncOpenPopOrgMode("+memId+");\">"+memOrnNm+"-"+nm+"-"+memId+"-"+duty+"</a>&nbsp;<a href=\""+evalMemId+"\" class=\"btnEvalMemDel\"><img src=\"/static/images/btn_del.gif\" style=\"vertical-align:middle\" /></a>";
			memberListHTML += "		<input type=\"hidden\" id=\"evalType\" value=\""+evalType+"\">";
			memberListHTML += "		</td>";
			memberListHTML += "</tr>";
		}
		
		return memberListHTML;
	}
	
	/**
	 * 심사자 설정 팝업 - 대상 검색 결과 HTML
	 * @param list 검색결과 리스트
	 * @return 테이블에 들어갈 <tr>...</tr> 소스
	 */
	public static String getMemberListHTML(List<HashMap> list) {
		String memberListHTML = "";
		
		for(int i = 0; i < list.size(); i++) {
			memberListHTML += "<tr>";
			memberListHTML += "		<td><input type=\"radio\" name=\"mem_id\" id=\""+i+"\" value=\""+StringUtil.nvl(list.get(i).get("MEM_ID"), "")+"\" /></td>";
			memberListHTML += "		<td class=\"al\">"+StringUtil.nvl(list.get(i).get("MEM_NM"), "")+"</td>";
			memberListHTML += "		<td class=\"al\">"+StringUtil.nvl(list.get(i).get("FULL_WORK_ORG_NM"), "")+"</td>";
			/*memberListHTML += "		<td class=\"al\">"+list.get(i).get("ORG_NM").toString()+"</td>";*/
			memberListHTML += "		<td>"+StringUtil.nvl(list.get(i).get("MEM_ID"), "")+"</td>";
			memberListHTML += "		<td>"+StringUtil.nvl(list.get(i).get("POSI_NM"), "")+"</td>";
			memberListHTML += "		<td>"+StringUtil.nvl(list.get(i).get("OFFICE_PHONE_NUM"), "")+"</td>";
			memberListHTML += "		<input type=\"hidden\" name=\"list_org_id\" id=\"list_org_id"+i+"\" value=\""+StringUtil.nvl(list.get(i).get("ORG_ID").toString(), "")+"\"/>";
			memberListHTML += "</tr>";
		}
		
		return memberListHTML;
	}
	
	/**
	 * multipart로 파일 업로드시 파일명 변경에 사용할 현재시간 문자열
	 * @return 현재시간 문자열 리턴
	 */
	public static String getFileNMByNow() {
		String now = new SimpleDateFormat("yyyyMMddHmsS").format(new Date());  //현재시간
		return now;
	}

	
	/**
	 * 임원여부
	 * @param memId :임원여부 판단할 임직원ID
	 * @return Y:임원 N:직원
	 */
	public static String getExecutiveYn( String memId ){
		
		String executiveYn = "N";

		if( memId.length() > 2 ){
			String subStrMemId = memId.substring(0, 2);
			if( subStrMemId.equals("10") ){
				executiveYn = "Y";
			}
		}
		
		return executiveYn;
	}
	
	/**
	 * 여러 타입의 숫자를 int 형으로 변환 리턴
	 * @param memId
	 * @return
	 */
	public static int getInt( Object numberObj ){
		int returnInt = 0;
		if(numberObj != null){
			if(numberObj instanceof BigDecimal)returnInt = ((BigDecimal)numberObj).intValue();
			if(numberObj instanceof Long)returnInt = ((Long)numberObj).intValue();
			if(numberObj instanceof Integer)returnInt = ((Integer)numberObj).intValue();
			if(numberObj instanceof String){
				if(!"".equals(numberObj)){
					returnInt = Integer.valueOf((String)numberObj);
				}
			}
		}
		return returnInt;
	}
	
	/**
	 * 여러 타입의 숫자를 float 형으로 변환 리턴
	 * @param memId
	 * @return
	 */
	public static float getFloat( Object numberObj ){
		float returnInt = 0;
		if(numberObj != null){
			if(numberObj instanceof Float)returnInt = ((Float)numberObj).floatValue();
			if(numberObj instanceof BigDecimal)returnInt = ((BigDecimal)numberObj).floatValue();
			if(numberObj instanceof Long)returnInt = ((Long)numberObj).floatValue();
			if(numberObj instanceof Integer)returnInt = ((Integer)numberObj).floatValue();
			if(numberObj instanceof String){
				if(numberObj.equals("")){
					returnInt = 0;
				}else{
					returnInt = Float.valueOf((String)numberObj);
				}
			}
		}
		return returnInt;
	}
}

