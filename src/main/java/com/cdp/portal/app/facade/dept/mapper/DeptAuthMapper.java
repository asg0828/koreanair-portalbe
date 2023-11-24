package com.cdp.portal.app.facade.dept.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cdp.portal.app.facade.dept.model.DeptAuthModel;

@Mapper
public interface DeptAuthMapper {
	Long insertMgrAuth(DeptAuthModel deptAuthMode);
	Long deleteMgrAuthByDeptCode(@Param("deptCode") String deptCode);
	Long deleteMgrAuthByAuthId(@Param("authId") String authId);
	
	Long insertUserAuth(DeptAuthModel deptAuthMode);
	Long deleteUserAuthByDeptCode(@Param("deptCode") String deptCode);
	Long deleteUserAuthByAuthId(@Param("authId") String authId);
}
