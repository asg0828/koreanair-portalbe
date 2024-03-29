package com.cdp.portal.app.facade.auth.service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdp.portal.app.facade.auth.dto.request.AuthMgmtReqDto;
import com.cdp.portal.app.facade.auth.dto.response.AuthMgmtResDto;
import com.cdp.portal.app.facade.auth.mapper.AuthMgmtMgrMapper;
import com.cdp.portal.app.facade.auth.model.AuthModel;
import com.cdp.portal.app.facade.dept.mapper.DeptAuthMapper;
import com.cdp.portal.app.facade.egroup.mapper.EgroupAuthMapper;
import com.cdp.portal.common.IdUtil;
import com.cdp.portal.common.enumeration.CdpPortalError;
import com.cdp.portal.common.util.SessionScopeUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthMgmtMgrService {
	private final IdUtil idUtil;
	private final AuthMgmtMgrMapper authMgmtMgrMapper;
	private final DeptAuthMapper deptAuthMapper;
	private final EgroupAuthMapper egroupAuthMapper;

    @Transactional
    public void createAuth(AuthMgmtReqDto.CreateAuth dto) {
    	final String authId = idUtil.getAuthMgrId();

        AuthModel authModel = AuthModel.builder()
        		.authId(authId)
        		.authNm(dto.getAuthNm())
        		.authDsc(dto.getAuthDsc())
                .rgstId(SessionScopeUtil.getContextSession().getUserId())    // TODO: 로그인한 사용자 세팅
                .modiId(SessionScopeUtil.getContextSession().getUserId())    // TODO: 로그인한 사용자 세팅
                .build();

        authMgmtMgrMapper.insert(authModel);
    }

    @Transactional(readOnly = true)
	public List<AuthMgmtResDto.Auth> getAuthMgmtMgrs(){
		return authMgmtMgrMapper.selectAll().stream().map(c -> AuthMgmtResDto.Auth.builder()
				.authId(c.getAuthId())
				.authNm(c.getAuthNm())
				.authDsc(c.getAuthDsc())
				.build()
				).collect(Collectors.toList());
	}

	@Transactional
    public void updateAuth(final String authId, AuthMgmtReqDto.UpdateAuth dto) {
		AuthModel auth = authMgmtMgrMapper.selectById(authId);
        if (Objects.isNull(auth)) {
            throw CdpPortalError.AUTH_NOT_FOUND.exception(authId);
        }

        AuthModel authModel = AuthModel.builder()
        		.authId(authId)
        		.authNm(dto.getAuthNm())
        		.authDsc(dto.getAuthDsc())
                .modiId(SessionScopeUtil.getContextSession().getUserId())    // TODO: 로그인한 사용자 세팅
                .build();

        authMgmtMgrMapper.update(authModel);
    }

	@Transactional
    public void deleteAuth(String authId) {
		authMgmtMgrMapper.delete(authId);
		deptAuthMapper.deleteMgrAuthByAuthId(authId);
		authMgmtMgrMapper.deleteMgrMenuByAuthId(authId);
		egroupAuthMapper.deleteMgrAuthByAuthId(authId);
    }
}
