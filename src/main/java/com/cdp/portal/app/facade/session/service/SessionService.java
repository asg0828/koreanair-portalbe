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

import com.amazonaws.services.secretsmanager.AWSSecretsManager;
import com.amazonaws.services.secretsmanager.model.AWSSecretsManagerException;
import com.amazonaws.services.secretsmanager.model.GetSecretValueRequest;
import com.amazonaws.services.secretsmanager.model.GetSecretValueResult;
import com.cdp.portal.app.facade.session.dto.SessionDto;
import com.cdp.portal.app.facade.session.dto.SessionRequestDto;
import com.cdp.portal.app.facade.session.repository.SessionRepository;
import com.cdp.portal.common.dto.ApiResDto;
import com.cdp.portal.common.enumeration.CdpPortalError;
import com.cdp.portal.common.error.exception.CdpPortalException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.HttpTransport;
//import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
//import com.kal.stpc.config.MenuByRoleIdConfig;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class SessionService {

	private final SessionRepository sessionRepository;
	private final AWSSecretsManager secretsManagerClient;
//	private final PssService pssService;
//	private final ActionHistoryService actionHistoryService;
//	private final MenuByRoleIdConfig menuByRoleIdConfig;
//	private final MenuService menuService;

	@Value("${api.cdp.client_id}")
	private String CLIENT_ID;

	public ApiResDto<SessionDto> createSession(SessionRequestDto sessionRequest) {

		ApiResDto<SessionDto> result = null;

		// google access token verify
		HttpTransport transport = new NetHttpTransport();
		JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();
		GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
				// Specify the CLIENT_ID of the app that accesses the backend:
				.setAudience(Collections.singletonList(CLIENT_ID))
				// Or, if multiple clients access the backend:
				// .setAudience(Arrays.asList(CLIENT_ID_1, CLIENT_ID_2, CLIENT_ID_3))
				.build();

		GoogleIdToken idToken = null;
		try {
			idToken = verifier.verify(sessionRequest.getGoogleIdToken());
		} catch (GeneralSecurityException | IOException e) {
			log.error(e.getMessage());
//			throw new CustomBusinessException(StatusCodeConstants.INVALID_GOOGLE_ID_TOKEN);
		}
		if (idToken != null) {
			Payload payload = idToken.getPayload();

			String userId = payload.getSubject();

			String email = payload.getEmail();
			boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
			String name = (String) payload.get("name");
			String pictureUrl = (String) payload.get("picture");
			String locale = (String) payload.get("locale");
			String familyName = (String) payload.get("family_name");
			String givenName = (String) payload.get("given_name");

			String employeeNumber = null;
			try {
				
				//HR 정보 조회용
//				getSecretsManager();
				
				employeeNumber = getEmployeeNumberFromGoogle(sessionRequest.getGoogleAccessToken());

				if (ObjectUtils.isEmpty(employeeNumber)) {
					employeeNumber = email.substring(0, email.indexOf('@')).toUpperCase();
				}

				// PSS Portal 정보 조회
//				UserRoleDto userRoleDto = pssService.retrievePssRole(employeeNumber);

				// 권한에 따른 메뉴 할당
//				userRoleDto.setMenus(
//						menuService.createAllowMenusWithParentMenuId(menuByRoleIdConfig.getMenus().get(userRoleDto.getRoleId())));

				SessionDto sessionInfo = SessionDto.builder().memberName(name).email(email).employeeNumber(employeeNumber)
//						.roleId(userRoleDto.getRoleId()).roleName(userRoleDto.getRoleName()).menus(userRoleDto.getMenus())
						.googleIdToken(sessionRequest.getGoogleIdToken()).googleAccessToken(sessionRequest.getGoogleAccessToken())
						.sessionId(userId + ':' + UUID.randomUUID()).build();

				sessionRepository.createSession(sessionInfo.getSessionId(), sessionInfo);

				sessionInfo.setGoogleAccessToken(null);
				sessionInfo.setGoogleIdToken(null);

				result = ApiResDto.success(sessionInfo);

//				actionHistoryService.createActionHistory(null, null, null, ActionCode.LOGIN_SUCCESS.name(), employeeNumber, null);
			} catch (CdpPortalException e) {
				log.error(e.getMessage());
				if (ObjectUtils.isEmpty(employeeNumber)) {
					employeeNumber = email.substring(0, email.indexOf('@')).toUpperCase();
				}

//				actionHistoryService.createActionHistory(null, null, null, ActionCode.LOGIN_FAIL.name(), employeeNumber, null);

				throw new CdpPortalException(e.getError(), e.getMessage());

			} catch (Exception e) {
				log.error(e.getMessage());
				if (ObjectUtils.isEmpty(employeeNumber)) {
					employeeNumber = email.substring(0, email.indexOf('@')).toUpperCase();
				}
				employeeNumber = email.substring(0, email.indexOf('@')).toUpperCase();

//				actionHistoryService.createActionHistory(null, null, null, ActionCode.LOGIN_FAIL.name(), employeeNumber, null);

				throw new CdpPortalException(CdpPortalError.INTERNAL_SERVER_ERROR, e.getMessage());
			}

		} else {
			log.info("Invalid ID token.");
		}

		return result;
	}

	private String getEmployeeNumberFromGoogle(String accessToken) {
		String employeeNumber = "";

		try {
			final String uri = "https://people.googleapis.com/v1/people/me?personFields=externalIds";

			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.set("Authorization", "Bearer " + accessToken);

			HttpEntity<String> entity = new HttpEntity<>("", headers);

			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<String> respEntity = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);

			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode jsonNode = objectMapper.readTree(respEntity.getBody());
			employeeNumber = jsonNode.at("/externalIds/0/value").asText();
		} catch (Exception e) {
			log.error(e.getMessage());
//			throw new CustomBusinessException(StatusCodeConstants.RETRIEVE_EMPLOYEE_NUMBER_FROM_GOOGLE_ERROR);
		}

		return employeeNumber;
	}
	
	//HR 정보 조회 API 권한용 ARN
	public void getSecretsManager() {
		System.out.println("##### getSecretsManager() invoked #####");
        try {
            var getSecretValueRequest = new GetSecretValueRequest()
                    .withSecretId("arn:aws:secretsmanager:ap-northeast-2:993398491107:secret:secret/dlk/dev/cdp-UzdVxY");
            GetSecretValueResult getSecretValueResult = null;
            getSecretValueResult = secretsManagerClient.getSecretValue(getSecretValueRequest);

            if (getSecretValueResult.getSecretString() != null) {
                var secret = getSecretValueResult.getSecretString();
                System.out.println("secret ======> : " + secret);

                var jObject = new JSONObject(secret);
//                this.dbPassword = jObject.getString("password");
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