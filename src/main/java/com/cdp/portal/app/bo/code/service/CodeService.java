package com.cdp.portal.app.bo.code.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.cdp.portal.app.bo.code.dto.request.CodeReqDto;
import com.cdp.portal.app.bo.code.dto.response.CodeResDto;
import com.cdp.portal.app.bo.code.mapper.CodeMapper;
import com.cdp.portal.app.bo.code.model.CodeModel;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CodeService {
    
    private final CodeMapper codeMapper;
    
    /**
     * 코드 그룹 ID 전체 목록
     * @param groupId
     * @return
     */
    public List<CodeResDto> getGroupIdAllList(String groupId) {
        return codeMapper.selectGroupIdAllList(groupId);
    }
    
    /**
     * 코드 그룹 등록
     * @param dto
     */
    public void saveGroupId(CodeReqDto.CreateCodeReq dto) {
        
        CodeModel codeModel = CodeModel.builder()
                .codeId(dto.getGroupCodeId())
                .codeNm(dto.getGroupCodeNm())
                .codeDsc(dto.getGroupCodeDsc())
                .useYn(dto.getGroupCodeUseYn())
                .groupId("GROUP_ID")
                .rgstId("admin")    // TODO: 로그인한 사용자 세팅
                .modiId("admin")    // TODO: 로그인한 사용자 세팅
                .build();
        
        codeMapper.insertCode(codeModel);
        
    }

}
