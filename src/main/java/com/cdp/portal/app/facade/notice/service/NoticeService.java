package com.cdp.portal.app.facade.notice.service;

import java.util.List;
import java.util.Objects;

import com.cdp.portal.app.facade.file.model.FileModel;
import com.cdp.portal.app.facade.file.service.FileService;
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
    private final FileService fileService;

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
                .rgstId(dto.getRgstId())
                .modiId(dto.getModiId()) // TODO: 로그인한 사용자 세팅
                .build();

        // 파일 서비스를 통해 파일의 refId를 설정
        if (dto.getFileIds() != null) {
            for (String fileId : dto.getFileIds()) {
                FileModel file = new FileModel();
                file.setFileId(fileId);
                file.setRefId(noticeId); // 파일의 refId를 공지사항의 ID로 설정
                file.setModiId("admin"); // TODO: 로그인한 사용자 세팅
                fileService.updateFile(file); // 파일 서비스의 updateFile 메서드를 호출하여 파일의 refId를 업데이트
            }
        }

        noticeMapper.insertNotice(noticeModel);
    }

    /**
     * 공지사항 목록 조회
     * @param
     * @return
     */
    @Transactional
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
                .modiId(dto.getModiId())    // TODO: 로그인한 사용자 세팅
                .build();

        // 파일 서비스를 통해 파일의 refId를 설정
        if (dto.getFileIds() != null) {
            for (String fileId : dto.getFileIds()) {
                FileModel file = new FileModel();
                file.setFileId(fileId);
                file.setRefId(noticeId); // 파일의 refId를 공지사항의 ID로 설정
                file.setModiId("admin"); // TODO: 로그인한 사용자 세팅
                fileService.updateFile(file); // 파일 서비스의 updateFile 메서드를 호출하여 파일의 refId를 업데이트
            }
        }

        noticeMapper.updateNotice(noticeModel);
    }
    /**
     * 공지사항 삭제
     * @param noticeId
     */
    @Transactional
    public void deleteNotice(String noticeId) {
        noticeMapper.deleteNotice(noticeId);
    }
    @Transactional
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
