package kr.or.connect.reservation.controller.api;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.or.connect.reservation.dto.Reservation;
import kr.or.connect.reservation.service.ReservepageService;

@RestController
@RequestMapping(path = "/reservepage/api")
public class ReservepageApiController {
	@Autowired
	private ReservepageService reservepageService;

	@GetMapping(path = "/display")
	public Map<String, Object> display(
			@RequestParam(name = "productId", required = false, defaultValue = "0") int productId,
			@RequestParam(name = "displayInfoId", required = false, defaultValue = "0") int displayInfoId) {
		Map<String, Object> ret = new HashMap<>();
		ret.put("display", reservepageService.getDisplayInfo(productId, displayInfoId));
		ret.put("price", reservepageService.getProductPriceInfo(productId));
		return ret;
	}

	@PostMapping(path = "/reservations")
	public int reservations(@ModelAttribute Reservation reservation) {
		reservation.setCreateDate(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss").format(LocalDateTime.now()));
		reservation.setModifyDate(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss").format(LocalDateTime.now()));
		return reservepageService.addReservation(reservation);
	}
}
