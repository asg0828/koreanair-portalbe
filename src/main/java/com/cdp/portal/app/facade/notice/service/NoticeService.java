package com.cdp.portal.app.facade.notice.service;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdp.portal.app.facade.file.mapper.FileLinkMapper;
import com.cdp.portal.app.facade.file.mapper.FileMapper;
import com.cdp.portal.app.facade.file.model.FileLinkModel;
import com.cdp.portal.app.facade.file.model.FileModel;
import com.cdp.portal.app.facade.notice.dto.request.NoticeReqDto;
import com.cdp.portal.app.facade.notice.dto.response.NoticeResDto;
import com.cdp.portal.app.facade.notice.mapper.NoticeMapper;
import com.cdp.portal.app.facade.notice.model.NoticeModel;
import com.cdp.portal.common.IdUtil;
import com.cdp.portal.common.dto.PagingDto;
import com.cdp.portal.common.enumeration.CdpPortalError;
import com.cdp.portal.common.util.SessionScopeUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class NoticeService {
	private final IdUtil idUtil;
    private final NoticeMapper noticeMapper;
    private final FileMapper fileMapper;
    private final FileLinkMapper fileLinkMapper;

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
                file.setModiId(SessionScopeUtil.getContextSession().getUserId());
                fileMapper.updateFile(file); // 파일 서비스의 updateFile 메서드를 호출하여 파일의 refId를 업데이트
            }
        }
        
        if (dto.getFileLinks() != null) {
        	for (String fileLinkUrl : dto.getFileLinks()) {
        		FileLinkModel fileLink = new FileLinkModel();
        		fileLink.setFileLinkId(idUtil.getFileLinkId());
        		fileLink.setRefId(noticeId);
        		fileLink.setFileLinkUrl(fileLinkUrl);
        		fileLinkMapper.insertFileLink(fileLink);
        	}
        }
        
        noticeMapper.insertNotice(noticeModel);
    }

    /**
     * 공지사항 목록 조회
     * @param
     * @return
     */
    @Transactional(readOnly = true)
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
    @Transactional(readOnly = true)
    public NoticeResDto getNotice(String noticeId) {
        NoticeResDto notice = noticeMapper.selectByNoticeId(noticeId);

        if (notice != null) {
            // 공지사항에 첨부된 파일 목록 조회
            List<FileModel> fileList = fileMapper.selectFileListByRefId(noticeId);
            notice.setFileList(fileList);
            List<String> fileLinks = fileLinkMapper.selectFileLinkListByRefId(noticeId);
            notice.setFileLinks(fileLinks);
        }

        return notice;
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
                file.setModiId(SessionScopeUtil.getContextSession().getUserId());
                fileMapper.updateFile(file); // 파일 서비스의 updateFile 메서드를 호출하여 파일의 refId를 업데이트
            }
        }
        
        if (dto.getFileLinks() != null) {
        	fileLinkMapper.deleteFileLinkByRefId(noticeId);
        	
        	for (String fileLinkUrl : dto.getFileLinks()) {
        		FileLinkModel fileLink = new FileLinkModel();
        		fileLink.setFileLinkId(idUtil.getFileLinkId());
        		fileLink.setRefId(noticeId);
        		fileLink.setFileLinkUrl(fileLinkUrl);
        		fileLinkMapper.insertFileLink(fileLink);
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
