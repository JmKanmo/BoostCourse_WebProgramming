package kr.or.connect.mvcexam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.connect.mvcexam.dto.User;

@Controller
public class UserController {
	@RequestMapping(path = "/userform", method = RequestMethod.GET)
	public String userform() {
		return "userform";
	}

	@RequestMapping(path = "/regist", method = RequestMethod.POST)
	public String regist(@ModelAttribute User user, ModelMap model) {
		// User객체에 담긴 정보들을 출력한다.
//		System.out.println(user.toString());
		model.addAttribute("user", user);
		return "regist";
	}
}
