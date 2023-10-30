package com.cdp.portal.app.facade.qna.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cdp.portal.app.facade.qna.dto.response.QnaResDto;

@Mapper
public interface QnaMapper {

    /**
     * QNA 전체 목록
     * @param
     * @return
     */

    List<QnaResDto> selectQnaList();
}
