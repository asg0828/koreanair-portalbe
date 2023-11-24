package com.cdp.portal.app.facade.auth.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cdp.portal.app.facade.auth.model.AuthModel;

@Mapper
public interface AuthMgmtMgrMapper {

	Long insert(AuthModel authModel);

	List<AuthModel> selectAll();

	AuthModel selectById(@Param("authId") String authId);

	Long update(AuthModel authModel);

	Long delete(@Param("authId") String authId);

}
