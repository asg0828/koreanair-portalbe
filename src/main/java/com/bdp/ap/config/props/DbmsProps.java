package com.bdp.ap.config.props;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * DBMS 설정 정보
 */
@Data
@Component
@ConfigurationProperties(prefix = "dbms-props")
public class DbmsProps {
	private String dbmsKind;
}
