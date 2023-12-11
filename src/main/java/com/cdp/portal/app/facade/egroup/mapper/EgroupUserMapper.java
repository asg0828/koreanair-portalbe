package com.cdp.portal.app.facade.egroup.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cdp.portal.app.facade.egroup.dto.response.EgroupMgmtResDto;
import com.cdp.portal.app.facade.egroup.model.EgroupModel;
import com.cdp.portal.app.facade.egroup.model.EgroupUserModel;

@Mapper
public interface EgroupUserMapper {

	List<EgroupMgmtResDto.EgroupUser> selectByGroupCode(@Param("groupCode") String groupCode);

	Long update(EgroupUserModel egroupUserModel);

//	Long updateGroupCodeToNullByUserId(EgroupUserModel egroupUserModel);

	Long updateGroupCodeToNull(EgroupUserModel egroupUserModel);

	Long updateBeforeGroupCode(@Param("userId") String userId);

	Long updateGroupCodeToNullByNotInUsers(EgroupUserModel egroupUserModel);

}
