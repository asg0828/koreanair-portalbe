package com.cdp.portal.app.facade.notice.service;

import java.util.List;

import com.cdp.portal.app.bo.code.dto.request.CodeReqDto;
import com.cdp.portal.app.bo.code.dto.response.CodeResDto;
import com.cdp.portal.app.bo.code.model.CodeModel;
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
     * 공지사항 등록
     * @param dto
     */
    public void createNotice(NoticeReqDto.CreateNoticeReq dto) {
        NoticeModel noticeModel = NoticeModel.builder()
                .noticeId(dto.getNoticeId())
                .sj(dto.getSj())
                .cn(dto.getCn())
                .importantYn(dto.getImportantYn())
                .rgstId("admin")
                .modiId("admin")
                .build();

        noticeMapper.insertNotice(noticeModel);
    }

    /**
     * 공지사항 삭제
     * @param noticeId
     */
    public void deleteNotice(String noticeId) {
        NoticeModel noticeModel = NoticeModel.builder()
                .delYn("N")
                .build();

        noticeMapper.deleteNotice(noticeId);
    }

    /**
     * 공지사항 수정
     * @param noticeId
     * @param dto
     */
    public void updateNotice(final String noticeId, NoticeReqDto.CreateNoticeReq dto) {
//        NoticeResDto noticeResDto = this.getNotice(noticeId);
//        if (Objects.isNull(codeResDto)) {
//            throw CdpPortalError.CODE_NOT_FOUND.exception(noticeId);
//        }

        NoticeModel noticeModel = NoticeModel.builder()
                .noticeId(noticeId)
                .sj(dto.getSj())
                .cn(dto.getCn())
//                .ordSeq(dto.getOrdSeq())
//                .useYn(dto.getUseYn())
                .modiId("admin")    // TODO: 로그인한 사용자 세팅
                .build();

        noticeMapper.updateNotice(noticeModel);
    }
}
