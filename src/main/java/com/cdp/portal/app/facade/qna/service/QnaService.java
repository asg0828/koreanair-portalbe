package com.cdp.portal.app.facade.qna.service;

import java.util.List;
import java.util.Objects;

import com.cdp.portal.app.facade.qna.dto.request.QnaReqDto;
import com.cdp.portal.app.facade.qna.dto.request.QnaReqDto;
import com.cdp.portal.app.facade.qna.dto.response.QnaResDto;
import com.cdp.portal.app.facade.qna.model.QnaModel;
import com.cdp.portal.app.facade.qna.dto.request.QnaReqDto;
import com.cdp.portal.app.facade.qna.model.QnaModel;
import com.cdp.portal.app.facade.qna.dto.response.QnaResDto;
import com.cdp.portal.app.facade.qna.model.QnaModel;
import com.cdp.portal.common.enumeration.CdpPortalError;
import org.springframework.stereotype.Service;

import com.cdp.portal.app.facade.qna.dto.response.QnaResDto;
import com.cdp.portal.app.facade.qna.mapper.QnaMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QnaService {

    private final QnaMapper qnaMapper;
    /**
     * QNA 전체 목록
     * @param
     * @return
     */
    public List<QnaResDto> getQnaAllList() {
        return qnaMapper.selectQnaList();
    }

    /**
     * 공지사항 상세조회
     * @param qnaId
     * @return
     */
    public QnaResDto getQna(String qnaId) {
        return qnaMapper.selectByQnaId(qnaId);
    }
    public void selectQna(QnaModel qnaModel){
        qnaMapper.selectQna(qnaModel);
    }

    /**
     * Q&A 등록
     * @param dto
     */
    public void createQna(QnaReqDto.CreateQnaReq dto) {
        QnaModel qnaModel = QnaModel.builder()
                .qnaId(dto.getQnaId())
                .clCode(dto.getClCode())
                .sj(dto.getSj())
                .cn(dto.getCn())
                .qnaStat("UNREAD")
                .useYn(dto.getUseYn())
                .openYn(dto.getOpenYn())
                .rgstId("admin") //TODO : 추후 변경
                .modiId("admin")
                .build();

        qnaMapper.insertQna(qnaModel);
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
     * @param dto
     */
    public void deleteQna(QnaReqDto.DeleteQnaReq dto) {
        qnaMapper.deleteQna(dto);
    }
    public void deleteQna2(QnaReqDto.DeleteQnaReq dto) {
        qnaMapper.deleteQna2(dto);
    }

    public void addViewCntQna(String qnaId) {
        qnaMapper.addViewCntQna(qnaId);
    }
}