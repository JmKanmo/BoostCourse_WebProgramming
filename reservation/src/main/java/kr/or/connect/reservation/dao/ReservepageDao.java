package kr.or.connect.reservation.dao;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservation.dao.sql.ReservepageDaoSqls;
import kr.or.connect.reservation.dto.Display;
import kr.or.connect.reservation.dto.ProductPrice;
import kr.or.connect.reservation.dto.Reservation;
import kr.or.connect.reservation.dto.ReservationPrice;

@Repository
public class ReservepageDao {
	private NamedParameterJdbcTemplate jdbc;
	private DataSource dataSoruce;
	private SimpleJdbcInsert insertAction;

	public ReservepageDao(DataSource dataSource) {
		this.dataSoruce = dataSource;
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public Display selectDisplayInfo(int productId, int displayInfoId) {
		Display ret = new Display();
		Map<String, Integer> param = new HashMap<>();

		try {
			param.put("productId", productId);
			param.put("displayInfoId", displayInfoId);
			ret = jdbc.queryForObject(ReservepageDaoSqls.SELECT_DISPLAY_INFO, param,
					BeanPropertyRowMapper.newInstance(Display.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	public List<ProductPrice> selectProductPriceInfo(int productId) {
		List<ProductPrice> ret = Collections.emptyList();
		Map<String, Object> param = new HashMap<>();

		try {
			param.put("productId", productId);
			ret = jdbc.query(ReservepageDaoSqls.SELECT_PRODUCT_PRICE_INFO, param,
					BeanPropertyRowMapper.newInstance(ProductPrice.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	private int insertReservationPrice(List<ReservationPrice> list, int reservationInfoId) {
		try {
			this.insertAction = new SimpleJdbcInsert(this.dataSoruce).withTableName("reservation_info_price")
					.usingGeneratedKeyColumns("id");
			for (ReservationPrice reservationPrice : list) {
				if (reservationPrice.getCount() > 0) {
					reservationPrice.setReservationInfoId(reservationInfoId);
					SqlParameterSource params = new BeanPropertySqlParameterSource(reservationPrice);
					this.insertAction.executeAndReturnKey(params).intValue();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		return 1;
	}

	public int insertReservation(Reservation reservation) {
		int ret = 0;

		try {
			SqlParameterSource params = new BeanPropertySqlParameterSource(reservation);
			this.insertAction = new SimpleJdbcInsert(this.dataSoruce).withTableName("reservation_info")
					.usingGeneratedKeyColumns("id");
			ret = this.insertAction.executeAndReturnKey(params).intValue();
			insertReservationPrice(reservation.getReservationPrice(), ret);
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		return ret;
	}
}
