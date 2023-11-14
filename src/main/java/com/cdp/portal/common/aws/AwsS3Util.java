package com.cdp.portal.common.aws;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.util.IOUtils;
import com.cdp.portal.app.facade.file.model.FileModel;
import com.cdp.portal.common.IdUtil;
import com.cdp.portal.common.CommonUtil;
import com.cdp.portal.common.aws.props.AwsProps;
import com.cdp.portal.common.constants.CommonConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;

import static org.springframework.data.redis.cache.CacheKeyPrefix.SEPARATOR;

@Slf4j
@Component
public class AwsS3Util {
    @Resource(name = "awsProps")
    private AwsProps awsProps;
    @Resource(name="commonUtil")
    private CommonUtil commonUtil;

    @Resource(name="idUtil")
    private IdUtil idUtil;

    private AmazonS3 s3Client;

    private void setSavePath(FileModel file) {
        // 저장소 구분
        file.setStorageSe(CommonConstants.File.AWS_S3);
        // 버킷 명
        file.setBucketNm(awsProps.getBucketName());
        // 저장 경로
        switch (file.getFileCl()) {
            case CommonConstants.File.PREVIEW:
                //태블로 프리뷰
                file.setSavePath(awsProps.getPreviewPath());
                break;
            case CommonConstants.File.VIEW:
                //태블로 뷰
                file.setSavePath(awsProps.getViewPath());
                break;
            default:
                //첨부 파일
                file.setSavePath(StringUtils.joinWith(SEPARATOR, awsProps.getFilePath(),commonUtil.getDateString("YYYY/MM/DD")));
                if (StringUtils.isBlank(file.getFileCl())) {
                    file.setFileCl(CommonConstants.File.BOARD);
                }
                break;
        }
        if (StringUtils.isBlank(file.getSaveFileNm())) {
            file.setSaveFileNm(idUtil.getUUID());
        }
        if (StringUtils.isBlank(file.getFileUrl())) {
            file.setFileUrl(file.getSaveFileNm());
        }
    }

    /**
     * S3 파일 목록 목록 조회
     */
    public void getList() throws Exception {
        ListObjectsV2Result result = s3Client.listObjectsV2(awsProps.getBucketName());
        for (S3ObjectSummary summary : result.getObjectSummaries())
            System.out.println(" - " + summary.getKey() + "  " +
                    "(size = " + summary.getSize() + ")");
    }

    /**
     * S3 파일 목록 업로드
     */
    public boolean upload(List<FileModel> files) {
        boolean result = true;
        for (FileModel file : files) {
            result = upload(file) && result;
        }
        return result;
    }

    /**
     * S3 파일 업로드
     */
    public boolean upload(FileModel file) {
        try {
            // 파일 경로 및 이름 설정
           String key = StringUtils.joinWith(SEPARATOR, file.getSavePath(), file.getSaveFileNm());
            // 파일 업로드 요청 생성
            PutObjectRequest putRequest = new PutObjectRequest(awsProps.getBucketName(), key, file.getInputStream(), null);
            // 파일 업로드 실행
            s3Client.putObject(putRequest);
            return true;
        } catch (Exception e) {
            log.warn("S3 FILE UPLOAD FAILED", e);
            return false;
        }
    }

//    public boolean uploadFromUri(String s3Uri, InputStream inputStream) {
//        boolean result = true;
//        try {
//            // S3 URI를 파싱하여 버킷 이름과 객체 키를 추출합니다.
//            S3Uri s3UriObj = S3Uri.parse(s3Uri);
//            String bucketName = s3UriObj.bucket();
//            String objectKey = s3UriObj.key();
//
//            // 파일 업로드 요청 생성
//            PutObjectRequest putRequest = PutObjectRequest.builder()
//                    .bucket(bucketName)
//                    .key(objectKey)
//                    .build();
//
//            // 파일 업로드
//            s3Client.putObject(putRequest, RequestBody.fromInputStream(inputStream, -1));
//
//            // 파일 업로드 성공 시
//            log.info("File uploaded successfully to S3 URI: {}", s3Uri);
//        } catch (S3Exception | SdkClientException e) {
//            result = false;
//            log.warn("S3 FILE UPLOAD FAILED");
//            log.warn(e.getMessage());
//        } finally {
//            // 필요에 따라 inputStream을 닫는 로직을 추가할 수 있습니다.
//        }
//        return result;
//    }


    /**
     * S3 파일 목록 다운로드
     */
    public boolean download(List<FileModel> files) {
        boolean result = true;
        for (FileModel file : files) {
            result = download(file) && result;
        }
        return result;
    }

    /**
     * S3 파일 다운로드
     */
    public boolean download(FileModel file) {
        try {
            // 파일 경로 및 이름 설정
            String key = StringUtils.joinWith(SEPARATOR, file.getSavePath(), file.getSaveFileNm());
            // 파일 다운로드 요청 생성
            S3Object s3Object = s3Client.getObject(new GetObjectRequest(awsProps.getBucketName(), key));
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            IOUtils.copy(s3Object.getObjectContent(), baos);
            file.setBytes(baos.toByteArray());
            return true;
        } catch (Exception e) {
            log.warn("S3 FILE DOWNLOAD FAILED", e);
            return false;
        }
    }

    /**
     * S3 파일 목록 삭제
     */
    public boolean delete(List<FileModel> files) {
        boolean result = true;
        for (FileModel file : files) {
            result = delete(file) && result;
        }
        return result;
    }

    /**
     * S3 파일 삭제
     */
    public boolean delete(FileModel file) {
        try {
            // 파일 경로 및 이름 설정
            String key = StringUtils.joinWith(SEPARATOR, file.getSavePath(), file.getSaveFileNm());
            // 파일 삭제 요청 생성 및 실행
            s3Client.deleteObject(new DeleteObjectRequest(awsProps.getBucketName(), key));
            return true;
        } catch (Exception e) {
            log.warn("S3 FILE DELETE FAILED", e);
            return false;
        }
    }
}