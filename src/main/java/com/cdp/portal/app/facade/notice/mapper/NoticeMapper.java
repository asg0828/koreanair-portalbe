package com.cdp.portal.app.facade.notice.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cdp.portal.app.facade.notice.dto.request.NoticeReqDto;
import com.cdp.portal.app.facade.notice.dto.response.NoticeResDto;
import com.cdp.portal.app.facade.notice.model.NoticeModel;
import com.cdp.portal.common.dto.PagingDto;

@Mapper
public interface NoticeMapper {

    /**
     * 공지사항 등록
     * @param noticeModel
     * @return
     */
    Long insertNotice(NoticeModel noticeModel);

    /**
     * 공지사항 전체 목록
     * @param
     * @return
     */
    List<NoticeResDto> selectAll();



    /**
     * 공지사항 조회
     * @param noticeId
     * @return
     */
    NoticeResDto selectByNoticeId( @Param("noticeId") String noticeId);

    List<NoticeResDto> selectAll(@Param("paging") PagingDto pagingDto, @Param("search") NoticeReqDto.SearchNotice searchDto);

    int selectCount(@Param("search") NoticeReqDto.SearchNotice searchDto);

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
    void deleteNotice2(NoticeReqDto.DeleteNoticeReq dto);

    /**
     * 조회수 증가
     * @param noticeId
     * @return
     */
    void addViewCntNotice(String noticeId);
}
