package kr.or.connect.reservation.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
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
	public ModelAndView reservationPage(ModelAndView model) {
		model.setViewName("reserve");
		return model;
	}

	@GetMapping(path = "/review")
	public ModelAndView reviewPage(ModelAndView model) throws ParseException {
		model.setViewName("review");
		return model;
	}

	@GetMapping(path = "/login")
	public ModelAndView loginPage(ModelAndView model) throws ParseException {
		model.setViewName("login");
		return model;
	}

	@GetMapping(path = "/myreservation")
	public ModelAndView myreservationPage(ModelAndView model, HttpServletRequest request, HttpSession session)
			throws ParseException {
		String resrvEmail = request.getParameter("resrv_email");
		// 세션에 저장 된 이메일 정보가 없고 resrvEmail의 예약내역이 있을경우,세션에 정보저장
		// session.setAttribute("email", resrvEmail);
		// session.removeAttribute("email");
		model.setViewName("myreservation");
		return model;
	}
}
