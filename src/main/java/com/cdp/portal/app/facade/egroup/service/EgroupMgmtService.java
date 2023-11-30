package com.cdp.portal.app.facade.egroup.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import com.cdp.portal.app.facade.egroup.dto.request.EgroupMgmtReqDto;
import com.cdp.portal.app.facade.egroup.dto.request.EgroupMgmtReqDto.EgroupUserUpdate;
import com.cdp.portal.app.facade.egroup.dto.response.EgroupMgmtResDto;
import com.cdp.portal.app.facade.egroup.mapper.EgroupAuthMapper;
import com.cdp.portal.app.facade.egroup.mapper.EgroupMgmtMapper;
import com.cdp.portal.app.facade.egroup.mapper.EgroupUserMapper;
import com.cdp.portal.app.facade.egroup.model.EgroupAuthModel;
import com.cdp.portal.app.facade.egroup.model.EgroupModel;
import com.cdp.portal.app.facade.egroup.model.EgroupUserModel;
import com.cdp.portal.common.IdUtil;
import com.cdp.portal.common.constants.CommonConstants;
import com.cdp.portal.common.enumeration.CdpPortalError;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EgroupMgmtService {
	private final EgroupUserService egroupUserService;

	private final IdUtil idUtil;
	private final EgroupMgmtMapper egroupMgmtMapper;
	private final EgroupAuthMapper egroupAuthMapper;
	private final EgroupUserMapper egroupUserMapper;

	@Transactional
    public void saveEgroupInfos(EgroupMgmtReqDto.EgroupInfos dto) {
		saveEgroup(dto.getSaveEgroup());
		egroupUserService.update(dto.getEgroupUserUpdate());
	}

    @Transactional
    public void saveEgroup(List<EgroupMgmtReqDto.SaveEgroup> dtos) {

    	List<EgroupMgmtReqDto.SaveEgroup> createEgroups = dtos.stream()
    			.filter(c -> CommonConstants.CUD_OPERATOR_CREATE.equals(c.getOprtrSe()))
    			.collect(Collectors.toList());

    	List<EgroupMgmtReqDto.SaveEgroup> updateEgroups = dtos.stream()
    			.filter(c -> CommonConstants.CUD_OPERATOR_UPDATE.equals(c.getOprtrSe()))
    			.collect(Collectors.toList());

    	List<EgroupMgmtReqDto.SaveEgroup> deleteEgroups = dtos.stream()
    			.filter(c -> CommonConstants.CUD_OPERATOR_DELETE.equals(c.getOprtrSe()))
    			.collect(Collectors.toList());

    	createEgroups.forEach(c -> {
    		final String groupCode = idUtil.getGroupCode();

    		egroupMgmtMapper.insert(EgroupModel.builder()
    				.groupCode(groupCode)
    				.groupNm(c.getGroupNm())
    				.upGroupCode(c.getUpGroupCode())
    				.ordSeq(c.getOrdSeq())
    				.rgstId("admin")    // TODO: 로그인한 사용자 세팅
    	            .modiId("admin")    // TODO: 로그인한 사용자 세팅
    				.build());

    		if(!ObjectUtils.isEmpty(c.getMgrAuthId())) {
    			egroupAuthMapper.insertMgrAuth(EgroupAuthModel.builder()
    					.groupCode(groupCode)
    					.authId(c.getMgrAuthId())
    					.rgstId("admin")    // TODO: 로그인한 사용자 세팅
        	            .modiId("admin")    // TODO: 로그인한 사용자 세팅
    					.build());
    		}

    		if(!ObjectUtils.isEmpty(c.getUserAuthId())) {
    			egroupAuthMapper.insertUserAuth(EgroupAuthModel.builder()
    					.groupCode(groupCode)
    					.authId(c.getUserAuthId())
    					.rgstId("admin")    // TODO: 로그인한 사용자 세팅
    					.modiId("admin")    // TODO: 로그인한 사용자 세팅
    					.build());
    		}
    	});

    	updateEgroups.forEach(c -> {
    		Boolean isExists = egroupMgmtMapper.isExistsByGroupCode(c.getGroupCode());
			if (!isExists) {
			      throw CdpPortalError.DEPT_CODE_NOT_FOUND.exception(c.getGroupCode());
			}

    		egroupMgmtMapper.update(EgroupModel.builder()
	    			.groupCode(c.getGroupCode())
	    			.groupNm(c.getGroupNm())
	    			.upGroupCode(c.getUpGroupCode())
	    			.ordSeq(c.getOrdSeq())
	    			.modiId("admin")    // TODO: 로그인한 사용자 세팅
	    			.build());

    		//수정시 무조건 권한 삭제 후 생성
    		egroupAuthMapper.deleteMgrAuthByGroupCode(c.getGroupCode());

    		if(!ObjectUtils.isEmpty(c.getMgrAuthId())) {
    			egroupAuthMapper.insertMgrAuth(EgroupAuthModel.builder()
        				.groupCode(c.getGroupCode())
        				.authId(c.getMgrAuthId())
        				.rgstId("admin")    // TODO: 로그인한 사용자 세팅
        				.modiId("admin")    // TODO: 로그인한 사용자 세팅
        				.build());
    		}

    		EgroupAuthModel userEgroupAuthModel = EgroupAuthModel.builder()
    				.groupCode(c.getGroupCode())
    				.authId(c.getUserAuthId())
    				.rgstId("admin")    // TODO: 로그인한 사용자 세팅
    				.modiId("admin")    // TODO: 로그인한 사용자 세팅
    				.build();

    		//수정시 무조건 권한 삭제 후 생성
    		egroupAuthMapper.deleteUserAuthByGroupCode(userEgroupAuthModel.getGroupCode());

    		if(!ObjectUtils.isEmpty(c.getUserAuthId())) {
    			egroupAuthMapper.insertUserAuth(userEgroupAuthModel);
    		}
    	});

    	deleteEgroups.forEach(c -> {
			Boolean isExists = egroupMgmtMapper.isExistsByGroupCode(c.getGroupCode());
			if (!isExists) {
			      throw CdpPortalError.DEPT_CODE_NOT_FOUND.exception(c.getGroupCode());
			}

    		egroupMgmtMapper.delete(c.getGroupCode());
    		egroupAuthMapper.deleteMgrAuthByGroupCode(c.getGroupCode());
    		egroupAuthMapper.deleteUserAuthByGroupCode(c.getGroupCode());
    		egroupUserMapper.updateGroupCodeToNull(EgroupUserModel.builder()
    				.groupCode(c.getGroupCode())
    				.modiId("admin")    // TODO: 로그인한 사용자 세팅
    				.build());
    	});

    }

//    @Transactional
//    public void egroupUserUpdate(List<EgroupUserUpdate> dtos) {
//    	dtos.forEach(c -> {
//    		egroupUserMapper.update(EgroupUserModel.builder()
//    	    		.groupCode(c.getGroupCode())
//    	    		.userIds(c.getUserIds())
//    	    		.modiId("admin")    // TODO: 로그인한 사용자 세팅
//    	    		.build());
//    	});
//    }

    public EgroupMgmtResDto.EgroupsResult getEgroups(EgroupMgmtReqDto.SearchEgroup searchDto) {

        return EgroupMgmtResDto.EgroupsResult.builder()
                .contents(egroupMgmtMapper.selectAll(searchDto))
                .search(searchDto)
                .build();
    }
}
