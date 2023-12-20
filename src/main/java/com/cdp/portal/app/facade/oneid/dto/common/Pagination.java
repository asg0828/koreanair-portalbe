package com.cdp.portal.app.facade.oneid.dto.common;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Pagination {
	private int offset;
	private int perPage;
	private int page;
	private int totalCount;
}

