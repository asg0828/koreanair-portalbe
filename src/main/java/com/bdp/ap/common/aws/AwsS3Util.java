package com.bdp.ap.common.aws;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.bdp.ap.app.file.model.FileModel;
import com.bdp.ap.common.CommonUtil;
import com.bdp.ap.common.Constant;
import com.bdp.ap.common.IdUtil;
import com.bdp.ap.config.props.AwsProps;

import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.ListObjectsRequest;
import software.amazon.awssdk.services.s3.model.ListObjectsResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;
import software.amazon.awssdk.services.s3.model.S3Object;

@Slf4j
@Component
public class AwsS3Util {
	
	@Resource(name = "awsProps")
	private AwsProps awsProps;

	@Resource(name = "s3Client")
	private S3Client s3;
	
	@Resource(name="commonUtil")
	private CommonUtil commonUtil;
	
	@Resource(name="idUtil")
	private IdUtil idUtil;
	
	private static final String SEPARATOR = "/";

	private void setSavePath(FileModel file) {
		// 저장소 구분
		file.setStorageSe(Constant.File.AWS_S3);
		// 버킷 명
		file.setBucketNm(awsProps.getBucketName());
		// 저장 경로
		switch (file.getFileCl()) {
		case Constant.File.PHOTO:
			//사용자 사진
			file.setSavePath(awsProps.getPhotoPath());
			break;
		case Constant.File.PREVIEW:
			//태블로 프리뷰
			file.setSavePath(awsProps.getPreviewPath());
			break;
		case Constant.File.VIEW:
			//태블로 뷰
			file.setSavePath(awsProps.getViewPath());
			break;
		default:
			//첨부 파일
			file.setSavePath(StringUtils.joinWith(SEPARATOR, awsProps.getFilePath(),commonUtil.getDateString("YYYY/MM/DD")));
			if (StringUtils.isBlank(file.getFileCl())) {
				file.setFileCl(Constant.File.BOARD);
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
	 * S3 파일 목록
	 */
	public void getList() throws Exception {

		// 목록 조회
		ListObjectsRequest listObjectsRequest = ListObjectsRequest.builder().bucket(awsProps.getBucketName()).build();
		ListObjectsResponse lor = s3.listObjects(listObjectsRequest);
		for (S3Object obj : lor.contents()) {
			obj.key();
		}

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
		// 올리기
		boolean result = true;
		try {
			if (StringUtils.isBlank(file.getStorageSe())) {
				setSavePath(file);
			}
			String key = StringUtils.joinWith(SEPARATOR, file.getSavePath(),file.getSaveFileNm());
			PutObjectRequest put = PutObjectRequest.builder().bucket(file.getBucketNm()).key(key).build();
			// InputStream
			//s3.putObject(put, RequestBody.fromInputStream(file.getInputStream(), file.getFileSize()));
			// byte array
			s3.putObject(put, RequestBody.fromBytes(IOUtils.toByteArray(file.getInputStream())));
			try {
				file.getInputStream().close();
			} catch (IOException e) {
				log.warn("S3 FILE UPLOAD 후 InputStream Close 오류");
			}
		} catch(IOException | S3Exception | SdkClientException e) {
			result = false;
			log.warn("S3 FILE UPLOAD FAILED");
			log.warn(e.getMessage());
		} finally {
			
		}
		return result;
	}

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
		// 가져오기
		boolean result = true;
		try {
			GetObjectRequest req = GetObjectRequest.builder().bucket(file.getBucketNm())
					.key(StringUtils.joinWith(SEPARATOR, file.getSavePath(),file.getSaveFileNm())).build();
			ResponseBytes<GetObjectResponse> obj = s3.getObjectAsBytes(req);
			file.setInputStream(obj.asInputStream());
		} catch (S3Exception | SdkClientException e) {
			result = false;
			log.warn("S3 FILE DOWNLOAD FAILED");
			log.warn(e.getMessage());
		}
		return result;
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
		// 삭제
		boolean result = true;
		try {
			DeleteObjectRequest req = DeleteObjectRequest.builder().bucket(file.getBucketNm())
					.key(StringUtils.joinWith(SEPARATOR, file.getSavePath(),file.getSaveFileNm())).build();
			s3.deleteObject(req);
		} catch (S3Exception e) {
			result = false;
			log.warn("S3 FILE DELETE FAILED");
			log.warn(e.getMessage());
		}
		return result;
	}
}
