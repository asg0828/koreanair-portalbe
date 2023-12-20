package com.cdp.portal.app.facade.user.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Schema(description = "사용자관리 요청")
public class UserMgmtReqDto {
    @Getter
    @Schema(description = "사용자 검색")
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Builder
    public static class SearchUser {

        @Schema(description = "사용자 명", example = "")
        private String userNm;

        @Schema(description = "부서(팀)명", example = "")
        private String deptNm;

        @Schema(description = "사용자권한 ID", example = "")
        private String userAuthId;

        @Schema(description = "관리자권한 ID", example = "")
        private String mgrAuthId;

        @Schema(description = "재직구분", example = "Y")
        private String useYn;

        @Schema(description = "페이징 여부 [Y:페이징 하지 않음, N:페이징]", example = "Y")
        private String withNoPaging;
    }

//    @Getter
//    @Schema(description = "사용자 저장 - insert")
//    @NoArgsConstructor(access = AccessLevel.PROTECTED)
//    @AllArgsConstructor
//    @Builder
//    public static class SaveUser {
//
//        @Schema(description = "사용자 ID", nullable = false)
//        private String userId;
//
//        @Schema(description = "사용자 명", nullable = false)
//        private String userNm;
//
//        @Schema(description = "사용자 이메일", example = "")
//        private String userEmail;
//
//        @Schema(description = "부서(팀)코드", example = "")
//        private String deptCode;
//
//        @Schema(description = "상위 부서코드", example = "")
//        private String upDeptCode;
//
//    }
}
