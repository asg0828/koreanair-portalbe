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
     * 코드 목록 조회
     * @param groupId
     * @return
     */
    public List<CodeResDto> getCodes(String groupId) {
        return codeMapper.selectCodeByGroupId(groupId);
    }
    
    /**
     * 코드 그룹 등록
     * @param dto
     */
    public void saveCodeGroup(CodeReqDto.CreateGroupCodeReq dto) {
        CodeModel codeModel = CodeModel.builder()
                .codeId(dto.getGroupId())
                .codeNm(dto.getGroupNm())
                .codeDsc(dto.getGroupDsc())
                .useYn(dto.getGroupUseYn())
                .groupId("GROUP_ID")
                .rgstId("admin")    // TODO: 로그인한 사용자 세팅
                .modiId("admin")    // TODO: 로그인한 사용자 세팅
                .build();
        
        codeMapper.insertCode(codeModel);
        
    }
    
    /**
     * 코드 조회
     * @param groupId
     * @param codeId
     * @return
     */
    public CodeResDto getByGroupIdAndCodeId(String groupId, String codeId) {
        return codeMapper.selectByGroupIdAndCodeId(groupId, codeId);
    }
    
    /**
     * 코드 등록
     * @param dto
     */
    public void saveCode(final String groupId, CodeReqDto.CreateCodeReq dto) {
        CodeModel codeModel = CodeModel.builder()
                .groupId(groupId)
                .codeId(dto.getCodeId())
                .codeNm(dto.getCodeNm())
                .codeDsc(dto.getCodeDsc())
                .ordSeq(dto.getOrdSeq())
                .useYn(dto.getUseYn())
                .rgstId("admin")    // TODO: 로그인한 사용자 세팅
                .modiId("admin")    // TODO: 로그인한 사용자 세팅
                .build();
        
        codeMapper.insertCode(codeModel);
    }

}
