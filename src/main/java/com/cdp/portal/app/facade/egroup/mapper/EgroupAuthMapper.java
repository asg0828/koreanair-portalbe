package com.cdp.portal.app.facade.egroup.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cdp.portal.app.facade.egroup.model.EgroupAuthModel;

@Mapper
public interface EgroupAuthMapper {
	Long insertMgrAuth(EgroupAuthModel egroupAuthMode);
	Long deleteMgrAuthByGroupCode(@Param("groupCode") String groupCode);
	Long deleteMgrAuthByAuthId(@Param("authId") String authId);

	Long insertUserAuth(EgroupAuthModel egroupAuthMode);
	Long deleteUserAuthByGroupCode(@Param("groupCode") String groupCode);
	Long deleteUserAuthByAuthId(@Param("authId") String authId);
}
