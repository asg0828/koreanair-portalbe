package com.bdp.ap.app.user.mapper;

import java.util.List;

import com.bdp.ap.app.user.model.UserCriteria;
import com.bdp.ap.app.user.model.UserModel;
import com.bdp.ap.common.annotation.ConnMapperFirst;

@ConnMapperFirst
public interface UserMapper {
	
	List<UserModel>selectUserList(UserCriteria userCriteria);
	List<UserModel>selectDeptUser(UserCriteria userCriteria);
	List<UserModel>selectPstnUser(UserCriteria userCriteria);
	List<UserModel>selectRankUser(UserCriteria userCriteria);
	List<UserModel>selectDutyUser(UserCriteria userCriteria);
	List<UserModel>selectAuthUser(UserCriteria userCriteria);
		
	int selectUserListCount(UserCriteria userCriteria);
	int selectDeptUserCount(UserCriteria userCriteria);
	int selectPstnUserCount(UserCriteria userCriteria);
	int selectRankUserCount(UserCriteria userCriteria);
	int selectDutyUserCount(UserCriteria userCriteria);
	int selectAuthUserCount(UserCriteria userCriteria);
}
