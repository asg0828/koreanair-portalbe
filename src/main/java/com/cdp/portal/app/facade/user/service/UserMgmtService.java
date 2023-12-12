package com.cdp.portal.app.facade.user.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdp.portal.app.facade.user.dto.request.UserMgmtReqDto;
import com.cdp.portal.app.facade.user.dto.response.UserMgmtResDto;
import com.cdp.portal.app.facade.user.mapper.UserMgmtMapper;
import com.cdp.portal.app.facade.user.model.UserModel;
import com.cdp.portal.common.dto.PagingDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserMgmtService {
	private final UserMgmtMapper userMgmtMapper;

    public UserMgmtResDto.UsersResult getUsers(PagingDto pagingDto, UserMgmtReqDto.SearchUser searchDto) {
        pagingDto.setPaging(userMgmtMapper.selectCount(searchDto));

        return UserMgmtResDto.UsersResult.builder()
                .contents(userMgmtMapper.selectAll(pagingDto, searchDto))
                .search(searchDto)
                .page(pagingDto)
                .build();
    }

    public UserMgmtResDto.User getUser(final String userId) {
        return userMgmtMapper.selectById(userId);
    }

    public UserMgmtResDto.UserApldAuth selectApldAuthByUser(final String userId) {
    	return userMgmtMapper.selectApldAuthByUser(userId);
    }

    @Transactional
    public void createUser(UserModel userModel) {
    	userMgmtMapper.insert(userModel);
    }

    @Transactional
    public void changeUserDept(UserModel userModel) {
    	userMgmtMapper.updateDept(userModel);
    }

    @Transactional
    public void upToDateUserLoginDate(UserModel userModel) {
    	userMgmtMapper.updateLastLogin(userModel);
    }
}
