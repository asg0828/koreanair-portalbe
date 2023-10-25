package com.cdp.portal.app.admin.code.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cdp.portal.app.admin.code.dto.response.CodeResDto;
import com.cdp.portal.app.admin.code.mapper.CodeMapper;

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

}
