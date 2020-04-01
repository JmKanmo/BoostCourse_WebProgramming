package kr.or.connect.reservation.dao;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservation.dao.sql.ReservepageDaoSqls;
import kr.or.connect.reservation.dto.Display;
import kr.or.connect.reservation.dto.ProductPrice;

@Repository
public class ReservepageDao {
	private NamedParameterJdbcTemplate jdbc;

	public ReservepageDao(DataSource dataSource) {
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
}
