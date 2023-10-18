package com.bdp.ap.config.props;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * Tableau 설정 정보
 */
@Data
@Component
@ConfigurationProperties(prefix = "tableau-props")
public class TableauProps {
	private String apiUrl;
	private String tokenUrl;
	private String schemaLocation;
	private String username;
	private String userpw;
	private String defaultContentUrl;
	private String targetSite;
	// 태블로 미리보기 캐시
	private String cachePreviewPath;
}
