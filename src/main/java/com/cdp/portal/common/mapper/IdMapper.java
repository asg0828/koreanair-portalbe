package com.cdp.portal.common.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IdMapper {
	String selectId(Map<String,String> param);
	String selectTimeSeq(Map<String,String> param);
	int insertId(Map<String,String> param);
}
