package kr.or.connect.reservation.controller;

import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PageController {

	@GetMapping(path = "/main")
	public ModelAndView mainpage(ModelAndView model) throws ParseException {
		model.setViewName("main");
		return model;
	}

	@GetMapping(path = "/detail")
	public ModelAndView datailpage(ModelAndView model) throws ParseException {
		model.setViewName("detail");
		return model;
	}
}
