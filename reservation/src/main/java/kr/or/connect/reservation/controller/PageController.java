package kr.or.connect.reservation.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.or.connect.reservation.service.MyReservationpageService;

@Controller
public class PageController {
	@Autowired
	private MyReservationpageService myReservationpageService;

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
	public ModelAndView reservationPage(ModelAndView model) throws ParseException {
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

		if (session.getAttribute("email") == null && myReservationpageService.getReservationCount(resrvEmail) > 0) {
			session.setAttribute("email", resrvEmail);
		}
		model.setViewName("myreservation");
		return model;
	}

	@GetMapping(path = "/reviewWrite")
	public ModelAndView reviewWritePage(ModelAndView model) throws ParseException {
		model.setViewName("reviewWrite");
		return model;
	}
}
