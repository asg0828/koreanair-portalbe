package com.bdp.ap.app.user.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bdp.ap.app.user.mapper.UserMapper;
import com.bdp.ap.app.user.model.UserCriteria;
import com.bdp.ap.app.user.model.UserModel;

@Service
public class UserService {
	@Resource
	private UserMapper userMapper;
	
	public List<UserModel>selectUserList(UserCriteria userCriteria){
		return userMapper.selectUserList(userCriteria);
	}
	
	public List<UserModel>selectDeptUser(UserCriteria userCriteria){
		return userMapper.selectDeptUser(userCriteria);
	}
	
	public List<UserModel>selectPstnUser(UserCriteria userCriteria){
		return userMapper.selectPstnUser(userCriteria);
	}
	
	public List<UserModel>selectRankUser(UserCriteria userCriteria){
		return userMapper.selectRankUser(userCriteria);
	}
	
	public List<UserModel>selectDutyUser(UserCriteria userCriteria){
		return userMapper.selectDutyUser(userCriteria);
	}
	
	public List<UserModel>selectAuthUser(UserCriteria userCriteria){
		return userMapper.selectAuthUser(userCriteria);
	}
	
	public int selectUserListCount(UserCriteria userCriteria){
		return userMapper.selectUserListCount(userCriteria);
	}
	
	public int selectDeptUserCount(UserCriteria userCriteria){
		return userMapper.selectDeptUserCount(userCriteria);
	}
	
	public int selectPstnUserCount(UserCriteria userCriteria){
		return userMapper.selectPstnUserCount(userCriteria);
	}
	
	public int selectRankUserCount(UserCriteria userCriteria){
		return userMapper.selectRankUserCount(userCriteria);
	}
	
	public int selectDutyUserCount(UserCriteria userCriteria){
		return userMapper.selectDutyUserCount(userCriteria);
	}
	public int selectAuthUserCount(UserCriteria userCriteria){
		return userMapper.selectAuthUserCount(userCriteria);
	}
	
	
}
