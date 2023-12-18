package com.cdp.portal.app.facade.oneid.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.cdp.portal.app.facade.menu.dto.request.MenuMgmtReqDto;
import com.cdp.portal.app.facade.menu.dto.response.MenuMgmtResDto;
import com.cdp.portal.config.OneidMapper;

@Repository
@OneidMapper
public interface OneIdLogMapper {

	List<MenuMgmtResDto.MenuMgmt> selectAll(@Param("search") MenuMgmtReqDto.SearchMenu searchDto);

	int selectCount(@Param("search") MenuMgmtReqDto.SearchMenu searchDto);

}
