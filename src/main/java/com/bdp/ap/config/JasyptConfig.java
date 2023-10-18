package com.bdp.ap.config;

import javax.annotation.Resource;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bdp.ap.common.CommonUtil;
import com.bdp.ap.common.Constant;

@Configuration
public class JasyptConfig {

	@Resource(name="commonUtil")
	private CommonUtil commonUtil;

	/**
	 * Jasypt 암호화
	 * @return
	 */
	@Bean
	public StringEncryptor jasyptStringEncryptor() {
		PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();

		SimpleStringPBEConfig config = new SimpleStringPBEConfig();

		config.setPassword(Constant.Jasypt.KEY);

		config.setAlgorithm(Constant.Jasypt.ALGORITHM);
		config.setKeyObtentionIterations("1000");
		config.setPoolSize("1");
		config.setProviderName("SunJCE");
		config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
		config.setIvGeneratorClassName("org.jasypt.iv.RandomIvGenerator");
		config.setStringOutputType("base64");

		encryptor.setConfig(config);
		return encryptor;
	}
}
