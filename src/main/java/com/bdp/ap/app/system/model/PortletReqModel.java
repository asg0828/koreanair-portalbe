package com.bdp.ap.app.system.model;

import com.bdp.ap.common.paging.Criteria;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PortletReqModel extends Criteria {
    private String portletNm;
    private String portletTypeCd;
    private String shareYn;
}