package com.cdp.portal.app.facade.oneid.dto.common;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Pagination {
	private int totalPage;
	private int pageSize;
	private int page;
	private int totalCount;
}

