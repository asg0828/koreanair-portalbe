package com.bdp.ap.schedule.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.bdp.ap.app.file.model.FileModel;
import com.bdp.ap.app.member.model.MemberModel;
import com.bdp.ap.common.Constant;
import com.bdp.ap.common.IdUtil;
import com.bdp.ap.config.props.HrProps;
import com.bdp.ap.schedule.mapper.JobUserPhotoMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JobUserPhotoService {

	@Resource(name = "hrProps")
	private HrProps props;

	@Resource(name = "jobUserPhotoMapper")
	private JobUserPhotoMapper mapper;

	@Resource(name = "idUtil")
	private IdUtil idUtil;

	/**
	 * 사용자 사진 목록 조회
	 *
	 * @return
	 */
	public List<Path> getPhotoFiles() {
		List<Path> result = null;

		try {
			result = Files.list(Paths.get(props.getBasePhotoPath())).filter(Files::isRegularFile)
					.collect(Collectors.toList());
		} catch (IOException e) {
			result = new ArrayList<>();
			log.warn("사용자 사진 목록 조회 중 오류 발생");
			log.warn(e.getMessage());
		}

		return result;
	}

	/**
	 * 사용자 사진 저장
	 */
	public JSONObject saveFile(Path path) {
		JSONObject result = new JSONObject();
		/*
		 * 사진 저장 처리
		 * 0. 파일명.확장자 = userId.jpg
		 * 1. MemberModel 조회 후 fileId 확인
		 *  1.1 조회된 userId 없으면 중지하고 다음 userId 처리
		 * 2. fileId 없으면 신규 등록 처리
		 *  2.1 FileModel 생성 후 값 설정
		 *  2.2 AWS S3 등록
		 *  2.3 FileModel Insert
		 *  2.4 MemberModel Update
		 * 3. fileId 있으면 수정 등록 처리
		 *  3.1 FileModel 조회
		 *  3.2 AWS S3 삭제
		 *  3.3 AWS S3 등록
		 * 4. 파일 처리 완료 후 cache/user-photo 폴더로 이동
		 * 5. 처리 결과
		 *  5.1 처리 결과는 "rslt":boolean 으로 반환
		 *  5.2 에러는 "errMsg": "에러 메시지 내용" 으로 반환
		 */
		try {
			// jpg, png 파일이 아니면 중지
			boolean hasNext = false;
			String fileNm = path.getFileName().toString();
			String userId = FilenameUtils.getBaseName(fileNm);
			String fileExtsn = FilenameUtils.getExtension(fileNm);
			if (StringUtils.equalsAnyIgnoreCase(fileExtsn, "jpg","png")) {
				MemberModel user = mapper.selectUser(userId);

				if (user != null) {
					if (StringUtils.isBlank(user.getFileUrl())) {
						// 사진 등록 처리
						FileModel file = new FileModel();
						file.setFileId(idUtil.getFileId());
						file.setRefId(user.getUserId());
						file.setFileCl(Constant.File.PHOTO);
						file.setFileNm(fileNm);
						file.setFileExtsn(fileExtsn);
						file.setSaveFileNm(fileNm);
						file.setFileUrl(idUtil.getUUID());
						file.setFileSize(Files.size(path));
						file.setInputStream(Files.newInputStream(path));
						//file.setBytes(FileUtils.readFileToByteArray(path.toFile()));

						// S3 파일 등록
						// hasNext = s3Util.upload(file);
						if (hasNext) {

							// 파일 정보 등록
							mapper.insertFile(file);

							// 사용자 파일 정보 수정
							mapper.updateUserFileInfo(file);

						}

					} else {
						// 사진 수정 처리

						// 기존 파일 조회
						FileModel file = mapper.selectFile(user.getFileUrl());

						// S3 삭제
						//s3Util.delete(file);

						// S3 신규 파일 등록 - 파일 over write
						file.setFileSize(Files.size(path));
						file.setInputStream(Files.newInputStream(path));
						//file.setBytes(FileUtils.readFileToByteArray(path.toFile()));
						// hasNext = s3Util.upload(file);
						if (hasNext) {

							// 파일 정보 업데이트
							mapper.updateFile(file);

						}
					}

					if (hasNext) {
						// S3에 업로드 되었으면 cache 로 이동
						Files.move(path, Paths.get(props.getCachePhotoPath(),fileNm), StandardCopyOption.REPLACE_EXISTING);
					}

				} else {
					// 파일명에 해당하는 사용자가 없으면 파일 삭제
					Files.delete(path);
				}
			} else {
				// jpg, png 파일이 아니면 파일 삭제
				Files.delete(path);
			}
			result.put("rslt", true);
		} catch (Exception e) {
			log.warn("사용자 사진 처리 중 오류 발생");
			log.warn(e.getMessage());
			result.put("rslt", false);
			result.put("errMsg", e.getMessage());
		}
		return result;
	}

}
