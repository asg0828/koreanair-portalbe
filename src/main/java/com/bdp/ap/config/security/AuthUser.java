package com.bdp.ap.config.security;

import java.util.Collection;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.bdp.ap.app.member.model.MemberModel;

/**
 * 스프링 시큐리티 로그인 처리를 위한 커스텀 UserDetails 클래스
 */
public class AuthUser implements UserDetails {

	private static final long serialVersionUID = 1L;

	private String username;
	private Collection<? extends GrantedAuthority> authorities;

	// 계정 만료
	private boolean isAccountNonExpired;
	// 계정 잠금
	private boolean isAccountNonLocked;
	// 비번 만료
	private boolean isCredentialsNonExpired;
	// 계정 활성 여부
	private boolean isEnabled;

	//SSO check 여부
	private boolean isSso = false;

	// 사용자 정보
	private MemberModel memberModel;

	//회사코드
	private String companyCd;
		
	public AuthUser(MemberModel memberModel, Collection<? extends GrantedAuthority> authorities, boolean isSso, String companyCd) {
		this.username = memberModel.getUserId();
		this.authorities = authorities;
		//계정 만료 :  - 접속일이 start_dt와 end_dt 사이에 있는지 : expired_yn
		this.isAccountNonExpired = StringUtils.equals("N",memberModel.getExpiredYn());
		// 계정 잠금 :  - 최종 로그인 180일 경과 : lock_yn
		this.isAccountNonLocked = StringUtils.equals("N",memberModel.getLockYn());
		// 비번 만료 : 비번 관리하지 않음
		this.isCredentialsNonExpired = true;
		// 계정 활성 여부
		this.isEnabled = StringUtils.equals("Y",memberModel.getUseYn());

		//SSO 인증 여부
		this.isSso = isSso;

		// 사용자 정보
		this.memberModel = memberModel;
		
		//회사코드
		this.companyCd=companyCd;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof AuthUser) {
			return this.username.equals(((AuthUser)obj).username);
		}
		return false;
	}

	@Override
	public int hashCode() {
		return this.username.hashCode();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// 권한
		return this.authorities;
	}

	@Override
	public String getPassword() {
		return this.username;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// 계정 만료 - 접속일이 start_dt와 end_dt 사이에 있는지 : expired_yn
		return this.isAccountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		// 계정 잠금 - 최종 로그인 180일 경과 : lock_yn
		return this.isAccountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// 비번 만료 여부 : 없음
		return this.isCredentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		// 계정 활성 여부 : use_yn
		return this.isEnabled;
	}

	public boolean isSso() {
		//SSO 인증 여부
		return this.isSso;
	}

	public MemberModel getMemberModel() {
		// 사용자 정보
		return this.memberModel;
	}
	
	public String getCompanyCd() {
		//회사코드
		return this.companyCd;
	}
}
