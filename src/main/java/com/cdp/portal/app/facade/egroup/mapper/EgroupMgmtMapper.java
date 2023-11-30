package com.cdp.portal.app.facade.egroup.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cdp.portal.app.facade.egroup.dto.request.EgroupMgmtReqDto;
import com.cdp.portal.app.facade.egroup.dto.response.EgroupMgmtResDto;
import com.cdp.portal.app.facade.egroup.model.EgroupModel;

@Mapper
public interface EgroupMgmtMapper {

	Long insert(EgroupModel egroupModel);

	Long update(EgroupModel egroupModel);

	List<EgroupMgmtResDto.Egroup> selectAll(@Param("search") EgroupMgmtReqDto.SearchEgroup searchDto);

	int selectCount(@Param("search") EgroupMgmtReqDto.SearchEgroup searchDto);

	Long delete(@Param("groupCode") String groupCode);

	Boolean isExistsByGroupCode(@Param("groupCode") String groupCode);

	List<EgroupMgmtResDto.EgroupUser> selectUsersByGroupCode(@Param("groupCode") String groupCode);

}
