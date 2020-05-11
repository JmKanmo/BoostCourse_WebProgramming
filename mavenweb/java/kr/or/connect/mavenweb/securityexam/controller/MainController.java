package kr.or.connect.mavenweb.securityexam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
	@RequestMapping("/main")
	public ModelAndView mainPage(ModelAndView model) {
		model.setViewName("main");
		return model;
	}

	@RequestMapping("/submain")
	public ModelAndView submainPage(ModelAndView model) {
		model.setViewName("submain");
		return model;
	}
	
	@RequestMapping("/securepage")
	@ResponseBody
	public String securitypage() {
		return "secure page";
	}
}