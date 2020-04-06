package kr.or.connect.reservation.dao;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservation.dao.sql.MyReservationDaoSqls;
import kr.or.connect.reservation.dto.BookingHistory;
import kr.or.connect.reservation.dto.TicketHistory;

@Repository
public class MyReservationpageDao {
	private NamedParameterJdbcTemplate jdbc;

	public MyReservationpageDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public List<BookingHistory> selectBookingHistory(String email) {
		List<BookingHistory> ret = Collections.emptyList();
		Map<String, String> params = new HashMap<>();

		try {
			params.put("email", email);
			ret = jdbc.query(MyReservationDaoSqls.SELECT_BOOKING_HISTORY, params,
					BeanPropertyRowMapper.newInstance(BookingHistory.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	public List<TicketHistory> selectTicketHistory(int reservation_info_id) {
		List<TicketHistory> ret = Collections.emptyList();
		Map<String, Integer> params = new HashMap<>();

		try {
			params.put("reservation_info_id", reservation_info_id);
			ret = jdbc.query(MyReservationDaoSqls.SELECT_TICKET_HISTORY, params,
					BeanPropertyRowMapper.newInstance(TicketHistory.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	public int selectReservationCount(String email) {
		try {
			Map<String, String> params = new HashMap<>();
			params.put("email", email);
			return jdbc.queryForObject(MyReservationDaoSqls.SELECT_RESERVATION_COUNT, params, Integer.class);
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	public int updateCancelFlag(int revervationInfoId) {
		int ret = 0;
		Map<String, Integer> params = new HashMap<>();

		try {
			params.put("reservation_info_id", revervationInfoId);
			ret = jdbc.update(MyReservationDaoSqls.UPDATE_CANCEL_FLAG, params);
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		return ret;
	}
}
