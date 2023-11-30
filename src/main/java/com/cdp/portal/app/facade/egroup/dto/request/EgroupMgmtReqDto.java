package com.cdp.portal.app.facade.egroup.dto.request;

import java.util.List;

import javax.validation.constraints.NotBlank;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Schema(description = "사용자 예외그룹 관리 요청")
public class EgroupMgmtReqDto {
	public static class ApiReqEgroups extends EgroupMgmtReqDto.EgroupInfos {}

	@Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Schema(description = "예외그룹 저장")
	public static class SaveEgroup {
		@NotBlank
	    @Schema(description = "그룹 코드 - 생성(insert)시 무시됨", example = "", nullable = false)
	    private String groupCode;

		@NotBlank
	    @Schema(description = "그룹 명", example = "", nullable = false)
	    private String groupNm;

	    @Schema(description = "상위 그룹 코드", example = "")
	    private String upGroupCode;

	    @Schema(description = "정렬 순서", example = "")
	    private int ordSeq;

	    @Schema(description = "사용자권한 ID", example = "")
        private String userAuthId;

        @Schema(description = "관리자권한 ID", example = "")
        private String mgrAuthId;

        @Schema(description = "오퍼레이터 구분[C:생성, U:수정, D:삭제]", example = "")
        private String oprtrSe;
	}

	@Getter
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	@Schema(description = "예외그룹별 사용자 그룹코드 수정")
	public static class EgroupUserUpdate {

		@Schema(description = "그룹 코드", nullable = false)
		private String groupCode;

		@Schema(description = "사용자 ID", nullable = false)
		private String[] userIds;

	}

    @Getter
    @Schema(description = "예외그룹 검색")
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Builder
    public static class SearchEgroup {

        @Schema(description = "그룹 명", example = "")
        private String groupNm;

    }

    @Getter
    @Schema(description = "예외그룹/사용자별 예외그룹 저장")
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Builder
    public static class EgroupInfos {

    	@Schema(description = "그룹 정보", example = "")
    	private List<SaveEgroup> saveEgroup;

    	@Schema(description = "그룹별 사용자 정보 저장", example = "")
    	private List<EgroupUserUpdate> egroupUserUpdate;

    }

}
