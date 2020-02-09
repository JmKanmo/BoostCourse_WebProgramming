package kr.or.connect.reservation.dao;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservation.dto.Category;
import kr.or.connect.reservation.dto.Product;
import kr.or.connect.reservation.dto.Promotion;

@Repository
public class ReservationDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<Promotion> promotion_Mapper = BeanPropertyRowMapper.newInstance(Promotion.class);
	private RowMapper<Category> category_Mapper = BeanPropertyRowMapper.newInstance(Category.class);
	private RowMapper<Product> product_Mapper = BeanPropertyRowMapper.newInstance(Product.class);

	public ReservationDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public int getProductCount(int categoryId) {
		if (categoryId == 0) {
			// 전체 상품개수 반홤
			return jdbc.queryForObject(ReservationDaoSqls.SELECT_ALL_PRODUCT_COUNT, Collections.emptyMap(),
					Integer.class);
		} else {
			// 카테고리별 상품개수 반환
			Map<String, Integer> params = new HashMap<>();
			params.put("id", categoryId);
			return jdbc.queryForObject(ReservationDaoSqls.SELECT_PRODUCT_COUNT_BY_CATEGORY, params, Integer.class);
		}
	}

	public List<Promotion> selectPromotion() {
		List<Promotion> ret = jdbc.query(ReservationDaoSqls.SELECT_PROMOTIONS, Collections.emptyMap(),
				promotion_Mapper);
		return ret;
	}

	public List<Category> selectCategory() {
		List<Category> ret = jdbc.query(ReservationDaoSqls.SELECT_CATEGORY, Collections.emptyMap(), category_Mapper);
		return ret;
	}

	public List<Product> selectProduct(int categoryId, int turn) {
		Map<String, Integer> params = new HashMap<>();
		List<Product> ret = null;

		if (categoryId == 0) {
			// 전체 상품정보 반환
			params.put("turn", turn);
			ret = jdbc.query(ReservationDaoSqls.SELECT_ALL_PRODUCTS, params, product_Mapper);
		} else {
			// 카테고리별 상품정보 반환
			params.put("id", categoryId);
			params.put("turn", turn);
			ret = jdbc.query(ReservationDaoSqls.SELECT_PRODUCTS_BY_CATEGORY, params, product_Mapper);
		}
		return ret;
	}
}
