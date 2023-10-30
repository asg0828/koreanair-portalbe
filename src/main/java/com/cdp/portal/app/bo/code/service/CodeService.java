package com.cdp.portal.app.bo.code.service;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdp.portal.app.bo.code.dto.request.CodeReqDto;
import com.cdp.portal.app.bo.code.dto.response.CodeResDto;
import com.cdp.portal.app.bo.code.mapper.CodeMapper;
import com.cdp.portal.app.bo.code.model.CodeModel;
import com.cdp.portal.common.enumeration.CdpPortalError;

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
     * 코드 그룹 저장
     * @param dto
     */
    @Transactional
    public void saveCodeGroup(CodeReqDto.CreateGroupCodeReq dto) {
        CodeModel codeModel= null;
        
        CodeResDto CodeResDto = this.getCode("GROUP_ID", dto.getGroupId());
        if (Objects.isNull(CodeResDto)) {
            codeModel = CodeModel.builder()
                    .groupId("GROUP_ID")
                    .codeId(dto.getGroupId())
                    .codeNm(dto.getGroupNm())
                    .codeDsc(dto.getGroupDsc())
                    .useYn(dto.getGroupUseYn())
                    .rgstId("admin")    // TODO: 로그인한 사용자 세팅
                    .modiId("admin")    // TODO: 로그인한 사용자 세팅
                    .build();
            
            codeMapper.insertCode(codeModel);
        } else {
            codeModel = CodeModel.builder()
                    .groupId("GROUP_ID")
                    .codeId(dto.getGroupId())
                    .codeNm(dto.getGroupNm())
                    .codeDsc(dto.getGroupDsc())
                    .useYn(dto.getGroupUseYn())
                    .modiId("admin")    // TODO: 로그인한 사용자 세팅
                    .build();
            
            codeMapper.updateCode(codeModel);
        }
    }
    
    /**
     * 코드 조회
     * @param groupId
     * @param codeId
     * @return
     */
    public CodeResDto getCode(String groupId, String codeId) {
        return codeMapper.selectByGroupIdAndCodeId(groupId, codeId);
    }
    
    /**
     * 코드 저장
     * @param dto
     */
    @Transactional
    public void saveCode(final String groupId, CodeReqDto.CreateCodeReq dto) {
        CodeModel codeModel = null;
        
        CodeResDto codeResDto = this.getCode(groupId, dto.getCodeId());
        if (Objects.isNull(codeResDto)) {
            codeModel = CodeModel.builder()
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
        } else {
            codeModel = CodeModel.builder()
                    .groupId(groupId)
                    .codeId(dto.getCodeId())
                    .codeNm(dto.getCodeNm())
                    .codeDsc(dto.getCodeDsc())
                    .ordSeq(dto.getOrdSeq())
                    .useYn(dto.getUseYn())
                    .modiId("admin")    // TODO: 로그인한 사용자 세팅
                    .build();
            
            codeMapper.updateCode(codeModel);
        }
    }
    
    /**
     * 코드 수정
     * @param groupId
     * @param dto
     */
    public void updateCode(final String groupId, final String codeId, CodeReqDto.CreateCodeReq dto) {
        CodeResDto codeResDto = this.getCode(groupId, codeId);
        if (Objects.isNull(codeResDto)) {
            throw CdpPortalError.CODE_NOT_FOUND.exception(codeId, groupId); 
        }
        
        CodeModel codeModel = CodeModel.builder()
                .groupId(groupId)
                .codeId(codeId)
                .codeNm(dto.getCodeNm())
                .codeDsc(dto.getCodeDsc())
                .ordSeq(dto.getOrdSeq())
                .useYn(dto.getUseYn())
                .modiId("admin")    // TODO: 로그인한 사용자 세팅
                .build();
        
        codeMapper.updateCode(codeModel);
    }

}
