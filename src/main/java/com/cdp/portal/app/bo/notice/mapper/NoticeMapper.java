package com.cdp.portal.app.bo.notice.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cdp.portal.app.bo.notice.dto.response.NoticeResDto;

@Mapper
public interface NoticeMapper {

    /**
     * 공지사항 전체 목록
     * @param
     * @return
     */

    List<NoticeResDto> selectNoticeList();
}
