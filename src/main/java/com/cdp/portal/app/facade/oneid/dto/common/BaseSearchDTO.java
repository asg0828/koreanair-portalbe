package com.cdp.portal.app.facade.oneid.dto.common;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class BaseSearchDTO<T> {
    private T search;
    private Pagination paging;
}