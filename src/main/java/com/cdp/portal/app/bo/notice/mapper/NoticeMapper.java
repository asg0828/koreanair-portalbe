package com.cdp.portal.app.bo.notice.mapper;

import java.util.List;

import com.cdp.portal.app.bo.code.model.CodeModel;
import com.cdp.portal.app.bo.notice.model.NoticeModel;
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

    /**
     * 공지사항 등록
     * @param noticeModel
     * @return
     */
    Long insertNotice(NoticeModel noticeModel);

    /**
     * 공지사항 삭제
     * @param noticeId
     * @return
     */
    void deleteNotice(String noticeId);

    /**
     * 공지사항 수정
     * @param noticeModel
     * @return
     */
    Long updateNotice(NoticeModel noticeModel);
}
