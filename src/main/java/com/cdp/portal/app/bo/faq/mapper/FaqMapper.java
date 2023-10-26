package com.cdp.portal.app.bo.faq.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cdp.portal.app.bo.faq.dto.response.FaqResDto;

@Mapper
public interface FaqMapper {

    /**
     * FAQ 전체 목록
     * @param
     * @return
     */

    List<FaqResDto> selectFaqList();
}
