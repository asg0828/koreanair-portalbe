package com.bdp.ap.app.file.model;

import java.io.InputStream;
import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class FileModel implements Serializable{

	private String fileId;
	private String storageSe;
	private String savePath;
	private String saveFileNm;
	private String fileNm;
	private String fileExtsn;
	private long fileSize;
	private String useYn;
	private String rgstId;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime rgstDt;
	private String modiId;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modiDt;
	private String bucketNm;
	private String fileUrl;
	private String fileCl;
	private String saveFileVer;
	private InputStream inputStream;
	private byte[] bytes;
	private String modiByUserYn; // 사용자가 등록, 수정, 삭제했는지 화면에서 전달되는 값
	private String refId;// 참조 ID -  프로젝트 ID, 리포트 ID 등등
	private int refVer;// 참조 버전
	private String atmcDelYn;// 대용량 자동 삭제 여부
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime atmcDelDt;// 대용량 자동 삭제 일시
	private String delYn; // 파일 삭제 여부
}
