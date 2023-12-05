package com.cdp.portal.app.facade.session.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserHrInfosDto {
	private List<UserHrInfoDto.HrInfo> Report_Entry = new ArrayList<>();
}
