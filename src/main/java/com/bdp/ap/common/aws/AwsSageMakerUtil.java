package com.bdp.ap.common.aws;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.bdp.ap.common.CommonUtil;
import com.bdp.ap.common.Constant;
import com.bdp.ap.config.props.AwsProps;

import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.services.sagemaker.SageMakerClient;
import software.amazon.awssdk.services.sagemaker.model.AppDetails;
import software.amazon.awssdk.services.sagemaker.model.AppSortKey;
import software.amazon.awssdk.services.sagemaker.model.ListAppsRequest;
import software.amazon.awssdk.services.sagemaker.model.ListAppsResponse;
import software.amazon.awssdk.services.sagemaker.model.ListTrainingJobsRequest;
import software.amazon.awssdk.services.sagemaker.model.ListTrainingJobsResponse;
import software.amazon.awssdk.services.sagemaker.model.ListUserProfilesRequest;
import software.amazon.awssdk.services.sagemaker.model.ListUserProfilesResponse;
import software.amazon.awssdk.services.sagemaker.model.SortBy;
import software.amazon.awssdk.services.sagemaker.model.SortOrder;
import software.amazon.awssdk.services.sagemaker.model.TrainingJobSummary;
import software.amazon.awssdk.services.sagemaker.model.UserProfileDetails;
import software.amazon.awssdk.services.sagemaker.model.UserProfileSortKey;

@Slf4j
@Component
public class AwsSageMakerUtil {

	@Resource(name = "awsCredentialsProvider")
	private AwsCredentialsProvider awsCredentialsProvider;
	
	@Resource(name="awsProps")
	private AwsProps awsProps;
	
	@Resource(name="commonUtil")
	private CommonUtil commonUtil;
	

	/**
	 * Create SageMaker Client
	 * @return
	 */
	private SageMakerClient getSageMakerClient() {
		return SageMakerClient.builder().credentialsProvider(awsCredentialsProvider).region(Constant.AWS.REGION).build();
	}
	
	/**
	 * get list apps : IDE 환경 자원 -> 분석 환경 자원
	 */
	public JSONObject listApps(JSONObject params) {
		JSONObject result = new JSONObject();
		SageMakerClient client = getSageMakerClient();
		ListAppsRequest.Builder builder = ListAppsRequest.builder();
		if (StringUtils.isNotBlank(params.optString("nextToken"))) {
			builder.nextToken(params.optString("nextToken"));
		}
		if (params.optInt("pageSize") != 0) {
			builder.maxResults(params.optInt("pageSize"));
		} else {
			builder.maxResults(awsProps.getPageSize());
		}
		if (StringUtils.isNotBlank(params.optString("userProfileNameEquals"))) {
			builder.userProfileNameEquals(params.optString("userProfileNameEquals"));
		}
		builder.sortBy(AppSortKey.CREATION_TIME);
		builder.sortOrder(SortOrder.DESCENDING);
		builder.domainIdEquals(awsProps.getSagemakerDomainId());
		ListAppsRequest request = builder.build();
		
		ListAppsResponse response = client.listApps(request);
		result.put("nextToken", response.nextToken());
		List<AppDetails> list = response.apps();
		for (AppDetails detail : list) {
			log.debug("AppDetail - name : {}",detail.appName());
		}
		return result;
	}
	
	/**
	 * get list training jobs : 분석 실행 자원 -> 트레이닝 자원
	 */
	public JSONObject listJobs(JSONObject params) {
		JSONObject result = new JSONObject();
		SageMakerClient client = getSageMakerClient();
		ListTrainingJobsRequest.Builder builder = ListTrainingJobsRequest.builder();
		if (StringUtils.isNotBlank(params.optString("nextToken"))) {
			builder.nextToken(params.optString("nextToken"));
		}
		if (params.optInt("pageSize") != 0) {
			builder.maxResults(params.optInt("pageSize"));
		} else {
			builder.maxResults(awsProps.getPageSize());
		}
		if (StringUtils.isNotBlank(params.optString("nameContains"))) {
			builder.nameContains(params.optString("nameContains"));
		}
		if (StringUtils.isNotBlank(params.optString("creationTimeAfter"))) {
			builder.creationTimeAfter(commonUtil.getInstant(params.optString("creationTimeAfter"),Constant.DATE_FORMAT.DATE, awsProps.getTimeZone()));
		}
		if (StringUtils.isNotBlank(params.optString("creationTimeBefore"))) {
			builder.creationTimeBefore(commonUtil.getInstant(params.optString("creationTimeBefore"),Constant.DATE_FORMAT.DATE, awsProps.getTimeZone()));
		}
		builder.sortBy(SortBy.CREATION_TIME);
		builder.sortOrder(SortOrder.DESCENDING);
		ListTrainingJobsRequest request = builder.build();
		ListTrainingJobsResponse response = client.listTrainingJobs(request);
		result.put("nextToken", response.nextToken());
		List<TrainingJobSummary> list = response.trainingJobSummaries();
		for (TrainingJobSummary summary : list) {
			log.debug("TrainingJob - name : {}",summary.trainingJobName());
		}
		return result;
	}
	
	/**
	 * get list user profiles
	 */
	public JSONObject listUserProfiles(JSONObject params) {
		JSONObject result = new JSONObject();
		SageMakerClient client = getSageMakerClient();
		ListUserProfilesRequest.Builder builder = ListUserProfilesRequest.builder();
		if (StringUtils.isNotBlank(params.optString("nextToken"))) {
			builder.nextToken(params.optString("nextToken"));
		}
		if (params.optInt("pageSize") != 0) {
			builder.maxResults(params.optInt("pageSize"));
		} else {
			builder.maxResults(awsProps.getPageSize());
		}
		builder.sortBy(UserProfileSortKey.CREATION_TIME);
		builder.sortOrder(SortOrder.DESCENDING);
		builder.domainIdEquals(awsProps.getSagemakerDomainId());
		ListUserProfilesRequest request = builder.build();
		ListUserProfilesResponse response = client.listUserProfiles(request);
		result.put("nextToken", response.nextToken());
		List<UserProfileDetails> list = response.userProfiles();
		for (UserProfileDetails detail : list) {
			log.debug("UserProfile - name : {}",detail.userProfileName());
		}
		return result;
	}
	
	
}
