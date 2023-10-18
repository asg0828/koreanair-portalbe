package com.bdp.ap.config.security;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

import com.bdp.ap.common.CommonUtil;
import com.bdp.ap.common.Constant;
import com.bdp.ap.config.security.mapper.SecurityMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AuthProvider extends DaoAuthenticationProvider {

	@Resource(name = "commonUtil")
	private CommonUtil util;

	@Resource(name = "securityMapper")
	private SecurityMapper mapper;

	@Autowired
	private HttpServletRequest request;

	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
		// DB 조회 정보
		AuthUser authUser = (AuthUser) userDetails;

		// 사용자 입력 ID, PW
		// ID : authentication.getName()
		// PW : authentication.getCredentials().toString()

		if (checkUDB(authentication.getName(), authentication.getCredentials().toString())) {
			// 인증 성공
		} else {
			// 인증 실패
			throw new LockedException(mapper.selectLoginMessage(Constant.LoginMessage.UDB_LOGIN_FAIL));
		}

	}

	// UDB 사용자 인증
	// 기존 EAI 인증처리 삭제
	private boolean checkUDB(String userName, String password) {
		boolean result = false;
		String msg = null;
		int check = 0; //로그인 인증 상태값 //비번다름,상태, 등등
		try {

			//TODO
			//로그인 인증처리 개별 구현 영역

		} catch (Exception e) {
			log.warn("사용자 로그인 중 오류 발생");
			log.warn(e.getMessage());
			if (StringUtils.isBlank(msg)) {
				//실패시 해당 메시지 가져온다.
				msg = mapper.selectLoginMessage(Constant.LoginMessage.UDB_CONNECT_FAIL);
			}
		}

		if (check == 0) {
			//인증 성공
			result = true;
		} else {
			//인증 실패
			throw new LockedException(msg);
		}

		return result;
	}
}
