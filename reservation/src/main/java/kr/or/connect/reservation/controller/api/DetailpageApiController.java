package kr.or.connect.reservation.controller.api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.or.connect.reservation.service.DetailpageService;

@RestController
@RequestMapping(path = "/detailpage/api")
public class DetailpageApiController {
	@Autowired
	private DetailpageService detailpageService;

	@GetMapping(path = "/promotion")
	public Map<String, Object> promotion(
			@RequestParam(name = "productId", required = false, defaultValue = "0") int productId) {
		Map<String, Object> ret = new HashMap<>();
		ret.put("image", detailpageService.getPromotion(productId));
		ret.put("etcImgCnt", detailpageService.getEtcImageCount(productId));
		return ret;
	}

	@GetMapping(path = "/product")
	public Map<String, Object> product(
			@RequestParam(name = "productId", required = false, defaultValue = "0") int productId) {
		Map<String, Object> ret = new HashMap<>();
		ret.put("product", detailpageService.getProduct(productId));
		return ret;
	}

	@GetMapping(path = "/review")
	public Map<String, Object> review(
			@RequestParam(name = "productId", required = false, defaultValue = "0") int productId,
			@RequestParam(name = "displayInfoId", required = false, defaultValue = "0") int displayInfoId) {
		Map<String, Object> ret = new HashMap<>();
		ret.put("avgAndCnt", detailpageService.getReviewAvgCnt(productId, displayInfoId));
		ret.put("review", detailpageService.getUserReview(productId, displayInfoId));
		return ret;
	}

	@GetMapping(path = "/display")
	public Map<String, Object> display(
			@RequestParam(name = "displayInfoId", required = false, defaultValue = "0") int displayInfoId) {
		Map<String, Object> ret = new HashMap<>();
		ret.put("display", detailpageService.getDisplayInfo(displayInfoId));
		return ret;
	}
}
