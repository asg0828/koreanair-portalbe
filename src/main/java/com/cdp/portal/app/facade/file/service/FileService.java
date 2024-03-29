package com.cdp.portal.app.facade.file.service;

import com.amazonaws.util.IOUtils;
import com.cdp.portal.app.facade.file.dto.response.FileResDto;
import com.cdp.portal.app.facade.file.mapper.FileMapper;
import com.cdp.portal.app.facade.file.model.FileModel;
import com.cdp.portal.common.CommonUtil;
import com.cdp.portal.common.IdUtil;
import com.cdp.portal.common.aws.AwsS3Util;
import com.cdp.portal.common.util.SessionScopeUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Slf4j
@Service
@RequiredArgsConstructor
public class FileService {

    private final FileMapper fileMapper;
    private final IdUtil idUtil;
    private final AwsS3Util s3Util;
    private final CommonUtil commonUtil;

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
     *
     * @param
     */
    @Transactional
    public List<String> insertFile(List<MultipartFile> files, String fileCl) throws IOException {
        List<String> activeProfiles = commonUtil.getProfiles();

        String activeProfile = activeProfiles.get(0);
        String bucketName = null;

        if ("local".equals(activeProfile)) {
            bucketName = "awsdc-s3-dlk-dev-cdp-portalobject";
        } else if ("dev".equals(activeProfile)) {
            bucketName = "awsdc-s3-dlk-dev-cdp-portalobject";
        } else if ("prd".equals(activeProfile)) {
            bucketName = "awsdc-s3-dlk-prd-cdp-portalobject";
        }

        List<FileModel> fileModels = new ArrayList<>();
        List<String> uploadedFileIds = new ArrayList<>();

        try {
            for (MultipartFile file : files) {
                String fileNm = file.getOriginalFilename();
                String fileExtsn = "";

                if (fileNm != null) {
                    int lastIndex = fileNm.lastIndexOf(".");
                    if (lastIndex != -1) {
                        fileExtsn = fileNm.substring(lastIndex + 1);
                    }
                }
                String fileUUID = idUtil.getUUID() + '.' + fileExtsn;

                final String fileId = idUtil.getFileId();
                log.debug("##### insertFile fileId: {}", fileId);

                FileModel fileModel = FileModel.builder()
                        .fileId(fileId)
                        .fileNm(fileNm)
                        .fileExtsn(fileExtsn)
                        .savePath("board")
                        .fileSize(file.getSize())
                        .saveFileNm(fileUUID)
                        .inputStream(file.getResource().getInputStream())
                        .storageSe("S3")
                        .bucketNm(bucketName)
                        .useYn("Y")
                        .rgstId(SessionScopeUtil.getContextSession().getUserId())
                        .modiId(SessionScopeUtil.getContextSession().getUserId())
                        .fileCl(fileCl)
                        .build();
                uploadedFileIds.add(fileId);
                fileModels.add(fileModel);
            }
            boolean uploadSuccess = s3Util.upload(fileModels, bucketName);
            if (uploadSuccess) {
                for (FileModel fileModel : fileModels) {
                    fileMapper.insertFile(fileModel);
                }
            } else {
                throw new RuntimeException("파일 업로드에 실패하였습니다");
            }

        } catch (Exception e) {
            throw new RuntimeException("파일 업로드 중 오류가 발생하였습니다.", e);
        }
        return uploadedFileIds;
    }




    /**
     * 파일 목록 조회
     *
     * @param
     */
    @Transactional(readOnly = true)
    public List<FileModel> selectFileList(FileModel model) {

        return fileMapper.selectFileList(model);
    }

    /**
     * 파일 조회
     *
     * @param fileId
     */
    @Transactional(readOnly = true)
    public FileModel selectFile(String fileId) {

        return fileMapper.selectByFileId(fileId);
    }


    /**
     * S3 파일 다운로드
     *
     * @param fileId
     */
    @Transactional
    public ResponseEntity<ByteArrayResource> downloadFile(String fileId) {
        ByteArrayResource resource = null;

        long fileSize = 0;
        String fileNm = "";
        // request의 파일 정보 파라메터로 파일을 조회 후 다운로드
        FileModel fileModel = fileMapper.selectByFileId(fileId);

        if (fileModel != null) {
            // S3에서 파일 다운로드
            boolean downloadSuccess = s3Util.download(fileModel);

            if (downloadSuccess) {
                // 다운로드 성공한 경우 파일 정보 설정
                byte[] fileBytes = fileModel.getBytes();
                resource = new ByteArrayResource(fileBytes);
                fileSize = fileBytes.length;
                fileNm = fileModel.getFileNm();
            }
        }

        HttpHeaders headers = getHeaders(fileNm);
        headers.setContentLength(fileSize);

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(fileSize)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }

    /**
     * 파일 수정
     *
     * @param model
     * @return
     */
    public long updateFile(FileModel model) {
        return fileMapper.updateFile(model);
    }


    /**
     * 파일 삭제
     *
     * @param fileId
     * @return 삭제한 파일의 ID
     */
    @Transactional
    public List<String> deleteFile(String fileId) {
        List<String> deletedFileIds = new ArrayList<>();
        FileModel file = fileMapper.selectByFileId(fileId);

        if (file != null) {
            file.setUseYn("N");
            fileMapper.deleteFile(file);
            deletedFileIds.add(fileId);
        }

        return deletedFileIds;
    }
}