package com.cdp.portal.app.facade.faq.service;

import java.util.List;
import java.util.Objects;

import com.cdp.portal.app.facade.faq.dto.request.FaqReqDto;
import com.cdp.portal.app.facade.faq.model.FaqModel;
import com.cdp.portal.app.facade.faq.dto.response.FaqResDto;
import com.cdp.portal.common.IdUtil;
import com.cdp.portal.common.dto.PagingDto;
import com.cdp.portal.common.enumeration.CdpPortalError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.cdp.portal.app.facade.faq.mapper.FaqMapper;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class FaqService {

    private final FaqMapper faqMapper;
    private final IdUtil idUtil;

    /**
     * Faq 등록
     * @param dto
     */
    @Transactional
    public void createFaq(FaqReqDto.CreateFaqReq dto) {

        final String faqId = idUtil.getFaqId();
        log.debug("##### createFaq faqId: {}", faqId);

        FaqModel faqModel = FaqModel.builder()
                .faqId(faqId)
                .clCode(dto.getClCode())
                .qstn(dto.getQstn())
                .answ(dto.getAnsw())
                .useYn(dto.getUseYn())
                .rgstId(dto.getRgstId()) // TODO: 로그인한 사용자 세팅
                .modiId(dto.getModiId())
                .build();

        faqMapper.insertFaq(faqModel);
    }

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
     * FAQ 조회
     * @param faqId
     * @return
     */
    public FaqResDto getFaq(String faqId) {
        return faqMapper.selectByFaqId(faqId);
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
                .modiId(dto.getModiId())    // TODO: 로그인한 사용자 세팅
                .build();

        faqMapper.updateFaq(faqModel);
    }
    /**
     * Faq 삭제
     * @param faqId
     */
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
