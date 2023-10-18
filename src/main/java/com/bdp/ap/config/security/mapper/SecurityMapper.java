package com.bdp.ap.config.security.mapper;

import java.util.Map;

import com.bdp.ap.app.member.model.MemberModel;
import com.bdp.ap.app.menu.model.MenuModel;
import com.bdp.ap.common.annotation.ConnMapperFirst;

@ConnMapperFirst
public interface SecurityMapper {

	MemberModel selectUser(String userId);

	int updateLastLogDt(String userId);

	String selectLoginMessage(String codeId);

	MenuModel selectMenuWithAuth(Map<String,String> param);
}
