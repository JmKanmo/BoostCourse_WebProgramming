package kr.or.connect.reservation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.connect.reservation.dao.MyReservationpageDao;
import kr.or.connect.reservation.dto.BookingHistory;
import kr.or.connect.reservation.dto.TicketHistory;
import kr.or.connect.reservation.service.MyReservationpageService;

@Service
public class MyReservationpageServiceImpl implements MyReservationpageService {
	@Autowired
	private MyReservationpageDao myReservationpageDao;

	@Override
	public List<BookingHistory> getBookingHistory(String email) {
		// TODO Auto-generated method stub
		return myReservationpageDao.selectBookingHistory(email);
	}

	@Override
	public List<TicketHistory> getTicketHistory(int reservation_info_id) {
		// TODO Auto-generated method stub
		return myReservationpageDao.selectTicketHistory(reservation_info_id);
	}

	@Override
	public int getReservationCount(String email) {
		// TODO Auto-generated method stub
		return myReservationpageDao.selectReservationCount(email);
	}

}
