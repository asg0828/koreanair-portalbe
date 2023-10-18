package com.bdp.ap.config.props;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * EAI 수집 로그
 */
@Data
@Component
@ConfigurationProperties(prefix = "log-to-file-props")
public class LogToFileProps {

	private String logPath;
	private String encoding;
	private int deletePd;
	private String userFile;
	private String userHistFile;
	private String rqstMgrSysFile;
	private String rqstUserSysFile;
	private String tableauMgrSysFile;
	private String tableauUserSysFile;
	private String bizmetaMgrSysFile;
	private String bizmetaUserSysFile;
	private String awsMgrSysFile;
	private String awsUserSysFile;
	private String aprvRqstFile;
}
