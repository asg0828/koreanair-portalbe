package com.cdp.portal.app.facade.qna.mapper;

import java.util.List;

import com.cdp.portal.app.facade.notice.dto.request.NoticeReqDto;
import com.cdp.portal.app.facade.qna.dto.request.QnaReqDto;
import com.cdp.portal.app.facade.qna.model.QnaModel;
import com.cdp.portal.app.facade.qna.model.QnaModel;
import com.cdp.portal.app.facade.qna.dto.response.QnaResDto;
import com.cdp.portal.app.facade.qna.model.QnaModel;
import com.cdp.portal.common.dto.PagingDto;
import org.apache.ibatis.annotations.Mapper;

import com.cdp.portal.app.facade.qna.dto.response.QnaResDto;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface QnaMapper {

    /**
     * QNA 전체 목록
     * @param
     * @return
     */

    List<QnaResDto> selectAll(@Param("paging") PagingDto pagingDto, @Param("search") QnaReqDto.SearchQna searchDto);

    int selectCount(@Param("search") QnaReqDto.SearchQna searchDto);
    /**
     * Q&A 조회
     * @param qnaId
     * @return
     */
    QnaResDto selectByQnaId(@Param("qnaId") String qnaId);

    /**
     * Q&A 등록
     * @param qnaModel
     * @return
     */
    Long insertQna(QnaModel qnaModel);

    /**
     * Q&A수정
     * @param qnaModel
     * @return
     */
    Long updateQna(QnaModel qnaModel);

    /**
     * Q&A 삭제
     * @param qnaId
     * @return
     */
    void deleteQna(String qnaId);
    void deleteQna2(QnaReqDto.DeleteQnaReq dto);

    /**
     * 조회수 증가
     * @param qnaId
     * @return
     */
    void addViewCntQna(String qnaId);
}
