package kr.or.connect.reservation.controller;

import java.io.File;
import java.nio.file.Files;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.or.connect.reservation.service.MyReservationpageService;
import kr.or.connect.reservation.service.ReviewWritepageService;

@Controller
public class BasicController {
	@Autowired
	private MyReservationpageService myReservationpageService;

	@Autowired
	private ReviewWritepageService reviewWritepageService;

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

	@RequestMapping(value = "imgLoad.do")
	public void imgLoad(HttpServletRequest req, HttpServletResponse res) throws Exception {
		Integer imageId = Integer.parseInt(req.getParameter("imageId"));
		if (imageId.equals(0)) {
			return;
		}
		String[] splited = reviewWritepageService.selectImage(imageId).split("/");
		String imagePath = "C:\\tmp\\" + splited[0] + "\\";
		File file = new File(imagePath, splited[1]);
		res.setHeader("Content-Length", String.valueOf(file.length()));
		res.setHeader("Content-Disposition", "inline; filename=\"" + file.getName() + "\"");
		Files.copy(file.toPath(), res.getOutputStream());
	}
}
