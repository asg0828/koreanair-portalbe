package com.bdp.ap.config.props;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * 시스템 수집 로그
 */
@Data
@Component
@ConfigurationProperties(prefix = "log-sys-props")
public class LogSysProps {

	private String logPath;
	private String encoding;
	private int deletePd;
	private String userRqstCntFile;
}
