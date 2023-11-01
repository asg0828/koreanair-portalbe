package com.cdp.portal.app.bo.faq.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FaqModel {
    @Schema(description = "FAQ ID", example = "FAQ_ID")
    private String faqId;
    @Schema(description = "분류 코드[CODE GROUP_ID: FAQ_CL_CODE]")
    private String clCode;
    @Schema(description = "질문")
    private String qstn;
    @Schema(description = "답변")
    private String answ;
    @Schema(description = "정렬 순서")
    private int ordSeq;
    @Schema(description = "사용 여부")
    private String useYn;
    @Schema(description = "삭제 여부")
    private String delYn;
    @Schema(description = "등록 ID")
    private String rgstId;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "등록 일시")
    private LocalDateTime rgstDt;
    @Schema(description = "수정 ID")
    private String modiId;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "수정 일시")
    private LocalDateTime modiDt;
    @Schema(description = "뷰 건수")
    private int viewCnt;

    //faq특성상 목록에서 각 글의 파일목록도 같이 보여줘야 함
//    private List<FileModel> fileList;
}
