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

	@GetMapping(path = "/reserve")
	public ModelAndView reservepage(ModelAndView model) throws ParseException {
		model.setViewName("reserve");
		return model;
	}

	@GetMapping(path = "/review")
	public ModelAndView reviewpage(ModelAndView model) throws ParseException {
		model.setViewName("review");
		return model;
	}
}
