package com.bdp.ap.config.props;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * HR 설정 정보
 */
@Data
@Component
@ConfigurationProperties(prefix = "hr-props")
public class HrProps {

	private String basePath;
	private String backupPath;
	private String encoding;
	private String userFile;
	private String deptFile;
	private String hdeptFile;
	private String pstnFile;
	//부서 분류
	private String baseDeptClPath;
	private String deptClFile;
	//사용자 사진
	private String basePhotoPath;
	private String cachePhotoPath;
}
