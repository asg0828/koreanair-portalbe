package com.cdp.portal.app.facade.user.service;

import java.util.Objects;

import org.springframework.stereotype.Service;

import com.cdp.portal.app.facade.user.dto.request.UserMgmtReqDto;
import com.cdp.portal.app.facade.user.dto.response.UserMgmtResDto;
import com.cdp.portal.app.facade.user.mapper.UserMgmtMapper;
import com.cdp.portal.common.dto.PagingDto;
import com.cdp.portal.common.enumeration.CdpPortalError;

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
    	UserMgmtResDto.User user = userMgmtMapper.selectById(userId);
        if (Objects.isNull(user)) {
            throw CdpPortalError.USER_NOT_EXISTS.exception(userId);
        }

        return user;
    }
}
