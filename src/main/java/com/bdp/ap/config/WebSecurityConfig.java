package com.bdp.ap.config;

import javax.annotation.Resource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.Session;
import org.springframework.session.security.SpringSessionBackedSessionRegistry;

import com.bdp.ap.config.security.AuthEntryPoint;
import com.bdp.ap.config.security.AuthFailure;
import com.bdp.ap.config.security.AuthProvider;
import com.bdp.ap.config.security.AuthUserDetailsService;
import com.bdp.ap.config.security.LoginSuccessLoggingAuthenticationSuccessHandler;
import com.bdp.ap.config.security.LogoutHandler;

/**
 * 스프링 시큐리티 설정
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig<S extends Session> extends WebSecurityConfigurerAdapter {

	@Resource(name = "authEntryPoint")
	private AuthEntryPoint authEntryPoint;

	@Resource(name = "authUserDetailsService")
	private AuthUserDetailsService authUserDetailsService;

	// Spring Session
	@Resource
	private FindByIndexNameSessionRepository<S> sessionRepository;

	@Override
	public void configure(WebSecurity web) {
		// 리소스 파일 관련 패턴은 무시하도록 시큐리티 설정
		web.ignoring().antMatchers("/css/**", "/js/**", "/images/**", "/font/**", "/webjars/**","/favicon.ico","/dispatch/**","/summernote/**","/down/**", "/pub/**", "/assets/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// Spring Session
		http.sessionManagement().disable();
				// .maximumSessions(1)    				 // 사용자별 최대세션
				// .maxSessionsPreventsLogin(false)	 // 세션이 사용중이면 이전 사용자를 로그아웃 처리
				// .expiredUrl("/login");
				// .sessionRegistry(sessionRegistry()); // prinicipal 문자열 이름으로 동일한 사용자를 체크

		// 시큐리티 관련 기본셋팅
		http.headers().frameOptions().sameOrigin();

		http.csrf().disable();


		//http.formLogin().loginPage("/login").defaultSuccessUrl("/", true).permitAll();
		http.formLogin().loginPage("/login").defaultSuccessUrl("/", true).loginProcessingUrl("/lgn")
				.usernameParameter("userId").passwordParameter("userPwd")
				.successHandler(loginSuccessHandler()).failureHandler(authFailure()).permitAll();
		http.logout().logoutUrl("/logout").logoutSuccessHandler(logoutHandler()).invalidateHttpSession(true).deleteCookies("JSESSIONID").permitAll();
		//SSO LOGIN 중간 처리
		http.authorizeRequests().antMatchers("/login").permitAll();
		http.authorizeRequests().antMatchers("/ssologin").permitAll();
		//http.addFilterBefore(loginAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

		//외부 시스템 요청은 인증 없이 처리
		http.authorizeRequests().antMatchers("/api/**").permitAll();

		//HR 연동 테스트 임시 허용
		http.authorizeRequests().antMatchers("/test/**").permitAll();

		http.authorizeRequests().antMatchers("/**").access("@authChecker.check(request,authentication)").and().httpBasic().authenticationEntryPoint(authEntryPoint);

	}



	/**
	 * 인증 처리
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(getAuthProvider());
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public AuthProvider getAuthProvider() {
		AuthProvider authProvider = new AuthProvider();
		authProvider.setUserDetailsService(authUserDetailsService);
		authProvider.setHideUserNotFoundExceptions(false);
		return authProvider;
	}

	// Spring Session
	@Bean
	public SpringSessionBackedSessionRegistry<S> sessionRegistry() {
		return new SpringSessionBackedSessionRegistry<>(this.sessionRepository);
	}

	@Bean
	public LoginSuccessLoggingAuthenticationSuccessHandler loginSuccessHandler() {
		return new LoginSuccessLoggingAuthenticationSuccessHandler();
	}

	@Bean
	public LogoutHandler logoutHandler() {
		return new LogoutHandler();
	}

	@Bean
	public AuthFailure authFailure() {
		AuthFailure authFailure = new AuthFailure();
		authFailure.setDefaultFailureUrl("/login");
		return authFailure;
	}

}
