package com.cdp.portal.app.facade.oneid.dto.common;

import lombok.Builder;
import lombok.Data;

import java.util.List;


@Data
@Builder
public class GridData<T> {
    private List<T> contents;
    private Pagination page;
}

