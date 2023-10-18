package com.bdp.ap.config.props;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * EAI Socket 통신 설정 정보
 */
@Data
@Component
@ConfigurationProperties(prefix = "eai-socket")
public class EaiSocketProps {
	//EAI Socket Client
	private String host;
	private int port;
	//EAI Socket Notify Client
	private int notifyPort;
	//EAI Socket Common
	private String encoding;
	//EAI Socket Server
	private int serverPort;
}
