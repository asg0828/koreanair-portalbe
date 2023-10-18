package com.bdp.ap.config.props;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * Goldwing 설정 정보
 */
@Data
@Component
@ConfigurationProperties(prefix = "goldwing-props")
public class GoldwingProps {
	
	private String conndocUrl;
	private String svcCode;
	private String formId;
	private String eaiInterfaceId;
	private String eaiServiceCode;
	
}
