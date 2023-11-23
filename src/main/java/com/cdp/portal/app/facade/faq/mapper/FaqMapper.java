package com.cdp.portal.app.facade.faq.mapper;

import java.util.List;

import com.cdp.portal.app.facade.faq.model.FaqModel;
import com.cdp.portal.app.facade.faq.dto.response.FaqResDto;
import com.cdp.portal.app.facade.faq.dto.request.FaqReqDto;
import com.cdp.portal.common.dto.PagingDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface FaqMapper {

    /**
     * FAQ 전체 목록
     * @param
     * @return
     */

    List<FaqResDto> selectAll(@Param("paging") PagingDto pagingDto, @Param("search") FaqReqDto.SearchFaq searchDto);

    int selectCount(@Param("search") FaqReqDto.SearchFaq searchDto);
    /**
     * FAQ 조회
     * @param faqId
     * eId
     * @return
     */
    FaqResDto selectByFaqId(@Param("faqId") String faqId);

    /**
     * FAQ 등록
     * @param faqModel
     * @return
     */
    Long insertFaq(FaqModel faqModel);

    /**
     * FAQ 수정
     * @param faqModel
     * @return
     */
    Long updateFaq(FaqModel faqModel);

    /**
     * FAQ 삭제
     * @param faqId
     * @return
     */
    void deleteFaq(String faqId);
    void deleteFaq2(FaqReqDto.DeleteFaqReq dto);

    void addViewCntFaq(String faqId);
}
