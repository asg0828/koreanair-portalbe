package com.cdp.portal.app.bo.code.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cdp.portal.app.bo.code.dto.response.CodeResDto;
import com.cdp.portal.app.bo.code.model.CodeModel;

@Mapper
public interface CodeMapper {
    
    /**
     * 코드 그룹 ID 전체 목록
     * @param groupId
     * @return
     */
    List<CodeResDto> selectGroupIdAllList(String groupId);
    
    /**
     * 코드 등록
     * @param codeModel
     * @return
     */
    Long insertCode(CodeModel codeModel);

}
