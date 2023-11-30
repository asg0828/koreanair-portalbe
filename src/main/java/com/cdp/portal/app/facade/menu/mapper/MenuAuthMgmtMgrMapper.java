package com.cdp.portal.app.facade.menu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cdp.portal.app.facade.menu.dto.response.MenuAuthMgmtResDto;
import com.cdp.portal.app.facade.menu.model.MenuAuthModel;

@Mapper
public interface MenuAuthMgmtMgrMapper {
	List<MenuAuthMgmtResDto.MenuAuthMgmt> selectByAuthId(@Param("authId") String authId);
	Long insert(MenuAuthModel menuAuthModel);
	Long delete(@Param("authId") String authId);
}
