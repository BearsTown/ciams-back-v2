package com.uitgis.ciams.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CustomErrorController implements ErrorController {

	private static final String PATH = "/error";

	@GetMapping("/")
	public String userIndex() {
		return "index";
	}

	@RequestMapping(PATH)
	public ModelAndView redirectRoot(HttpServletRequest req) {
		ModelAndView mav;
		mav = new ModelAndView("index");
		return mav;
	}

	public String getErrorPath() {
		return PATH;
	}

}
