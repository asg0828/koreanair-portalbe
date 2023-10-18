package com.bdp.ap.common.aws;

import javax.annotation.Resource;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.bdp.ap.common.Constant;

import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.services.lambda.LambdaClient;
import software.amazon.awssdk.services.lambda.model.InvokeRequest;
import software.amazon.awssdk.services.lambda.model.InvokeResponse;
import software.amazon.awssdk.services.lambda.model.LambdaException;

@Slf4j
@Component
public class AwsLambdaUtil {
	
	@Resource(name = "awsCredentialsProvider")
	private AwsCredentialsProvider awsCredentialsProvider;

	/*
	 * 프로젝트 생성
	 * Lambda Function Name : Constant.AWS.LambdaName.PROJECT_CREATE
	 * Parameter :
	 *     teamId : String : 프로젝트 ID
	 *     userid_list : Array<String> : "u" + 사용자 ID
	 *     table_list : Array<String> : 정의된 데이터 테이블 명
	 *     allow_app_instance_type_list : Array<String> : IDE 환경 자원 AWS Instance Name -> 분석 환경 자원
	 *     allow_job_instance_type_list : Array<String> : 분석 실행 자원 AWS Instance Name -> 트레이닝 자원
	 * 
	 * 프로젝트 삭제
	 * Lambda Function Name : Constant.AWS.LambdaName.PROJECT_DELETE
	 * Parameter :
	 *     teamId : String : 프로젝트 ID
	 *     userid_list : Array<String> : "u" + 사용자 ID
	 * 
	 * 프로젝트 수정
	 * Lambda Function Name : Constant.AWS.LambdaName.PROJECT_UPDATE
	 * Parameter :
	 *     teamId : String : 프로젝트 ID
	 *     userid_list : Array<String> : "u" + 사용자 ID
	 *     table_list : Array<String> : 정의된 데이터 테이블 명
	 *     allow_app_instance_type_list : Array<String> : IDE 환경 자원 AWS Instance Name -> 분석 환경 자원
	 *     allow_job_instance_type_list : Array<String> : 분석 실행 자원 AWS Instance Name -> 트레이닝 자원
	 * 
	 */
	
	/**
	 * AWS Lambda API 호출
	 */
	public JSONObject invokeLambda(String functionName, JSONObject params) {
		return invokeLambda(functionName, params.toString());
	}
	
	/**
	 * AWS Lambda API 호출
	 */
	public JSONObject invokeLambda(String functionName, String params) {
		JSONObject result = null;
		try {
			// Need a SdkBytes instance for the payload
			SdkBytes payload = SdkBytes.fromUtf8String(params);

			// Setup an InvokeRequest
			InvokeRequest request = InvokeRequest.builder().functionName(functionName).payload(payload).build();

			LambdaClient awsLambda = LambdaClient.builder().credentialsProvider(awsCredentialsProvider)
					.region(Constant.AWS.REGION).build();

			// Invoke the Lambda function
			InvokeResponse res = awsLambda.invoke(request);
			String value = res.payload().asUtf8String();
			result = new JSONObject(value);
			
			//body를 JSONObject로 변경
			JSONObject body = new JSONObject(result.optString("body","{}"));
			result.put("body", body);

		} catch (LambdaException e) {
			log.warn(e.getMessage());
			result = new JSONObject();
		}
		return result;
	}
}
