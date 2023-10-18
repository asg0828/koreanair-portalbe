package com.bdp.ap.app.common;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.bdp.ap.app.menu.service.MenuService;
import com.bdp.ap.common.annotation.NoLogging;
import com.bdp.ap.config.security.AuthUser;

@Controller
public class IndexController {

	@Resource(name="menuService")
	private MenuService menuService;

	@NoLogging
    @GetMapping("/")
    public String index(Model model, HttpServletRequest request, HttpSession session, @ModelAttribute String modiId, @AuthenticationPrincipal AuthUser authUser) {
    	String result = "redirect:/login";
    	if (authUser != null) {
    		result = "redirect:"+menuService.selectFirstMenuUrl(null, authUser.getMemberModel().getAuthId());
    	}
        return result;
    }

    @NoLogging
    @GetMapping("/system")
    public String system(Model model, HttpServletRequest request, HttpSession session, @ModelAttribute String modiId, @AuthenticationPrincipal AuthUser authUser) {
    	String result = "redirect:/login";
    	if (authUser != null) {
    		result = "redirect:"+menuService.selectFirstMenuUrl("/system",authUser.getMemberModel().getAuthId());
    	}
        return result;
    }

    @NoLogging
    @GetMapping("/external")
    public String external(Model model, HttpServletRequest request, HttpSession session, @ModelAttribute String modiId, @AuthenticationPrincipal AuthUser authUser) {
    	String result = "redirect:/login";
    	if (authUser != null) {
    		result = "redirect:"+menuService.selectFirstMenuUrl("/external", authUser.getMemberModel().getAuthId());
    	}
        return result;
    }

    @NoLogging
    @GetMapping("/board")
    public String board(Model model, HttpServletRequest request, HttpSession session, @ModelAttribute String modiId, @AuthenticationPrincipal AuthUser authUser) {
    	String result = "redirect:/login";
    	if (authUser != null) {
    		result = "redirect:"+menuService.selectFirstMenuUrl("/board",authUser.getMemberModel().getAuthId());
    	}
        return result;
    }

    @NoLogging
    @GetMapping("/project")
    public String project(Model model, HttpServletRequest request, HttpSession session, @ModelAttribute String modiId, @AuthenticationPrincipal AuthUser authUser) {
    	String result = "redirect:/login";
    	if (authUser != null) {
    		result = "redirect:"+menuService.selectFirstMenuUrl("/project",authUser.getMemberModel().getAuthId());
    	}
        return result;
    }

    @NoLogging
    @GetMapping("/monitor")
    public String resource(Model model, HttpServletRequest request, HttpSession session, @ModelAttribute String modiId, @AuthenticationPrincipal AuthUser authUser) {
    	String result = "redirect:/login";
    	if (authUser != null) {
    		result = "redirect:"+menuService.selectFirstMenuUrl("/monitor",authUser.getMemberModel().getAuthId());
    	}
        return result;
    }

    @NoLogging
    @GetMapping("/audit")
    public String audit(Model model, HttpServletRequest request, HttpSession session, @ModelAttribute String modiId, @AuthenticationPrincipal AuthUser authUser) {
    	String result = "redirect:/login";
    	if (authUser != null) {
    		result = "redirect:"+menuService.selectFirstMenuUrl("/audit",authUser.getMemberModel().getAuthId());
    	}
        return result;
    }
}
