package kr.or.connect.reservation.controller.rest;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.or.connect.reservation.dto.BookingHistory;
import kr.or.connect.reservation.dto.TicketHistory;
import kr.or.connect.reservation.service.MyReservationpageService;

@RestController
@RequestMapping(path = "/myreservationpage/api")
public class MyReservationpageRestController {
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
			Calendar startCal = Calendar.getInstance();
			Calendar endCal = Calendar.getInstance();
			startCal.setTime(curDate);
			endCal.setTime(resrvDate);
			long diffMillis = startCal.getTimeInMillis() - endCal.getTimeInMillis();
			int diff = (int) (diffMillis / (24 * 60 * 60 * 1000));

			if (diff > 0) {
				return Status.USED;
			} else {
				return Status.SCHEDULED;
			}
		}
	}

	@GetMapping(path = "/reservations")
	public Map<String, Object> getHistoryByEmail(
			@RequestParam(name = "resrvEmail", required = false, defaultValue = "") String resrvEmail)
			throws ParseException {
		Map<String, Object> ret = new HashMap<>();
		List<BookingHistory> totalHistoryList = myReservationpageService.getBookingHistory(resrvEmail);
		List<BookingHistory> canceldHistory = new ArrayList<>();
		List<BookingHistory> scheduledHistory = new ArrayList<>();
		List<BookingHistory> usedHistory = new ArrayList<>();

		for (BookingHistory bookingHistory : totalHistoryList) {
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

	@GetMapping(path = "/reservations/id")
	public Map<String, Object> getHistoryById(
			@RequestParam(name = "reservationId", required = false, defaultValue = "") int reservationId) {
		Map<String, Object> ret = new HashMap<>();
		BookingHistory bookingHistory = myReservationpageService.getBookingHistory(reservationId);
		ret.put("bookingHistory", bookingHistory);
		return ret;
	}

	@PutMapping(path = "/reservations")
	public int cancelReservation(
			@RequestParam(name = "reservationInfoId", required = false, defaultValue = "") int reservationInfoId) {
		return myReservationpageService.cancelReservation(reservationInfoId);
	}
}
