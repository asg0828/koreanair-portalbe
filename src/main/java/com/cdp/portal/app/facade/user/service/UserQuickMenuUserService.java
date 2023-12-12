package com.cdp.portal.app.facade.user.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdp.portal.app.facade.user.dto.request.UserQuickMenuReqDto;
import com.cdp.portal.app.facade.user.dto.response.UserMgmtResDto;
import com.cdp.portal.app.facade.user.dto.response.UserQuickMenuResDto;
import com.cdp.portal.app.facade.user.mapper.UserQuickMenuUserMapper;
import com.cdp.portal.app.facade.user.model.UserQuickMenuModel;
import com.cdp.portal.common.enumeration.CdpPortalError;
import com.cdp.portal.common.util.SessionScopeUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserQuickMenuUserService {
	private final UserQuickMenuUserMapper userQuickMenuUserMapper;
	private final UserMgmtService userMgmtService;

	@Transactional(readOnly = true)
    public UserQuickMenuResDto.QuickMenuByUser getUserQuickMenus(final String userId) {
		UserMgmtResDto.UserApldAuth userApldAuth = userMgmtService.selectApldAuthByUser(userId);

		UserQuickMenuReqDto.SearchQuickMenuByAuthUser search = UserQuickMenuReqDto.SearchQuickMenuByAuthUser.builder()
			.authId(userApldAuth.getApldUserAuthId())
			.userId(userId)
			.build();

        return UserQuickMenuResDto.QuickMenuByUser.builder()
        		.menus(userQuickMenuUserMapper.selectByAuthIdUserQuickMenus(search))
        		.search(search)
        		.build();
    }

	@Transactional
	public void createUserQuickMenu(String userId, UserQuickMenuReqDto.CreateQuickMenu dto) {
		Boolean isExists = userQuickMenuUserMapper.isExistsByUserIdAndMenuId(userId, dto.getMenuId());
        if (isExists) {
            throw CdpPortalError.USER_QUICK_MENU_DUPLICATED.exception();
        }

		userQuickMenuUserMapper.insert(UserQuickMenuModel.builder()
				.userId(userId)
				.menuId(dto.getMenuId())
				.rgstId(SessionScopeUtil.getContextSession().getUserId())
				.modiId(SessionScopeUtil.getContextSession().getUserId())
				.build());

	}

	@Transactional
	public void deleteUserQuickMenu(String userId, String menuId) {
		Boolean isExists = userQuickMenuUserMapper.isExistsByUserIdAndMenuId(userId, menuId);
        if (!isExists) {
            throw CdpPortalError.USER_QUICK_MENU_NOT_EXISTS.exception();
        }

        userQuickMenuUserMapper.delete(userId, menuId);
	}
}
