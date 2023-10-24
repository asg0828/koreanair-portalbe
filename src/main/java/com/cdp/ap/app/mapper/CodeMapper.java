package com.cdp.ap.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cdp.ap.app.dto.response.CodeResDto;

@Mapper
public interface CodeMapper {
    
    /**
     * 코드 그룹 ID 전체 목록
     * @param groupId
     * @return
     */
    List<CodeResDto> selectGroupIdAllList(String groupId);

}
