package kr.or.connect.reservation.controller.api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/myreservationpage/api")
public class MyreservationpageApiController {

	@GetMapping(path = "/history")
	public Map<String, Object> promotion(
			@RequestParam(name = "resrv_email", required = false, defaultValue = "") String resrvEmail) {
		Map<String, Object> ret = new HashMap<>();
		// 이메일의 예약내역 service를 이용해 ret에 담아서 반환
		return ret;
	}
}
