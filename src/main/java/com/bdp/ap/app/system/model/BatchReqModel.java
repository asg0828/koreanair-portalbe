package com.bdp.ap.app.system.model;

import com.bdp.ap.common.paging.Criteria;

import lombok.Data;

@Data
public class BatchReqModel extends Criteria {
    private String scheduleNm;
    private String scheduleDsc;
    private String lockYn;
    private String useYn;
}
