package kr.or.connect.reservation.controller.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.or.connect.reservation.dto.BookingHistory;
import kr.or.connect.reservation.dto.TicketHistory;
import kr.or.connect.reservation.service.MyReservationpageService;

@RestController
@RequestMapping(path = "/myreservationpage/api")
public class MyReservationpageApiController {
	@Autowired
	private MyReservationpageService myReservationpageService;

	@GetMapping(path = "/history")
	public Map<String, Object> history(
			@RequestParam(name = "resrv_email", required = false, defaultValue = "") String resrvEmail) {
		Map<String, Object> ret = new HashMap<>();
		List<BookingHistory> bookingHistoryList = myReservationpageService.getBookingHistory(resrvEmail);

		for (int i = 0; i < bookingHistoryList.size(); i++) {
			BookingHistory bookingHistory = bookingHistoryList.get(i);
			List<TicketHistory> ticketHistory = myReservationpageService
					.getTicketHistory(bookingHistory.getReservationId());
			bookingHistory.setTicketHistory(ticketHistory);
			bookingHistoryList.set(i, bookingHistory);
		}
		ret.put("bookingHistory", bookingHistoryList);
		return ret;
	}
}
