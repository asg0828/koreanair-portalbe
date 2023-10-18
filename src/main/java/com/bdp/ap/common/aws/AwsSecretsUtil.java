package com.bdp.ap.common.aws;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.bdp.ap.common.CommonUtil;
import com.bdp.ap.common.Constant;

import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueResponse;
import software.amazon.awssdk.services.secretsmanager.model.SecretsManagerException;

@Slf4j
@Component
public class AwsSecretsUtil {
	
	@Resource(name="commonUtil")
	private CommonUtil commonUtil;

	@Resource(name="awsCredentialsProvider")
	private AwsCredentialsProvider awsCredentialsProvider;
	
	private SecretsManagerClient secretsClient;
	
	private JSONObject secretsKey;
	
	// AWS Secret 을 사용할 경우 주석 해제
	//@PostConstruct
	public void setup() {
		secretsClient = SecretsManagerClient.builder().credentialsProvider(awsCredentialsProvider).region(Constant.AWS.REGION).build();
	}
	
	public String getValue(String key) {
		return getSecretsKey().optString(key, StringUtils.EMPTY);
	}
	
	private JSONObject getSecretsKey() {
		if (secretsKey == null) {
			try {
				GetSecretValueRequest getSecretValueRequest = null;
				if (StringUtils.equals(Constant.Profile.PROD, commonUtil.getProfile())) {
					getSecretValueRequest = GetSecretValueRequest.builder().secretId(Constant.AWS.SECRET_NAME_PRD).build();
				} else {
					getSecretValueRequest = GetSecretValueRequest.builder().secretId(Constant.AWS.SECRET_NAME_DEV).build();
				}
				GetSecretValueResponse getSecretValueResponse = secretsClient.getSecretValue(getSecretValueRequest);
				String secret = getSecretValueResponse.secretString();
				if (StringUtils.isNotBlank(secret)) {
					secretsKey = new JSONObject(secret);
				}
			} catch (SecretsManagerException e) {
				log.warn("AWS SecretsManager Response Failed");
				log.warn(e.getMessage());
			} catch (JSONException e) {
				log.warn("AWS SecretsManager Parsing JSON Failed");
				log.warn(e.getMessage());
			}
		}
		return secretsKey;
	}
}
