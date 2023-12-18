package com.cdp.portal.app.facade.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cdp.portal.app.facade.user.dto.request.UserQuickMenuReqDto;
import com.cdp.portal.app.facade.user.dto.response.UserQuickMenuResDto;
import com.cdp.portal.app.facade.user.model.UserQuickMenuModel;

@Mapper
public interface UserQuickMenuUserMapper {

	Long insert(UserQuickMenuModel quickMenuModel);

	Long delete(@Param("userId") String userId, @Param("menuId") String menuId);

	Boolean isExistsByUserIdAndMenuId(@Param("userId") String userId, @Param("menuId") String menuId);

	List<UserQuickMenuResDto.QuickMenu> selectByAuthIdUserQuickMenus(@Param("search") UserQuickMenuReqDto.SearchQuickMenuByAuthUser searchDto);

}
