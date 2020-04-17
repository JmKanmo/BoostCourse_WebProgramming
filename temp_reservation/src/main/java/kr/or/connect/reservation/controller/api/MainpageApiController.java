package kr.or.connect.reservation.controller.api;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.or.connect.reservation.service.MainpageService;

@RestController
@RequestMapping(path = "/mainpage/api")
public class MainpageApiController {
	@Autowired
	private MainpageService mainpageService;

	@GetMapping(path = "/products")
	public Map<String, Object> products(@RequestParam(name = "id", required = false, defaultValue = "0") int categoryId,
			@RequestParam(name = "turn", required = false, defaultValue = "0") int turn,
			@RequestParam(name = "limit", required = false, defaultValue = "4") int limit) {
		Map<String, Object> ret = new HashMap<>();
		ret.put("products", mainpageService.getProduct(categoryId, turn, limit));
		ret.put("productCount", mainpageService.getProductCount(categoryId));
		return ret;
	}

	@GetMapping(path = "/categories")
	public Map<String, Object> categories() {
		return Collections.singletonMap("categories", mainpageService.getCategory());
	}

	@GetMapping(path = "/promotions")
	public Map<String, Object> promotions() {
		return Collections.singletonMap("promotions", mainpageService.getPromotion());
	}
}
