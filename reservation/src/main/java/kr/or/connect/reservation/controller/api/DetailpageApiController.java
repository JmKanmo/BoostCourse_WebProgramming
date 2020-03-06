package kr.or.connect.reservation.controller.api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.or.connect.reservation.service.DetailpageService;

@RestController
@RequestMapping(path = "/detailpage/api")
public class DetailpageApiController {
	private DetailpageService detailpageService;

	public DetailpageApiController(DetailpageService detailpageService) {
		this.detailpageService = detailpageService;
	}

	@GetMapping(path = "/product")
	public Map<String, Object> product(@RequestParam(name = "id", required = false, defaultValue = "0") int productId) {
		Map<String, Object> ret = new HashMap<>();
		ret.put("product", detailpageService.getProduct(productId));
		ret.put("image", detailpageService.getPromotion(productId));
		ret.put("etcImgCnt", detailpageService.getEtcImageCount(productId));
		return ret;
	}
}
