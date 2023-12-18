package com.cdp.portal.common.mapper;

import java.util.Map;

import com.cdp.portal.config.OneidMapper;

@OneidMapper
public interface IdMapper {
	String selectId(Map<String,String> param);
	String selectTimeSeq(Map<String,String> param);
	int insertId(Map<String,String> param);
}
