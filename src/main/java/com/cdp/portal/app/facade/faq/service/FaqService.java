package com.cdp.portal.app.facade.faq.service;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdp.portal.app.facade.faq.dto.request.FaqReqDto;
import com.cdp.portal.app.facade.faq.dto.response.FaqResDto;
import com.cdp.portal.app.facade.faq.mapper.FaqMapper;
import com.cdp.portal.app.facade.faq.model.FaqModel;
import com.cdp.portal.app.facade.file.mapper.FileLinkMapper;
import com.cdp.portal.app.facade.file.mapper.FileMapper;
import com.cdp.portal.app.facade.file.model.FileLinkModel;
import com.cdp.portal.app.facade.file.model.FileModel;
import com.cdp.portal.common.IdUtil;
import com.cdp.portal.common.dto.PagingDto;
import com.cdp.portal.common.enumeration.CdpPortalError;
import com.cdp.portal.common.util.SessionScopeUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class FaqService {

    private final FaqMapper faqMapper;
    private final IdUtil idUtil;
    private final FileMapper fileMapper;
    private final FileLinkMapper fileLinkMapper;

    /**
     * FAQ 목록 조회
     * @param
     * @return
     */
    @Transactional(readOnly = true)
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
    @Transactional(readOnly = true)
    public FaqResDto getFaq(String faqId) {
            FaqResDto faq = faqMapper.selectByFaqId(faqId);

        if (faq != null) {
            List<FileModel> fileList = fileMapper.selectFileListByRefId(faqId);
            faq.setFileList(fileList);
            List<String> fileLinks = fileLinkMapper.selectFileLinkListByRefId(faqId);
            faq.setFileLinks(fileLinks);
        }

        return faq;
    }

    /**
     * FAQ 등록
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
                .rgstId(dto.getRgstId())
                .modiId(dto.getModiId())
                .build();

        // 파일 서비스를 통해 파일의 refId를 설정
        if (dto.getFileIds() != null) {
            for (String fileId : dto.getFileIds()) {
                FileModel file = new FileModel();
                file.setFileId(fileId);
                file.setRefId(faqId); // 파일의 refId를 공지사항의 ID로 설정
                file.setModiId(SessionScopeUtil.getContextSession().getUserId());
                fileMapper.updateFile(file); // 파일 서비스의 updateFile 메서드를 호출하여 파일의 refId를 업데이트
            }
        }
        
        if (dto.getFileLinks() != null) {
        	for (String fileLinkUrl : dto.getFileLinks()) {
        		FileLinkModel fileLink = new FileLinkModel();
        		fileLink.setFileLinkId(idUtil.getFileLinkId());
        		fileLink.setRefId(faqId);
        		fileLink.setFileLinkUrl(fileLinkUrl);
        		fileLinkMapper.insertFileLink(fileLink);
        	}
        }

        faqMapper.insertFaq(faqModel);
    }

    /**
     * FAQ 수정
     * @param faqId
     * @param dto
     */
    @Transactional
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
                .modiId(SessionScopeUtil.getContextSession().getUserId())
                .build();

        // 파일 서비스를 통해 파일의 refId를 설정
        if (dto.getFileIds() != null) {
            for (String fileId : dto.getFileIds()) {
                FileModel file = new FileModel();
                file.setFileId(fileId);
                file.setRefId(faqId); // 파일의 refId를 공지사항의 ID로 설정
                file.setModiId(SessionScopeUtil.getContextSession().getUserId());
                fileMapper.updateFile(file); // 파일 서비스의 updateFile 메서드를 호출하여 파일의 refId를 업데이트
            }
        }
        
        if (dto.getFileLinks() != null) {
        	fileLinkMapper.deleteFileLinkByRefId(faqId);
        	
        	for (String fileLinkUrl : dto.getFileLinks()) {
        		FileLinkModel fileLink = new FileLinkModel();
        		fileLink.setFileLinkId(idUtil.getFileLinkId());
        		fileLink.setRefId(faqId);
        		fileLink.setFileLinkUrl(fileLinkUrl);
        		fileLinkMapper.insertFileLink(fileLink);
        	}
        }

        faqMapper.updateFaq(faqModel);
    }

    @Transactional
    public void deleteFaq(String faqId) {
    	fileLinkMapper.deleteFileLinkByRefId(faqId);
        faqMapper.deleteFaq(faqId);
    }
    @Transactional
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
