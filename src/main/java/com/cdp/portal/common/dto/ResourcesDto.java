package com.cdp.portal.common.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Schema(description = "API 목록 정보 공통 응답")
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_EMPTY)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResourcesDto<T> {
    
    @Schema(description = "컨텐츠 정보", example = "", nullable = false)
    private T contents = null;
    
    @Schema(description = "검색 정보", example = "", nullable = false)
    private T search = null;
    
    @Schema(description = "페이지 정보", example = "", nullable = false)
    private PagingDto page = null;

    @Builder
    public ResourcesDto(T contents, T search, PagingDto page) {
        this.contents = contents;
        this.search = search;
        this.page = page;
    }

}
