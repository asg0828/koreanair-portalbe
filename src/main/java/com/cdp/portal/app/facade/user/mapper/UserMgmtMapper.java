package com.cdp.portal.app.facade.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cdp.portal.app.facade.user.dto.request.UserMgmtReqDto;
import com.cdp.portal.app.facade.user.dto.response.UserMgmtResDto;
import com.cdp.portal.app.facade.user.model.UserModel;
import com.cdp.portal.common.dto.PagingDto;

@Mapper
public interface UserMgmtMapper {
	List<UserMgmtResDto.User> selectAll(@Param("paging") PagingDto pagingDto, @Param("search") UserMgmtReqDto.SearchUser searchDto);
	int selectCount(@Param("search") UserMgmtReqDto.SearchUser searchDto);

	UserMgmtResDto.User selectById(String userId);

	Long insert(UserModel userModel);

	Long updateDept(UserModel userModel);

	Long updateLastLogin(UserModel userModel);

	UserMgmtResDto.UserApldAuth selectApldAuthByUser(@Param("userId") String userId);
}
