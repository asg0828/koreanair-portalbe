package com.bdp.ap.config.props;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * 외부 데이터 설정정보
 */
@Data
@Component
@ConfigurationProperties(prefix = "external-data-props")
public class ExternalDataProps {

	//엑셀 일괄등록 업로드시 저장경로
	private String excelPath;

}
