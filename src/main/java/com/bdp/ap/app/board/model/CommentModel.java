package com.bdp.ap.app.board.model;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat; 
import com.bdp.ap.common.paging.Criteria;

import lombok.Data;

@Data
public class CommentModel extends Criteria{

    private String boardId;
    private String commentId;
    private String bfCommentId;
    private String cn;
    private String refId;
    private String delYn;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime rgstDt;
    private String rgstId;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modiDt;
    private String modiId;
    private String rgstNm;
    private String rgstDeptNm;
    private int lv; // 레벨
	
}
