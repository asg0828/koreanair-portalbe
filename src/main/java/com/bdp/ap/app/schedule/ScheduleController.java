package com.bdp.ap.app.schedule;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bdp.ap.app.schedule.service.ScheduleService;
import com.bdp.ap.common.annotation.NoLogging;

@Controller
@RequestMapping("/cmm")
public class ScheduleController {

	@Resource(name = "scheduleService")
    private ScheduleService scheduleService;

	/**
     * Schedule Job execute immediately
     */
	@NoLogging
    @ResponseBody
    @RequestMapping(value="/schedule/{scheduleNm}",method= {RequestMethod.GET,RequestMethod.POST}, produces=MediaType.APPLICATION_JSON_VALUE)
    public Map<String,Object> testSchedule(@PathVariable String scheduleNm) {
    	Map<String,Object> result = new HashMap<>();
    	result.put("result", scheduleService.startJob(scheduleNm));
    	return result;
    }


}
