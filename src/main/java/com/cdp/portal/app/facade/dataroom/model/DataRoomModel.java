package com.cdp.portal.app.facade.dataroom.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DataRoomModel {
    @Schema(description = "자료실 ID", example = "DATA_ID")
    private String dataId;
    @Schema(description = "제목", example = "제목")
    private String sj;
    @Schema(description = "내용", example = "내용")
    private String cn;
    @Schema(description = "조회수", example = "1")
    private String viewCnt;
    @Schema(description = "사용여부", example = "Y|N")
    private String useYn;
    @Schema(description = "등록자 ID", example = "admin")
    private String rgstId;
    @Schema(description = "등록일시", example = "2023-11-03")
    private Timestamp rgstDt;
    @Schema(description = "수정자 ID", example = "admin")
    private String modiId;
    @Schema(description = "수정일시", example = "2023-11-03")
    private Timestamp modiDt;
    @Schema(description = "삭제여부", example = "Y|N")
    private String delYn;
    @Schema(description = "화면 순번")
    private int rownum;
    @Schema(description = "등록자 이름")
    private String rgstNm;
    @Schema(description = "부서 이름")
    private String rgstDeptNm;
}
