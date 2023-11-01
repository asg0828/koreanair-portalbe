package com.cdp.portal.app.facade.qna.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cdp.portal.app.facade.qna.dto.response.QnaResDto;
import com.cdp.portal.app.facade.qna.mapper.QnaMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QnaService {

    private final QnaMapper qnaMapper;
    /**
     * QNA 전체 목록
     * @param
     * @return
     */
    public List<QnaResDto> getQnaAllList() {
        return qnaMapper.selectQnaList();
    }

}
