package com.cdp.portal.app.facade.notice.service;

import java.util.List;
import java.util.Objects;

import com.cdp.portal.common.IdUtil;
import com.cdp.portal.common.dto.PagingDto;
import com.cdp.portal.common.enumeration.CdpPortalError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.cdp.portal.app.facade.notice.dto.request.NoticeReqDto;
import com.cdp.portal.app.facade.notice.dto.response.NoticeResDto;
import com.cdp.portal.app.facade.notice.mapper.NoticeMapper;
import com.cdp.portal.app.facade.notice.model.NoticeModel;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class NoticeService {
    private final NoticeMapper noticeMapper;
    private final IdUtil idUtil;

    /**
     * 공지사항 등록
     * @param dto
     *
     */
    @Transactional
    public void createNotice(NoticeReqDto.CreateNoticeReq dto) {

        final String noticeId = idUtil.getNoticeId();
        log.debug("##### createNotice noticeId: {}", noticeId);

        NoticeModel noticeModel = NoticeModel.builder()
                .noticeId(noticeId)
                .sj(dto.getSj())
                .cn(dto.getCn())
                .importantYn(dto.getImportantYn())
                .popupYn(dto.getPopupYn())
                .useYn(dto.getUseYn())
                .startDt(dto.getStartDt())
                .endDt(dto.getEndDt())
                .rgstId(dto.getRgstId())
                .modiId(dto.getModiId()) // TODO: 로그인한 사용자 세팅
                .build();

        noticeMapper.insertNotice(noticeModel);
    }

    /**
     * 공지사항 목록 조회
     * @param
     * @return
     */
    public NoticeResDto.NoticesResult getNotices (PagingDto pagingDto, NoticeReqDto.SearchNotice searchDto) {
        pagingDto.setPaging(noticeMapper.selectCount(searchDto));

        return NoticeResDto.NoticesResult.builder()
                .page(pagingDto)
                .search(searchDto)
                .contents(noticeMapper.selectAll(pagingDto,searchDto))
                .build();
    }

    /**
     * 공지사항 조회
     * @param noticeId
     * @return
     */
    @Transactional
    public NoticeResDto getNotice(String noticeId) {
        return noticeMapper.selectByNoticeId(noticeId);
    }

    /**
     * 공지사항 수정
     * @param noticeId
     * @param dto
     */
    @Transactional
    public void updateNotice(final String noticeId, NoticeReqDto.UpdateNoticeReq dto) {
        NoticeResDto noticeResDto = this.getNotice(noticeId);

        if (Objects.isNull(noticeResDto)) {
            throw CdpPortalError.CODE_NOT_FOUND.exception(noticeId);
        }

        NoticeModel noticeModel = NoticeModel.builder()
                .noticeId(noticeId)
                .sj(dto.getSj())
                .cn(dto.getCn())
                .popupYn(dto.getPopupYn())
                .importantYn(dto.getImportantYn())
                .useYn(dto.getUseYn())
                .startDt(dto.getStartDt())
                .endDt(dto.getEndDt())
                .modiId(dto.getModiId())    // TODO: 로그인한 사용자 세팅
                .build();

        noticeMapper.updateNotice(noticeModel);
    }
    /**
     * 공지사항 삭제
     * @param noticeId
     */
    public void deleteNotice(String noticeId) {
        noticeMapper.deleteNotice(noticeId);
    }
    public void deleteNotice2(NoticeReqDto.DeleteNoticeReq dto) {
        noticeMapper.deleteNotice2(dto);
    }

    /**
     * 조회수 증가
     * @param noticeId
     */
    public void addViewCntNotice(String noticeId) {
        noticeMapper.addViewCntNotice(noticeId);
    }
}
