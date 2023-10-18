package com.bdp.ap.config.props;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * file upload/download 설정 정보
 */
@Data
@Component
@ConfigurationProperties(prefix = "file-props")
public class FileProps {

	private String uploadPath;
	private String path;
	private String uploadServer;
}
