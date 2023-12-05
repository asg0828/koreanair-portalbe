package com.cdp.portal.app.facade.code.service;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdp.portal.app.facade.code.dto.request.CodeReqDto;
import com.cdp.portal.app.facade.code.dto.response.CodeResDto;
import com.cdp.portal.app.facade.code.mapper.CodeMapper;
import com.cdp.portal.app.facade.code.model.CodeModel;
import com.cdp.portal.common.enumeration.CdpPortalError;
import com.cdp.portal.common.util.SessionScopeUtil;

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
    @Transactional(readOnly = true)
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
                    .rgstId(SessionScopeUtil.getContextSession().getUserId())
                    .modiId(SessionScopeUtil.getContextSession().getUserId())
                    .build();
            
            codeMapper.insertCode(codeModel);
        } else {
            codeModel = CodeModel.builder()
                    .groupId("GROUP_ID")
                    .codeId(dto.getGroupId())
                    .codeNm(dto.getGroupNm())
                    .codeDsc(dto.getGroupDsc())
                    .useYn(dto.getGroupUseYn())
                    .modiId(SessionScopeUtil.getContextSession().getUserId())
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
    @Transactional(readOnly = true)
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
                    .rgstId(SessionScopeUtil.getContextSession().getUserId())
                    .modiId(SessionScopeUtil.getContextSession().getUserId())
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
                    .modiId(SessionScopeUtil.getContextSession().getUserId())
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
                .modiId(SessionScopeUtil.getContextSession().getUserId())
                .build();
        
        codeMapper.updateCode(codeModel);
    }

}
