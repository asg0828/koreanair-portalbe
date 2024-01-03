package com.cdp.portal.app.facade.qna.service;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdp.portal.app.facade.file.mapper.FileLinkMapper;
import com.cdp.portal.app.facade.file.mapper.FileMapper;
import com.cdp.portal.app.facade.file.model.FileLinkModel;
import com.cdp.portal.app.facade.file.model.FileModel;
import com.cdp.portal.app.facade.qna.dto.request.QnaReqDto;
import com.cdp.portal.app.facade.qna.dto.response.QnaResDto;
import com.cdp.portal.app.facade.qna.mapper.QnaMapper;
import com.cdp.portal.app.facade.qna.model.QnaModel;
import com.cdp.portal.common.IdUtil;
import com.cdp.portal.common.dto.PagingDto;
import com.cdp.portal.common.enumeration.CdpPortalError;
import com.cdp.portal.common.util.SessionScopeUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class QnaService {

	private final IdUtil idUtil;
    private final QnaMapper qnaMapper;
    private final FileMapper fileMapper;
    private final FileLinkMapper fileLinkMapper;

    /**
     * Q&A 등록
     * @param dto
     */
    @Transactional
    public void createQna(QnaReqDto.CreateQnaReq dto) {

        final String qnaId = idUtil.getQnaId();
        log.debug("##### createNotice noticeId: {}", qnaId);

        QnaModel qnaModel = QnaModel.builder()
                .qnaId(qnaId)
                .clCode(dto.getClCode())
                .sj(dto.getSj())
                .cn(dto.getCn())
                .answ(dto.getAnsw())
                .qnaStat("UNREAD")
                .bfQnaId(dto.getBfQnaId())
                .useYn(dto.getUseYn())
                .openYn(dto.getOpenYn())
                .rgstId(dto.getRgstId())
                .answRgstId(dto.getAnswRgstId())
                .modiId(dto.getModiId())
                .build();

        if (dto.getFileIds() != null) {
            for (String fileId : dto.getFileIds()) {
                FileModel file = new FileModel();
                file.setFileId(fileId);
                file.setRefId(qnaId);
                file.setModiId(SessionScopeUtil.getContextSession().getUserId());
                fileMapper.updateFile(file);
            }
        }
        
        if (dto.getFileLinks() != null) {
        	for (String fileLinkUrl : dto.getFileLinks()) {
        		FileLinkModel fileLink = new FileLinkModel();
        		fileLink.setFileLinkId(idUtil.getFileLinkId());
        		fileLink.setRefId(qnaId);
        		fileLink.setFileLinkUrl(fileLinkUrl);
        		fileLinkMapper.insertFileLink(fileLink);
        	}
        }

        qnaMapper.insertQna(qnaModel);
    }

    /**
     * QNA 목록 조회
     * @param
     * @return
     */
    @Transactional(readOnly = true)
    public QnaResDto.QnasResult getQnas(PagingDto pagingDto, QnaReqDto.SearchQna searchDto) {
        pagingDto.setPaging(qnaMapper.selectCount(searchDto));

        return QnaResDto.QnasResult.builder()
                .page(pagingDto)
                .search(searchDto)
                .contents(qnaMapper.selectAll(pagingDto,searchDto))
                .build();
    }
    /**
     * Q&A 조회
     * @param qnaId
     * @return
     */
    @Transactional(readOnly = true)
    public QnaResDto getQna(String qnaId) {
        QnaResDto qna = qnaMapper.selectByQnaId(qnaId);
        if (qna != null) {
            List<FileModel> fileList = fileMapper.selectFileListByRefId(qnaId);
            qna.setFileList(fileList);
            List<String> fileLinks = fileLinkMapper.selectFileLinkListByRefId(qnaId);
            qna.setFileLinks(fileLinks);
        }

        return qna;
    }

    @Transactional
    public List<QnaResDto> selectQnaReplyList(String qnaId) {
        return qnaMapper.selectQnaReplyList(qnaId);
    }

    /**
     * Q&A 수정
     * @param qnaId
     * @param dto
     */
    @Transactional
    public void updateQna(final String qnaId, QnaReqDto.UpdateQnaReq dto) {
        QnaResDto qnaResDto = this.getQna(qnaId);
        if (Objects.isNull(qnaResDto)) {
            throw CdpPortalError.CODE_NOT_FOUND.exception(qnaId);
        }

        QnaModel qnaModel = QnaModel.builder()
                .qnaId(qnaId)
                .clCode(dto.getClCode())
                .sj(dto.getSj())
                .cn(dto.getCn())
                .answ(dto.getAnsw())
                .useYn(dto.getUseYn())
                .openYn(dto.getOpenYn())
                .qnaStat(dto.getQnaStat())
                .modiId(SessionScopeUtil.getContextSession().getUserId())
                .build();

        // 파일 서비스를 통해 파일의 refId를 설정
        if (dto.getFileIds() != null) {
            for (String fileId : dto.getFileIds()) {
                FileModel file = new FileModel();
                file.setFileId(fileId);
                file.setRefId(qnaId); // 파일의 refId를 공지사항의 ID로 설정
                file.setModiId(SessionScopeUtil.getContextSession().getUserId());
                fileMapper.updateFile(file); // 파일 서비스의 updateFile 메서드를 호출하여 파일의 refId를 업데이트
            }
        }
        
        if (dto.getFileLinks() != null) {
        	fileLinkMapper.deleteFileLinkByRefId(qnaId);
        	
        	for (String fileLinkUrl : dto.getFileLinks()) {
        		FileLinkModel fileLink = new FileLinkModel();
        		fileLink.setFileLinkId(idUtil.getFileLinkId());
        		fileLink.setRefId(qnaId);
        		fileLink.setFileLinkUrl(fileLinkUrl);
        		fileLinkMapper.insertFileLink(fileLink);
        	}
        }

        qnaMapper.updateQna(qnaModel);
    }

    /**
     * Q&A 삭제
     * @param qnaId
     */
    @Transactional
    public void deleteQna(String qnaId) {
        qnaMapper.deleteQna(qnaId);
    }
    @Transactional
    public void deleteQna2(QnaReqDto.DeleteQnaReq dto) {
        qnaMapper.deleteQna2(dto);
    }

    public void addViewCntQna(String qnaId) {
        qnaMapper.addViewCntQna(qnaId);
    }
    public void updateQnaStat(String qnaId) { qnaMapper.updateQnaStat(qnaId);}

}
