package com.cdp.portal.app.facade.notice.service;

import java.util.List;
import java.util.Objects;

import com.cdp.portal.common.enumeration.CdpPortalError;
import org.springframework.stereotype.Service;
import com.cdp.portal.app.facade.notice.dto.request.NoticeReqDto;
import com.cdp.portal.app.facade.notice.dto.response.NoticeResDto;
import com.cdp.portal.app.facade.notice.mapper.NoticeMapper;
import com.cdp.portal.app.facade.notice.model.NoticeModel;

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

    /**
     * 공지사항 상세 화면
     * @param noticeId
     * @return
     */

    public void selectNotice(String noticeId){
        NoticeModel noticeModel = NoticeModel.builder()
                .noticeId(noticeId)
                .build();
        noticeMapper.selectNotice(noticeModel);
    }
    
    /**
     * 공지사항 등록
     * @param dto
     */
    public void createNotice(NoticeReqDto.CreateNoticeReq dto) {
        NoticeModel noticeModel = NoticeModel.builder()
                .noticeId(dto.getNoticeId())
                .sj(dto.getSj())
                .cn(dto.getCn())
                .importantYn(dto.getImportantYn())
                .popupYn(dto.getPopupYn())
                .useYn(dto.getUseYn())
                .startDt(dto.getStartDt())
                .endDt(dto.getEndDt())
                .rgstId("admin")
                .modiId("admin")
                .build();

        noticeMapper.insertNotice(noticeModel);
    }

    /**
     * 공지사항 조회
     * @param noticeId
     * @return
     */
    public NoticeResDto getNotice(String noticeId) {
        return noticeMapper.selectByNoticeId(noticeId);
    }

    /**
     * 공지사항 삭제
     * @param noticeId
     */
    public void deleteNotice(String noticeId) {
        noticeMapper.deleteNotice(noticeId);
    }

    /**
     * 공지사항 수정
     * @param noticeId
     * @param dto
     */
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
                .modiId("admin")    // TODO: 로그인한 사용자 세팅
                .build();

        noticeMapper.updateNotice(noticeModel);
    }

    public void addViewCntNotice(String noticeId) {
        noticeMapper.addViewCntNotice(noticeId);
    }
}
