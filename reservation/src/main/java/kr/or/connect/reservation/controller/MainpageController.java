package kr.or.connect.reservation.controller;

import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainpageController {
	private RestTemplate restTemplate = new RestTemplate();

	public List<Map<String, Object>> request(String param) throws ParseException {
		String ret = restTemplate.getForObject("http://localhost:8080/reservation/api/" + param, String.class);
		JSONParser parser = new JSONParser();
		JSONObject jsonObj = (JSONObject) parser.parse(ret);
		return (List<Map<String, Object>>) jsonObj.get(param);
	}

	@GetMapping(path = "/main")
	public ModelAndView mainpage(ModelAndView model) throws ParseException {
		model.addObject("categories", request("categories"));
		model.addObject("promotions", request("promotions"));
		model.setViewName("main");
		return model;
	}

}
