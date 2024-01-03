package com.cdp.portal.app.facade.file.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdp.portal.app.facade.file.mapper.FileLinkMapper;
import com.cdp.portal.app.facade.file.model.FileLinkModel;
import com.cdp.portal.common.IdUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
@RequiredArgsConstructor
public class FileLinkService {

    private final FileLinkMapper fileLinkMapper;
    private final IdUtil idUtil;

    /**
     * 파일 링크 등록
     *
     * @param
     */
    @Transactional
    public void insertFileLink(FileLinkModel fileLink) {
    	final String fileLinkId = idUtil.getFileLinkId();
    	log.debug("##### createNotice fileLinkId: {}", fileLinkId);
    	
    	FileLinkModel fileLinkModel = FileLinkModel.builder()
    			.fileLinkId(fileLinkId)
    			.fileLinkUrl(fileLink.getFileLinkUrl())
    			.build();
    	
        fileLinkMapper.insertFileLink(fileLinkModel);
    }

    /**
     * 파일 링크 목록 조회
     *
     * @param
     */
    @Transactional(readOnly = true)
    public List<String> selectFileLinkListByRefId(String refId) {
        return fileLinkMapper.selectFileLinkListByRefId(refId);
    }

    /**
     * 파일 링크 삭제
     *
     * @param fileLinkId
     * @return 삭제한 파일 링크의 ID
     */
    @Transactional
    public long deleteFileLinkByRefId(String refId) {
        return fileLinkMapper.deleteFileLinkByRefId(refId);
    }
}