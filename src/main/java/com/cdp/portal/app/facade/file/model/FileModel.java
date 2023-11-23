package com.cdp.portal.app.facade.file.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.InputStream;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FileModel {

    @Schema(description = "파일ID", example = "fl23000001315")
    private String fileId;
    @Schema(description = "저장소 구분", example = "NAS")
    private String storageSe;
    @Schema(description = "저장 경로", example = "C:/fileupload/EDITOR/")
    private String savePath;
    @Schema(description = "저장 파일 명", example = "98ff6a0a-4658-4ae9-8e14-e112b56f741e")
    private String saveFileNm;
    @Schema(description = "파일 명", example = "테스트.png")
    private String fileNm;
    @Schema(description = "파일 확장자", example = "png")
    private String fileExtsn;
    @Schema(description = "파일 사이즈", example = "18547")
    private long fileSize;
    @Schema(description = "사용 여부", example = "Y|N")
    private String useYn;
    @Schema(description = "등록 ID", example = "RGST_ID")
    private String rgstId;
    @Schema(description = "등록 일시", example = "2023-11-06 11:31:13.378280")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp rgstDt;
    @Schema(description = "수정 ID", example = "MODI_ID")
    private String modiId;
    @Schema(description = "수정 일시", example = "2023-11-06 11:31:13.378280")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp modiDt;
    @Schema(description = "버킷 명[S3 / NAS]", example = "")
    private String bucketNm;
    @Schema(description = "파일 URL", example = "fl23000001312")
    private String fileUrl;
    @Schema(description = "파일 분류", example = "NOTICE")
    private String fileCl;
    @Schema(description = "저장 파일 버전", example = "")
    private String saveFileVer;
    @JsonIgnore
    private InputStream inputStream;
    private byte[] bytes;
    private String modiByUserYn; // 사용자가 등록, 수정, 삭제했는지 화면에서 전달되는 값
    @Schema(description = "참조 ID", example = "sc23000000040")
    private String refId;// 참조 ID -  프로젝트 ID, 리포트 ID 등등
    @Schema(description = "참조 버전", example = "")
    private int refVer;// 참조 버전
    @Schema(description = "자동 삭제 여부", example = "Y|N")
    private String atmcDelYn;// 대용량 자동 삭제 여부
    @Schema(description = "자동 삭제 일시", example = "")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp atmcDelDt;// 대용량 자동 삭제 일시
    @Schema(description = "파일 삭제 여부", example = "Y|N")
    private String delYn; // 파일 삭제 여부
}
