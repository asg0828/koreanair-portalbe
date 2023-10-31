package com.cdp.portal.app.facade.notice.mapper;

import java.util.List;

import com.cdp.portal.app.facade.notice.model.NoticeModel;
import org.apache.ibatis.annotations.Mapper;

import com.cdp.portal.app.facade.notice.dto.response.NoticeResDto;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface NoticeMapper {

    /**
     * 공지사항 전체 목록
     * @param
     * @return
     */
    List<NoticeResDto> selectNoticeList();

    /**
     * 공지사항 상세
//     * @param noticeId
     * @param noticeModel
     * @return
     */
    void selectNotice(NoticeModel noticeModel);

    /**
     * 공지사항 등록
     * @param noticeModel
     * @return
     */
    Long insertNotice(NoticeModel noticeModel);

    /**
     * 공지사항 조회
     * @param noticeId
     * @return
     */
    NoticeResDto selectByNoticeId( @Param("noticeId") String noticeId);

    /**
     * 공지사항 수정
     * @param noticeModel
     * @return
     */
    Long updateNotice(NoticeModel noticeModel);

    /**
     * 공지사항 삭제
     * @param noticeId
     * @return
     */
    void deleteNotice(String noticeId);
    void addViewCntNotice(String noticeId);
}
