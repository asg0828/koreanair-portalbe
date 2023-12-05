package com.cdp.portal.app.facade.menu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cdp.portal.app.facade.menu.dto.request.MenuMgmtReqDto;
import com.cdp.portal.app.facade.menu.dto.response.MenuMgmtResDto;
import com.cdp.portal.app.facade.menu.model.MenuModel;

@Mapper
public interface MenuMgmtMgrMapper {

	Long insert(MenuModel menuModel);

	Long update(MenuModel menuModel);

	List<MenuMgmtResDto.MenuMgmt> selectAll(@Param("search") MenuMgmtReqDto.SearchMenu searchDto);

	int selectCount(@Param("search") MenuMgmtReqDto.SearchMenu searchDto);

	Long delete(@Param("menuId") String menuId);

	Boolean isExistsByMenuId(@Param("menuId") String menuId);

	List<MenuMgmtResDto.MenuMgmt> selectByAuthIdMgrMenus(@Param("search") MenuMgmtReqDto.SearchMenuByAuth searchDto);

	List<MenuMgmtResDto.MenuMgmt> selectByAuthIdUserMenus(@Param("search") MenuMgmtReqDto.SearchMenuByAuth searchDto);
}
