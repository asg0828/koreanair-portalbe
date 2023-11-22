package com.cdp.portal.app.facade.qna.service;

import java.util.List;
import java.util.Objects;

import com.cdp.portal.app.facade.file.model.FileModel;
import com.cdp.portal.app.facade.file.service.FileService;
import com.cdp.portal.app.facade.qna.dto.request.QnaReqDto;
import com.cdp.portal.app.facade.qna.dto.response.QnaResDto;
import com.cdp.portal.app.facade.qna.model.QnaModel;
import com.cdp.portal.common.IdUtil;
import com.cdp.portal.common.dto.PagingDto;
import com.cdp.portal.common.enumeration.CdpPortalError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.cdp.portal.app.facade.qna.mapper.QnaMapper;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class QnaService {

    private final QnaMapper qnaMapper;
    private final IdUtil idUtil;
    private final FileService fileService;

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
                .rgstId(dto.getRgstId()) //TODO : 추후 변경
                .answRgstId(dto.getAnswRgstId())
                .modiId(dto.getModiId())
                .build();

        // 파일 서비스를 통해 파일의 refId를 설정
        if (dto.getFileIds() != null) {
            for (String fileId : dto.getFileIds()) {
                FileModel file = new FileModel();
                file.setFileId(fileId);
                file.setRefId(qnaId); // 파일의 refId를 공지사항의 ID로 설정
                file.setModiId("admin"); // TODO: 로그인한 사용자 세팅
                fileService.updateFile(file); // 파일 서비스의 updateFile 메서드를 호출하여 파일의 refId를 업데이트
            }
        }

        qnaMapper.insertQna(qnaModel);
    }

    /**
     * QNA 목록 조회
     * @param
     * @return
     */
    @Transactional
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
    @Transactional
    public QnaResDto getQna(String qnaId) {
        return qnaMapper.selectByQnaId(qnaId);
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
                .answRgstId("admin")// TODO: 로그인한 사용자 세팅
                .useYn(dto.getUseYn())
                .openYn(dto.getOpenYn())
                .qnaStat(dto.getQnaStat())
                .modiId("admin")    // TODO: 로그인한 사용자 세팅
                .build();

        // 파일 서비스를 통해 파일의 refId를 설정
        if (dto.getFileIds() != null) {
            for (String fileId : dto.getFileIds()) {
                FileModel file = new FileModel();
                file.setFileId(fileId);
                file.setRefId(qnaId); // 파일의 refId를 공지사항의 ID로 설정
                file.setModiId("admin"); // TODO: 로그인한 사용자 세팅
                fileService.updateFile(file); // 파일 서비스의 updateFile 메서드를 호출하여 파일의 refId를 업데이트
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
