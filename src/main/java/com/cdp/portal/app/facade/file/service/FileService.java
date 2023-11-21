package com.cdp.portal.app.facade.file.service;

import com.amazonaws.util.IOUtils;
import com.cdp.portal.app.facade.file.dto.response.FileResDto;
import com.cdp.portal.app.facade.file.mapper.FileMapper;
import com.cdp.portal.app.facade.file.model.FileModel;
import com.cdp.portal.common.IdUtil;
import com.cdp.portal.common.aws.AwsS3Util;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class FileService {

    private final FileMapper fileMapper;
    private final IdUtil idUtil;

    private final AwsS3Util s3Util;

    /**
     * 파일 다운로드 헤더
     */
    @Transactional
    public HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Pragma", "no-cache");
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        return headers;
    }

    /**
     * 파일 다운로드 헤더
     */
    @Transactional
    public HttpHeaders getHeaders(String fileNm) {
        HttpHeaders headers = getHeaders();
        if (StringUtils.isNotBlank(fileNm)) {
            String downFileNm = "";
            try {
                downFileNm = URLEncoder.encode(fileNm, "UTF-8").replaceAll("\\+", "%20");
            } catch (UnsupportedEncodingException e) {
                log.warn("File download name URLEncoder encode Exception");
                downFileNm = "download";
            }
            headers.add("Content-Disposition", StringUtils.joinWith(null, "attachment;filename=\"", downFileNm, "\""));
        }
        return headers;
    }

    /**
     * 파일을 ByteArrayResource 로 변환
     *
     * @param model
     * @return
     */
    @Transactional
    public ByteArrayResource getByteArrayResource(FileModel model) {
        ByteArrayResource resource = null;
        try {
            File file = new File(model.getSavePath(), model.getSaveFileNm());

            resource = new ByteArrayResource(IOUtils.toByteArray(new FileInputStream(file)));
        } catch (IOException e) {
            log.warn("file 변환 처리 중 오류");
            log.warn(e.getMessage());
        }
        return resource;
    }

    /**
     * 파일 등록
     * @param
     *
     */
    @Transactional
    public void insertFile(MultipartFile file) throws IOException {
        String fileNm = file.getOriginalFilename();

        final String fileId = idUtil.getFileId();
        log.debug("##### insertFile fileId: {}", fileId);

        FileModel fileModel = FileModel.builder()
                .fileId(fileId)
                .fileNm(fileNm)
                .savePath("awsdc-s3-dlk-dev-cdp-portalobject/board")
                .fileSize(file.getSize())
                .saveFileNm(fileNm)
                .inputStream(file.getResource().getInputStream())
                .storageSe("S3")
                .bucketNm("awsdc-s3-dlk-dev-cdp-portalobject")
                .useYn("Y")
                .rgstId("admin")
                .modiId("admin") // TODO: 로그인한 사용자 세팅
                .build();

        fileMapper.insertFile(fileModel);
        s3Util.upload(fileModel);
    }

    /**
     * 파일 목록 조회
     * @param
     *
     */
    @Transactional
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