package com.bdp.ap.config.security;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.bdp.ap.app.member.model.MemberModel;
import com.bdp.ap.common.Constant;
import com.bdp.ap.config.security.mapper.SecurityMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * 스프링 시큐리티의 username에 따른 사용자를 조회하기 위한 커스텀 서비스 클래스
 */
@Slf4j
@Component
public class AuthUserDetailsService implements UserDetailsService {

	@Resource(name = "securityMapper")
	private SecurityMapper mapper;

	@Autowired
	private HttpServletRequest request;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AuthUser authUser = null;

		// login.jsp 에서 SSO 갔다오고 난 후에 /ssologin 에서 SSO 인증 확인하고 login.jsp 에서 바로 form submit 호출 하여 스프링 시큐리티 시작
		boolean isSso = StringUtils.equalsIgnoreCase("Y", request.getParameter("sso"));
		String companyCd = request.getParameter("companyCd");
		if (StringUtils.isNotBlank(username)) {
			// 사용자 정보 DB 조회
			MemberModel member = mapper.selectUser(username);
			if (member != null) {
				// 계정 활성 : useYn
				if (StringUtils.equals("N", member.getUseYn())) {
					throw new UsernameNotFoundException(mapper.selectLoginMessage(Constant.LoginMessage.ACCOUNT_DISABLE));
				}
				//계정 잠김 : lastLogDt
				if (StringUtils.equals("Y", member.getLockYn())) {
					throw new UsernameNotFoundException(mapper.selectLoginMessage(Constant.LoginMessage.ACCOUNT_LOCK));
				}
				// 계정 만료 : startDt ~ endDt
				if (StringUtils.equals("Y", member.getExpiredYn())) {
					throw new UsernameNotFoundException(mapper.selectLoginMessage(Constant.LoginMessage.ACCOUNT_EXPIRE));
				}
				// 권한
				if (StringUtils.equals("Y",member.getAuthUseYn()) && StringUtils.isNotBlank(member.getAuthId())) {
					Set<GrantedAuthority> authorities = new HashSet<>();
					authorities.add(new SimpleGrantedAuthority(StringUtils.joinWith("_", "ROLE",member.getAuthId())));
					authUser = new AuthUser(member, authorities, isSso, companyCd);
				} else {
					throw new UsernameNotFoundException(mapper.selectLoginMessage(Constant.LoginMessage.AUTH_FAIL));
				}
			}
		}
		if (authUser == null) {
			throw new UsernameNotFoundException(mapper.selectLoginMessage(Constant.LoginMessage.USER_NOT_FOUND));
		}
		return authUser;
	}

}
