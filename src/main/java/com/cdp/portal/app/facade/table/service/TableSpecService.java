package com.cdp.portal.app.facade.table.service;

import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdp.portal.app.facade.table.dto.request.TableSpecReqDto;
import com.cdp.portal.app.facade.table.dto.request.TableSpecReqDto.CreateTableColumnSpec;
import com.cdp.portal.app.facade.table.dto.response.TableSpecResDto;
import com.cdp.portal.app.facade.table.mapper.TableColumnSpecMapper;
import com.cdp.portal.app.facade.table.mapper.TableSpecMapper;
import com.cdp.portal.app.facade.table.model.TableColumnSpecModel;
import com.cdp.portal.app.facade.table.model.TableSpecModel;
import com.cdp.portal.common.IdUtil;
import com.cdp.portal.common.dto.PagingDto;
import com.cdp.portal.common.enumeration.CdpPortalError;
import com.cdp.portal.common.util.SessionScopeUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class TableSpecService {
    
    private final TableSpecMapper tableSpecMapper;
    private final TableColumnSpecMapper tableColumnSpecMapper;
    private final IdUtil idUtil;
    
    @Transactional
    public void createTableSpec(TableSpecReqDto.CreateTableSpec dto) {
        Boolean isExists = tableSpecMapper.isExistsByMtsEnNm(dto.getMtsEnNm());
        if (isExists) {
            throw CdpPortalError.MTS_EN_NM_DUPLICATED.exception(dto.getMtsEnNm());
        }
        
        /* insert table spec */
        final String mtsId = idUtil.getMetaTblId();
        log.debug("##### createTableSpec mtsId: {}", mtsId);
        
        TableSpecModel tableSpecModel = TableSpecModel.builder()
                .mtsId(mtsId)
                .mtsKoNm(dto.getMtsKoNm())
                .mtsEnNm(dto.getMtsEnNm())
                .mtsDef(dto.getMtsDef())
                .srcSys(dto.getSrcSys())
                .srcTbNm(dto.getSrcTbNm())
                .srcDbCd(dto.getSrcDbCd())
                .mtsDsc(dto.getMtsDsc())
                .rgstId(SessionScopeUtil.getContextSession().getUserId())
                .modiId(SessionScopeUtil.getContextSession().getUserId())
                .build();
        
        tableSpecMapper.insert(tableSpecModel);
        
        /* insert table column spec */
        for (CreateTableColumnSpec columnSpec : dto.getColumnSpecs()) {
            TableColumnSpecModel tableColumnSpecModel = TableColumnSpecModel.builder()
                    .mtsId(mtsId)
                    .mcsId(idUtil.getMetaTblColId())
                    .mcsEnNm(columnSpec.getMcsEnNm())
                    .mcsKoNm(columnSpec.getMcsKoNm())
                    .mcsDef(columnSpec.getMcsDef())
                    .srcClNm(columnSpec.getSrcClNm())
                    .clFm(columnSpec.getClFm())
                    .rgstId(SessionScopeUtil.getContextSession().getUserId())
                    .modiId(SessionScopeUtil.getContextSession().getUserId())
                    .build();
            
            tableColumnSpecMapper.insert(tableColumnSpecModel);
        }
    }
    
    @Transactional(readOnly = true)
    public TableSpecResDto.TableSpecsResult getTableSpecs(PagingDto pagingDto, TableSpecReqDto.SearchTableSpec searchDto) {
        pagingDto.setPaging(tableSpecMapper.selectCount(searchDto));
        
        return TableSpecResDto.TableSpecsResult.builder()
                .page(pagingDto)
                .search(searchDto)
                .contents(tableSpecMapper.selectAll(pagingDto, searchDto))
                .build();
    }
    
    @Transactional(readOnly = true)
    public TableSpecResDto.TableSpec getTableSpec(final String mtsId) {
        TableSpecResDto.TableSpec tableSpec = tableSpecMapper.selectById(mtsId);
        if (Objects.isNull(tableSpec)) {
            throw CdpPortalError.TABLE_SPEC_NOT_FOUND.exception(mtsId);
        }
        
        tableSpec.setColumnSpecs(tableColumnSpecMapper.selectByMtsId(mtsId));
        
        return tableSpec;
    }
    
    @Transactional
    public void updateTableSpec(final String mtsId, TableSpecReqDto.UpdateTableSpec dto) {
        /* get table spec */
        TableSpecResDto.TableSpec tableSpec = tableSpecMapper.selectById(mtsId);
        if (Objects.isNull(tableSpec)) {
            throw CdpPortalError.TABLE_SPEC_NOT_FOUND.exception(mtsId);
        }
        
        /* update table spec */
        TableSpecModel tableSpecModel = TableSpecModel.builder()
                .mtsId(mtsId)
                .mtsKoNm(dto.getMtsKoNm())
                .mtsEnNm(dto.getMtsEnNm())
                .mtsDef(dto.getMtsDef())
                .srcSys(dto.getSrcSys())
                .srcTbNm(dto.getSrcTbNm())
                .srcDbCd(dto.getSrcDbCd())
                .mtsDsc(dto.getMtsDsc())
                .modiId(SessionScopeUtil.getContextSession().getUserId())
                .build();
        
        tableSpecMapper.update(tableSpecModel);
        
        /* delete table column spec */
        tableColumnSpecMapper.deleteByMtsId(mtsId);
        
        /* insert table column spec */
        for (CreateTableColumnSpec columnSpec : dto.getColumnSpecs()) {
            TableColumnSpecModel tableColumnSpecModel = TableColumnSpecModel.builder()
                    .mtsId(mtsId)
                    .mcsId(idUtil.getMetaTblColId())
                    .mcsEnNm(columnSpec.getMcsEnNm())
                    .mcsKoNm(columnSpec.getMcsKoNm())
                    .mcsDef(columnSpec.getMcsDef())
                    .srcClNm(columnSpec.getSrcClNm())
                    .clFm(columnSpec.getClFm())
                    .rgstId(SessionScopeUtil.getContextSession().getUserId())
                    .modiId(SessionScopeUtil.getContextSession().getUserId())
                    .build();
            
            tableColumnSpecMapper.insert(tableColumnSpecModel);
        }
    }
    
    @Transactional
    public void deleteTableSpec(final String mtsId) {
        /* get table spec */
        TableSpecResDto.TableSpec tableSpec = tableSpecMapper.selectById(mtsId);
        if (Objects.isNull(tableSpec)) {
            throw CdpPortalError.TABLE_SPEC_NOT_FOUND.exception(mtsId);
        }
        
        /* update table spec delYn */
        tableSpecMapper.updateDelYnById(SessionScopeUtil.getContextSession().getUserId(), mtsId);
        
        /* update table column spec delYn */
        tableColumnSpecMapper.updateDelYnByMtsId(SessionScopeUtil.getContextSession().getUserId(), mtsId);
    }

}
