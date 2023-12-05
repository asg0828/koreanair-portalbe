package com.cdp.portal.app.facade.dept.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import com.cdp.portal.app.facade.dept.dto.request.DeptMgmtReqDto;
import com.cdp.portal.app.facade.dept.dto.response.DeptMgmtResDto;
import com.cdp.portal.app.facade.dept.mapper.DeptAuthMapper;
import com.cdp.portal.app.facade.dept.mapper.DeptMgmtMapper;
import com.cdp.portal.app.facade.dept.model.DeptAuthModel;
import com.cdp.portal.app.facade.dept.model.DeptModel;
import com.cdp.portal.common.constants.CommonConstants;
import com.cdp.portal.common.enumeration.CdpPortalError;
import com.cdp.portal.common.util.SessionScopeUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeptMgmtService {
	private final DeptMgmtMapper deptMgmtMapper;
	private final DeptAuthMapper deptAuthMapper;

    @Transactional
    public void saveDepts(List<DeptMgmtReqDto.SaveDept> dtos) {

    	List<DeptMgmtReqDto.SaveDept> createDepts = dtos.stream()
    			.filter(c -> CommonConstants.CUD_OPERATOR_CREATE.equals(c.getOprtrSe()))
    			.collect(Collectors.toList());

    	List<DeptMgmtReqDto.SaveDept> updateDepts = dtos.stream()
    			.filter(c -> CommonConstants.CUD_OPERATOR_UPDATE.equals(c.getOprtrSe()))
    			.collect(Collectors.toList());

    	List<DeptMgmtReqDto.SaveDept> deleteDepts = dtos.stream()
    			.filter(c -> CommonConstants.CUD_OPERATOR_DELETE.equals(c.getOprtrSe()))
    			.collect(Collectors.toList());

    	createDepts.forEach(c -> {
			Boolean isExists = deptMgmtMapper.isExistsByDeptCode(c.getDeptCode());
			if (isExists) {
			      throw CdpPortalError.DEPT_CODE_DUPLICATED.exception(c.getDeptCode());
			}

    		deptMgmtMapper.insert(DeptModel.builder()
    				.deptCode(c.getDeptCode())
    				.deptNm(c.getDeptNm())
    				.upDeptCode(c.getUpDeptCode())
    				.ordSeq(c.getOrdSeq())
    				.rgstId(SessionScopeUtil.getContextSession().getUserId())    // TODO: 로그인한 사용자 세팅
    	            .modiId(SessionScopeUtil.getContextSession().getUserId())    // TODO: 로그인한 사용자 세팅
    				.build());

    		if(!ObjectUtils.isEmpty(c.getMgrAuthId())) {
    			deptAuthMapper.insertMgrAuth(DeptAuthModel.builder()
    					.deptCode(c.getDeptCode())
    					.authId(c.getMgrAuthId())
    					.rgstId(SessionScopeUtil.getContextSession().getUserId())    // TODO: 로그인한 사용자 세팅
        	            .modiId(SessionScopeUtil.getContextSession().getUserId())    // TODO: 로그인한 사용자 세팅
    					.build());
    		}

    		if(!ObjectUtils.isEmpty(c.getUserAuthId())) {
    			deptAuthMapper.insertUserAuth(DeptAuthModel.builder()
    					.deptCode(c.getDeptCode())
    					.authId(c.getUserAuthId())
    					.rgstId(SessionScopeUtil.getContextSession().getUserId())    // TODO: 로그인한 사용자 세팅
    					.modiId(SessionScopeUtil.getContextSession().getUserId())    // TODO: 로그인한 사용자 세팅
    					.build());
    		}
    	});

    	updateDepts.forEach(c -> {
    		Boolean isExists = deptMgmtMapper.isExistsByDeptCode(c.getDeptCode());
			if (!isExists) {
			      throw CdpPortalError.DEPT_CODE_NOT_FOUND.exception(c.getDeptCode());
			}

    		deptMgmtMapper.update(DeptModel.builder()
	    			.deptCode(c.getDeptCode())
	    			.deptNm(c.getDeptNm())
	    			.upDeptCode(c.getUpDeptCode())
	    			.ordSeq(c.getOrdSeq())
	    			.modiId(SessionScopeUtil.getContextSession().getUserId())    // TODO: 로그인한 사용자 세팅
	    			.build());

    		DeptAuthModel mgrDeptAuthModel = DeptAuthModel.builder()
    				.deptCode(c.getDeptCode())
    				.authId(c.getMgrAuthId())
    				.rgstId(SessionScopeUtil.getContextSession().getUserId())    // TODO: 로그인한 사용자 세팅
    				.modiId(SessionScopeUtil.getContextSession().getUserId())    // TODO: 로그인한 사용자 세팅
    				.build();

    		//수정시 무조건 권한 삭제 후 생성
    		deptAuthMapper.deleteMgrAuthByDeptCode(mgrDeptAuthModel.getDeptCode());

    		if(!ObjectUtils.isEmpty(c.getMgrAuthId())) {
    			deptAuthMapper.insertMgrAuth(mgrDeptAuthModel);
    		}

    		DeptAuthModel userDeptAuthModel = DeptAuthModel.builder()
    				.deptCode(c.getDeptCode())
    				.authId(c.getUserAuthId())
    				.rgstId(SessionScopeUtil.getContextSession().getUserId())    // TODO: 로그인한 사용자 세팅
    				.modiId(SessionScopeUtil.getContextSession().getUserId())    // TODO: 로그인한 사용자 세팅
    				.build();

    		//수정시 무조건 권한 삭제 후 생성
    		deptAuthMapper.deleteUserAuthByDeptCode(userDeptAuthModel.getDeptCode());

    		if(!ObjectUtils.isEmpty(c.getUserAuthId())) {
    			deptAuthMapper.insertUserAuth(userDeptAuthModel);
    		}
    	});

    	deleteDepts.forEach(c -> {
			Boolean isExists = deptMgmtMapper.isExistsByDeptCode(c.getDeptCode());
			if (!isExists) {
			      throw CdpPortalError.DEPT_CODE_NOT_FOUND.exception(c.getDeptCode());
			}

    		deptMgmtMapper.delete(c.getDeptCode());
    		deptAuthMapper.deleteMgrAuthByDeptCode(c.getDeptCode());
    		deptAuthMapper.deleteUserAuthByDeptCode(c.getDeptCode());
    	});

    }

    public DeptMgmtResDto.DeptsResult getDepts(DeptMgmtReqDto.SearchDept searchDto) {

        return DeptMgmtResDto.DeptsResult.builder()
                .contents(deptMgmtMapper.selectAll(searchDto))
                .search(searchDto)
                .build();
    }
}
