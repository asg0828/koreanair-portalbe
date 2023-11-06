package com.cdp.portal.app.facade.qna.service;

import java.util.List;
import java.util.Objects;

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

    /**
     * Q&A 등록
     * @param dto
     */
    @Transactional
    public void createQna(QnaReqDto.CreateQnaReq dto) {

        final String qnaId = idUtil.getQnaId();
        log.debug("##### createQna qnaId: {}", qnaId);

        QnaModel qnaModel = QnaModel.builder()
                .qnaId(qnaId)
                .clCode(dto.getClCode())
                .sj(dto.getSj())
                .cn(dto.getCn())
                .qnaStat("UNREAD")
                .useYn(dto.getUseYn())
                .openYn(dto.getOpenYn())
                .rgstId(dto.getRgstId()) //TODO : 추후 변경
                .modiId(dto.getModiId())
                .build();

        qnaMapper.insertQna(qnaModel);
    }

    /**
     * QNA 목록 조회
     * @param
     * @return
     */
    public QnaResDto.QnasResult getQnas(PagingDto pagingDto, QnaReqDto.SearchQna searchDto) {
        pagingDto.setPaging(qnaMapper.selectCount(searchDto));

        return QnaResDto.QnasResult.builder()
                .page(pagingDto)
                .search(searchDto)
                .contents(qnaMapper.selectAll(pagingDto,searchDto))
                .build();
    }
    /**
     * 공지사항 상세조회
     * @param qnaId
     * @return
     */
    public QnaResDto getQna(String qnaId) {
        return qnaMapper.selectByQnaId(qnaId);
    }

    /**
     * Q&A 수정
     * @param qnaId
     * @param dto
     */
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
                .modiId("admin")    // TODO: 로그인한 사용자 세팅
                .build();

        qnaMapper.updateQna(qnaModel);
    }

    /**
     * Q&A 삭제
     * @param qnaId
     */
    public void deleteQna(String qnaId) {
        qnaMapper.deleteQna(qnaId);
    }
    public void deleteQna2(QnaReqDto.DeleteQnaReq dto) {
        qnaMapper.deleteQna2(dto);
    }

    public void addViewCntQna(String qnaId) {
        qnaMapper.addViewCntQna(qnaId);
    }
}
