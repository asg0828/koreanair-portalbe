package com.cdp.portal.app.bo.notice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cdp.portal.app.bo.notice.dto.response.NoticeResDto;
import com.cdp.portal.app.bo.notice.mapper.NoticeMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeMapper noticeMapper;

    /**
     * 공지사항 전체 목록
     * @param
     * @return
     */

    public List<NoticeResDto> getNoticeAllList() {
        return noticeMapper.selectNoticeList();
    }

}
