package com.bdp.ap.app.audit;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bdp.ap.app.audit.service.DataService;
import com.bdp.ap.common.paging.Criteria;

import lombok.extern.slf4j.Slf4j;

/**
 * 데이터 수집 현황
 */
@RequestMapping("/audit")
@Controller
@Slf4j
public class DataCollectController {

    @Resource
    private DataService dataService;


    /**
     * 데이터 수집 현황
     * @param model
     * @return
     */
    @GetMapping("/dataCollect")
    public String dataCollect(@ModelAttribute Criteria criteria, @RequestParam(required = false) String startDt, @RequestParam(required = false) String endDt, Model model) {

        if(!StringUtils.isEmpty(startDt)) {
			criteria.setStartDt(startDt);
		}
		if(!StringUtils.isEmpty(endDt)) {
			//endDate = endDate+" 23:59:59";
			criteria.setEndDt(endDt);
		}
        //수집
        criteria.setType("C");

        model.addAttribute("datas", dataService.selectDataList(criteria));
        criteria.setTotalCount(dataService.selectDataListCount(criteria));
        model.addAttribute("pages", criteria);

        return "audit/dataCollect";
    }

   /**
    * 데이터 파기 현황
    * @param model
    * @return
   */
    @GetMapping("/dataDestroy")
    public String dataDestroy(@ModelAttribute Criteria criteria, @RequestParam(required = false) String startDt, @RequestParam(required = false) String endDt, Model model) {

        if(!StringUtils.isEmpty(startDt)) {
			criteria.setStartDt(startDt);
		}
		if(!StringUtils.isEmpty(endDt)) {
			//endDate = endDate+" 23:59:59";
			criteria.setEndDt(endDt);
		}
        //파기
        criteria.setType("D");

        model.addAttribute("datas", dataService.selectDataList(criteria));
        criteria.setTotalCount(dataService.selectDataListCount(criteria));
        model.addAttribute("pages", criteria);

        return "audit/dataDestroy";
    }
}
