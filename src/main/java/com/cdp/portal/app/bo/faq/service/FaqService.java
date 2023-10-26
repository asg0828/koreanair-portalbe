package com.cdp.portal.app.bo.faq.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cdp.portal.app.bo.faq.dto.response.FaqResDto;
import com.cdp.portal.app.bo.faq.mapper.FaqMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FaqService {

    private final FaqMapper faqMapper;

    /**
     * FAQ 전체 목록
     * @param
     * @return
     */

    public List<FaqResDto> getFaqAllList() {
        return faqMapper.selectFaqList();
    }

}
