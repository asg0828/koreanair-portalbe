package com.cdp.portal.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Schema(description = "페이징 정보")
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PagingDto {

    @Schema(description = "페이지", example = "1", nullable = false)
    private int page;
    
    @Schema(description = "페이지 사이즈", example = "10", nullable = false)
    private int pageSize;
    
    @Schema(description = "전체 페이지수", example = "7", nullable = false)
    private int totalPage;
    
    @Schema(description = "전체 데이터 카운트", example = "70", nullable = false)
    private int totalCount;
    
    @Builder
    public PagingDto(int page, int pageSize) {
        this.page = page;
        this.pageSize = pageSize;
    }
    
    public void setPaging(int totalCount) {
        this.totalCount = totalCount;
        int tempPage = totalCount / pageSize;

        if (totalCount % pageSize != 0) tempPage++;
        this.totalPage = tempPage;
    }

}
