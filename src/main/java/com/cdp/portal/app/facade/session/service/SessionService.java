package com.cdp.portal.app.facade.session.service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Collections;
import java.util.UUID;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.amazonaws.services.secretsmanager.AWSSecretsManager;
import com.amazonaws.services.secretsmanager.model.AWSSecretsManagerException;
import com.amazonaws.services.secretsmanager.model.GetSecretValueRequest;
import com.amazonaws.services.secretsmanager.model.GetSecretValueResult;
import com.cdp.portal.app.facade.menu.dto.request.MenuMgmtReqDto;
import com.cdp.portal.app.facade.menu.service.MenuMgmtMgrService;
import com.cdp.portal.app.facade.menu.service.MenuMgmtUserService;
import com.cdp.portal.app.facade.session.dto.SessionDto;
import com.cdp.portal.app.facade.session.dto.SessionRequestDto;
import com.cdp.portal.app.facade.session.dto.UserHrInfoDto;
import com.cdp.portal.app.facade.session.dto.UserHrInfoDto.HrInfo;
import com.cdp.portal.app.facade.session.repository.SessionRepository;
import com.cdp.portal.app.facade.user.dto.response.UserMgmtResDto;
import com.cdp.portal.app.facade.user.service.UserMgmtService;
import com.cdp.portal.common.dto.ApiResDto;
import com.cdp.portal.common.enumeration.CdpPortalError;
import com.cdp.portal.common.error.exception.CdpPortalException;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.HttpTransport;
//import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
//import com.kal.stpc.config.MenuByRoleIdConfig;
import com.google.gson.Gson;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class SessionService {

	private final SessionRepository sessionRepository;
	private final AWSSecretsManager secretsManagerClient;
	private final UserMgmtService userMgmtService;
	private final MenuMgmtMgrService menuMgmtMgrService;
	private final MenuMgmtUserService menuMgmtUserService;

	@Value("${api.cdp.client_id}")
	private String clientId;

	@Value("${local.hr.url}")
	private String hrApiUrl;

	@Value("${local.hr.id}")
	private String hrApiId;

	@Value("${local.hr.password}")
	private String hrApiPassword;

	@Value("${hr.arn}")
	private String hrArn;

	@Value("${spring.config.activate.on-profile}")
    private String profile;

	public ApiResDto<SessionDto> createSession(SessionRequestDto sessionRequest) {

		ApiResDto<SessionDto> result = null;

		// google access token verify
		HttpTransport transport = new NetHttpTransport();
		JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();
		GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
				// Specify the clientId of the app that accesses the backend:
				.setAudience(Collections.singletonList(clientId))
				// Or, if multiple clients access the backend:
				// .setAudience(Arrays.asList(clientId_1, clientId_2, clientId_3))
				.build();

		GoogleIdToken idToken = null;
		try {
			idToken = verifier.verify(sessionRequest.getGoogleIdToken());
		} catch (GeneralSecurityException | IOException e) {
			log.error(e.getMessage());
			throw new CdpPortalException(CdpPortalError.INVALID_GOOGLE_ID_TOKEN);
		}
		if (idToken != null) {
			Payload payload = idToken.getPayload();

			String subject = payload.getSubject();

			String name = (String) payload.get("name");
			String email = payload.getEmail();
			/*
			boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
			String pictureUrl = (String) payload.get("picture");
			String locale = (String) payload.get("locale");
			String familyName = (String) payload.get("family_name");
			String givenName = (String) payload.get("given_name");
			*/

			try {
				HrInfo hrInfo = getEmployeeHrInfo(email);
//				HrInfo hrInfo = getEmployeeHrInfo("pj.uhlee@kalmate.net");
//				HrInfo hrInfo = getEmployeeHrInfo("ymson@koreanair.com");
//				HrInfo hrInfo = getEmployeeHrInfo("pj.wjjung@kalmate.net");

				if (ObjectUtils.isEmpty(hrInfo)) {
					hrInfo = HrInfo.builder()
					.EEID(email.substring(0, email.indexOf('@')).toLowerCase())
					.build();
				}

				// TODO: 존재 하지 않는 사용자인경우 insert 필요
				UserMgmtResDto.User user = userMgmtService.getUser(hrInfo.getEEID());

				// TODO: HR 팀/부서가 포털 팀/부서와 다를때 업데이트
				if(!ObjectUtils.isEmpty(hrInfo.getTeam_Code()) && !hrInfo.getTeam_Code().equals(user.getDeptCode())){
					// TODO: 팀/부서 코드 업데이트
				}

				if(!ObjectUtils.isEmpty(user.getApldMgrAuthId()))
					user.setMenuByAuthMgr(menuMgmtMgrService.getMenusByAuthMgr(MenuMgmtReqDto.SearchMenuByAuth.builder()
							.authId(user.getApldMgrAuthId())
							.authNm(user.getApldMgrAuthNm())
							.build()));

				if(!ObjectUtils.isEmpty(user.getApldUserAuthId()))
					user.setMenuByAuthUser(menuMgmtUserService.getMenusByAuthUser(MenuMgmtReqDto.SearchMenuByAuth.builder()
							.authId(user.getApldUserAuthId())
							.authNm(user.getApldUserAuthNm())
							.build()));

				SessionDto sessionInfo = SessionDto.builder()
						.userId(user.getUserId())
						.userNm(user.getUserNm())
						.userEmail(email)
						.deptCode(user.getDeptCode())
						.deptNm(user.getDeptNm())
						.upDeptCode(user.getUpDeptCode())
						.upDeptNm(user.getUpDeptNm())
						.apldMgrAuthId(user.getApldMgrAuthId())
						.apldMgrAuthNm(user.getApldMgrAuthNm())
						.apldUserAuthId(user.getApldUserAuthId())
						.apldUserAuthNm(user.getApldUserAuthNm())
						.menuByAuthMgr(user.getMenuByAuthMgr())
						.menuByAuthUser(user.getMenuByAuthUser())
						.googleIdToken(sessionRequest.getGoogleIdToken())
						.googleAccessToken(sessionRequest.getGoogleAccessToken())
						.sessionId(subject + ':' + UUID.randomUUID()).build();

				sessionRepository.createSession(sessionInfo.getSessionId(), sessionInfo);

				sessionInfo.setGoogleAccessToken(null);
				sessionInfo.setGoogleIdToken(null);

				result = ApiResDto.success(sessionInfo);

				// TODO: 마지막 로그인 일시 업데이트
			} catch (CdpPortalException e) {
				log.error(e.getMessage());
				// TODO: 로그인 실패 로그 저장 필요?
				// actionHistoryService.createActionHistory(null, null, null, ActionCode.LOGIN_FAIL.name(), employeeNumber, null);

				throw new CdpPortalException(e.getError(), e.getMessage());

			} catch (Exception e) {
				log.error(e.getMessage());
				// TODO: 로그인 실패 로그 저장 필요?
				// actionHistoryService.createActionHistory(null, null, null, ActionCode.LOGIN_FAIL.name(), employeeNumber, null);

				throw new CdpPortalException(CdpPortalError.INTERNAL_SERVER_ERROR);
			}

		} else {
			log.info("Invalid ID token.");
			throw new CdpPortalException(CdpPortalError.INVALID_GOOGLE_ID_TOKEN);
		}

		return result;
	}

	private HrInfo getEmployeeHrInfo(String email) {
		HrInfo hrInfo = null;
		String uri = null;
		String id = null;
		String password = null;

		try {
			if(!"local".equals(profile)) {
				getSecretsManager();
				System.out.println("getSecretsManager invoked");
			}

			uri = hrApiUrl;
			id = hrApiId;
			password = hrApiPassword;

			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.setBasicAuth(id, password);

			HttpEntity<String> entity = new HttpEntity<>("", headers);

			UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(uri)
	                .queryParam("Email_Address", email);

			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<String> respEntity = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, String.class);

			Gson gson = new Gson();
			UserHrInfoDto.HrInfoResult hrInfoResult = gson.fromJson(respEntity.getBody(), UserHrInfoDto.HrInfoResult.class);

			if(hrInfoResult.getReport_Entry() != null && hrInfoResult.getReport_Entry().size() > 0) {
				hrInfo = hrInfoResult.getReport_Entry().get(0);
			}

		} catch (Exception e) {
			log.error(e.getMessage());
			throw new CdpPortalException(CdpPortalError.RETRIEVE_EMPLOYEE_INFO_ERROR);
		}

		return hrInfo;
	}

	//HR 정보 조회 API 권한용 ARN
	public void getSecretsManager() {
        try {
            var getSecretValueRequest = new GetSecretValueRequest().withSecretId(hrArn);
            GetSecretValueResult getSecretValueResult = null;
            getSecretValueResult = secretsManagerClient.getSecretValue(getSecretValueRequest);

            if (getSecretValueResult.getSecretString() != null) {
                var secret = getSecretValueResult.getSecretString();

                var jObject = new JSONObject(secret);
                this.hrApiUrl = jObject.getString("HTTP URL");
                this.hrApiId = jObject.getString("ID");
                this.hrApiPassword = jObject.getString("Password");

                System.out.println("hrApiUrl : " + hrApiUrl);
                System.out.println("hrApiId : " + hrApiId);
                System.out.println("hrApiPassword : " + hrApiPassword);
            }
        } catch (Exception e) {
            throw new AWSSecretsManagerException("SecretManager Exception Occured : " + e.getMessage());
        }
    }

	public SessionDto getSession(String sessionId) {
		return sessionRepository.getSession(sessionId);
	}

	public boolean logout(String sessionId) {
		return sessionRepository.deleteSession(sessionId);
	}
}