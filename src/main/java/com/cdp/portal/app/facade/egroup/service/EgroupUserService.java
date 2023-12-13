package com.cdp.portal.app.facade.egroup.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdp.portal.app.facade.egroup.dto.request.EgroupMgmtReqDto.EgroupUserUpdate;
import com.cdp.portal.app.facade.egroup.dto.response.EgroupMgmtResDto;
import com.cdp.portal.app.facade.egroup.mapper.EgroupUserMapper;
import com.cdp.portal.app.facade.egroup.model.EgroupUserModel;
import com.cdp.portal.common.util.SessionScopeUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EgroupUserService {
	private final EgroupUserMapper egroupUserMapper;

	@Transactional(readOnly = true)
	public List<EgroupMgmtResDto.EgroupUser> getEgroupUsers(final String groupCode){
		return egroupUserMapper.selectByGroupCode(groupCode).stream().map(c -> EgroupMgmtResDto.EgroupUser.builder()
				.userId(c.getUserId())
				.userNm(c.getUserNm())
				.groupCode(c.getGroupCode())
				.deptCode(c.getDeptCode())
				.deptNm(c.getDeptNm())
				.build()
				).collect(Collectors.toList());
	}

	@Transactional
    public void update(List<EgroupUserUpdate> dtos) {
		dtos.forEach(c -> {
			Arrays.stream(c.getUserIds()).forEach(s -> {
				egroupUserMapper.updateBeforeGroupCode(s);
			});

			// 사용자 기존 권한 그룹 NULL 업데이트
			egroupUserMapper.updateGroupCodeToNullByNotInUsers(EgroupUserModel.builder()
					.groupCode(c.getGroupCode())
    	    		.userIds(c.getUserIds())
    	    		.modiId(SessionScopeUtil.getContextSession().getUserId())
    	    		.build());

    		egroupUserMapper.update(EgroupUserModel.builder()
    	    		.groupCode(c.getGroupCode())
    	    		.userIds(c.getUserIds())
    	    		.modiId(SessionScopeUtil.getContextSession().getUserId())
    	    		.build());
    	});
    }
}
