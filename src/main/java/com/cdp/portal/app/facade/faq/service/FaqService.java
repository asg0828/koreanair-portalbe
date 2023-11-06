package com.cdp.portal.app.facade.faq.service;

import java.util.List;
import java.util.Objects;

import com.cdp.portal.app.facade.faq.dto.request.FaqReqDto;
import com.cdp.portal.app.facade.faq.model.FaqModel;
import com.cdp.portal.app.facade.faq.dto.request.FaqReqDto;
import com.cdp.portal.app.facade.faq.model.FaqModel;
import com.cdp.portal.app.facade.faq.dto.response.FaqResDto;
import com.cdp.portal.app.facade.faq.dto.request.FaqReqDto;
import com.cdp.portal.app.facade.faq.dto.response.FaqResDto;
import com.cdp.portal.app.facade.faq.model.FaqModel;
import com.cdp.portal.app.facade.faq.dto.request.FaqReqDto;
import com.cdp.portal.app.facade.faq.dto.request.FaqReqDto;
import com.cdp.portal.app.facade.notice.dto.request.NoticeReqDto;
import com.cdp.portal.app.facade.notice.dto.response.NoticeResDto;
import com.cdp.portal.common.dto.PagingDto;
import com.cdp.portal.common.enumeration.CdpPortalError;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.cdp.portal.app.facade.faq.dto.response.FaqResDto;
import com.cdp.portal.app.facade.faq.mapper.FaqMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FaqService {

    private final FaqMapper faqMapper;

    /**
     * FAQ 목록 조회
     * @param
     * @return
     */

    public FaqResDto.FaqsResult getFaqs (PagingDto pagingDto, FaqReqDto.SearchFaq searchDto) {
        pagingDto.setPaging(faqMapper.selectCount(searchDto));

        return FaqResDto.FaqsResult.builder()
                .page(pagingDto)
                .search(searchDto)
                .contents(faqMapper.selectAll(pagingDto,searchDto))
                .build();
    }

    /**
     * FAQ 상세조회
     * @param faqId
     * @return
     */
    public FaqResDto getFaq(String faqId) {
        return faqMapper.selectByFaqId(faqId);
    }

    /**
     * 공지사항 등록
     * @param dto
     */
    public void createFaq(FaqReqDto.CreateFaqReq dto) {
        FaqModel faqModel = FaqModel.builder()
                .faqId(dto.getFaqId())
                .clCode(dto.getClCode())
                .qstn(dto.getQstn())
                .answ(dto.getAnsw())
                .useYn(dto.getUseYn())
                .rgstId("admin")
                .modiId("admin")
                .build();

        faqMapper.insertFaq(faqModel);
    }

    /**
     * FAQ 수정
     * @param faqId
     * @param dto
     */
    public void updateFaq(final String faqId, FaqReqDto.UpdateFaqReq dto) {
        FaqResDto faqResDto = this.getFaq(faqId);
        if (Objects.isNull(faqResDto)) {
            throw CdpPortalError.CODE_NOT_FOUND.exception(faqId);
        }

        FaqModel faqModel = FaqModel.builder()
                .faqId(faqId)
                .clCode(dto.getClCode())
                .qstn(dto.getQstn())
                .answ(dto.getAnsw())
                .useYn(dto.getUseYn())
                .modiId("admin")    // TODO: 로그인한 사용자 세팅
                .build();

        faqMapper.updateFaq(faqModel);
    }

    public void deleteFaq(String faqId) {
        faqMapper.deleteFaq(faqId);
    }
    public void deleteFaq2(FaqReqDto.DeleteFaqReq dto) {
        faqMapper.deleteFaq2(dto);
    }

    /**
     * 조회수 증가
     * @param faqId
     */
    public void addViewCntFaq(String faqId) {
        faqMapper.addViewCntFaq(faqId);
    }
}
