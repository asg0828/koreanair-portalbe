package com.cdp.portal.app.bo.code.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cdp.portal.app.bo.code.dto.response.CodeResDto;
import com.cdp.portal.app.bo.code.model.CodeModel;

@Mapper
public interface CodeMapper {
    
    /**
     * 코드 목록 조회
     * @param groupId
     * @return
     */
    List<CodeResDto> selectCodeByGroupId(String groupId);
    
    /**
     * 코드 등록
     * @param codeModel
     * @return
     */
    Long insertCode(CodeModel codeModel);
    
    /**
     * 코드 조회
     * @param groupId
     * @param codeId
     * @return
     */
    CodeResDto selectByGroupIdAndCodeId(@Param("groupId") String groupId, @Param("codeId") String codeId);
    
    /**
     * 코드 수정
     * @param codeModel
     * @return
     */
    Long updateCode(CodeModel codeModel);

}
