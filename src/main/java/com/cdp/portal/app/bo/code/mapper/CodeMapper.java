package com.cdp.portal.app.bo.code.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cdp.portal.app.bo.code.dto.response.CodeResDto;

@Mapper
public interface CodeMapper {
    
    /**
     * 코드 그룹 ID 전체 목록
     * @param groupId
     * @return
     */
    List<CodeResDto> selectGroupIdAllList(String groupId);

}
