package kr.or.connect.reservation.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PageController {

	@GetMapping(path = "/main")
	public ModelAndView mainPage(ModelAndView model) throws ParseException {
		model.setViewName("main");
		return model;
	}

	@GetMapping(path = "/detail")
	public ModelAndView datailPage(ModelAndView model) throws ParseException {
		model.setViewName("detail");
		return model;
	}

	@GetMapping(path = "/reserve")
	public ModelAndView reservationPage(ModelAndView model,
			@CookieValue(value = "count", defaultValue = "1", required = true) String value,
			HttpServletResponse response) throws ParseException {
		try {
			int i = Integer.parseInt(value);
			value = Integer.toString(++i);
		} catch (Exception ex) {
			value = "1";
		}
		Cookie cookie = new Cookie("count", value);
		cookie.setMaxAge(0); // 1년 동안 유지.
		cookie.setPath("/"); // / 경로 이하에 모두 쿠키 적용.
		response.addCookie(cookie);
		model.addObject("cookieCount", value);
		model.setViewName("reserve");
		return model;
	}

	@GetMapping(path = "/review")
	public ModelAndView reviewPage(ModelAndView model) throws ParseException {
		model.setViewName("review");
		return model;
	}
}
