package com.cdp.portal.app.facade.file.service;

import com.cdp.portal.app.facade.file.dto.request.FileReqDto;
import com.cdp.portal.app.facade.file.dto.response.FileResDto;
import com.cdp.portal.app.facade.file.mapper.FileMapper;
import com.cdp.portal.app.facade.file.model.FileModel;
import com.cdp.portal.common.IdUtil;
import com.cdp.portal.common.aws.AwsS3Util;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.Date;

import static software.amazon.ion.Timestamp.now;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileService {

    private final FileMapper fileMapper;
    private final IdUtil idUtil;

    /**
     * 파일 등록
     * @param dto
     *
     */
    @Transactional
    public void insertFile(MultipartFile file) {
        String name = file.getName();
        Resource resource = file.getResource();
        final String fileId = idUtil.getFileId();
        log.debug("##### insertFile fileId: {}", fileId);
        AwsS3Util s3util = new AwsS3Util();

        String s3Uri = "s3://awsdc-s3-dlk-dev-cdp-portalobject";
//        InputStream fileInputStream = FileReqDto.setInputStream();
//        boolean uploadResult = s3util.uploadFromUri(s3Uri, fileInputStream);

        FileModel fileModel = FileModel.builder()
                .fileId(fileId)
                .storageSe("S3")
                .savePath("dto.getSavePath()")
                .saveFileNm("dto.getSaveFileNm()")
                .bucketNm("awsdc-s3-dlk-dev-cdp-portalfe")
                .useYn("Y")
                .rgstId("admin")
                .modiId("admin") // TODO: 로그인한 사용자 세팅
//                .modiDt() // TODO: 로그인한 사용자 세팅
                .build();

        fileMapper.insertFile(fileModel);
        s3util.upload(fileModel);
    }

    /**
     * 파일 목록 조회
     * @param
     *
     */
    public FileResDto.FilesResult getFiles () {

        return FileResDto.FilesResult.builder()
                .contents(fileMapper.selectAll())
                .build();
    }

    /**
     * 파일 조회
     * @param fileId
     *
     */
    @Transactional
    public FileResDto getFile(String fileId) {
        return fileMapper.selectByFileId(fileId);
    }

    /**
     * 파일 다운로드
     * @param fileId
     *
     */


    /**
     * 파일 삭제
     * @param fileId
     *
     */


}
