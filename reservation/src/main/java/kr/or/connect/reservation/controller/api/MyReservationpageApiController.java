package kr.or.connect.reservation.controller.api;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

	private enum Status {
		CANCEL, SCHEDULED, USED
	}

	private Status classifyHistory(BookingHistory bookingHistory) throws ParseException {
		if (bookingHistory.getCancelFlag() == 1) {
			return Status.CANCEL;
		} else {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
			Date resrvDate = dateFormat.parse(bookingHistory.getReservationDate());
			Date curDate = new Date();
			int diff = curDate.compareTo(resrvDate);

			if (diff > 0) {
				return Status.USED;
			} else {
				return Status.SCHEDULED;
			}
		}
	}

	@GetMapping(path = "/history")
	public Map<String, Object> history(
			@RequestParam(name = "resrvEmail", required = false, defaultValue = "") String resrvEmail)
			throws ParseException {
		Map<String, Object> ret = new HashMap<>();
		List<BookingHistory> totalHistoryList = myReservationpageService.getBookingHistory(resrvEmail);
		List<BookingHistory> canceldHistory = new ArrayList<>();
		List<BookingHistory> scheduledHistory = new ArrayList<>();
		List<BookingHistory> usedHistory = new ArrayList<>();

		for (int i = 0; i < totalHistoryList.size(); i++) {
			BookingHistory bookingHistory = totalHistoryList.get(i);
			List<TicketHistory> ticketHistory = myReservationpageService
					.getTicketHistory(bookingHistory.getReservationId());
			bookingHistory.setTicketHistory(ticketHistory);
			Status classifiedRes = this.classifyHistory(bookingHistory);

			switch (classifiedRes) {
			case CANCEL:
				canceldHistory.add(bookingHistory);
				break;

			case USED:
				usedHistory.add(bookingHistory);
				break;

			case SCHEDULED:
				scheduledHistory.add(bookingHistory);
				break;
			}
		}
		ret.put("scheduledHistory", scheduledHistory);
		ret.put("usedHistory", usedHistory);
		ret.put("canceldHistory", canceldHistory);
		return ret;
	}
}
