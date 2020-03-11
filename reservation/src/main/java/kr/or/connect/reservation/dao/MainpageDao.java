package kr.or.connect.reservation.dao;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservation.dao.sql.MainpageDaoSqls;
import kr.or.connect.reservation.dto.Category;
import kr.or.connect.reservation.dto.Product;
import kr.or.connect.reservation.dto.Promotion;

@Repository
public class MainpageDao {
	private NamedParameterJdbcTemplate jdbc;

	public MainpageDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public int getProductCount(int categoryId) {
		int ret = 0;

		try {
			if (categoryId == 0) {
				// 전체 상품개수 반홤
				ret = jdbc.queryForObject(MainpageDaoSqls.SELECT_ALL_PRODUCT_COUNT, Collections.emptyMap(),
						Integer.class);
			} else {
				// 카테고리별 상품개수 반환
				Map<String, Integer> params = new HashMap<>();
				params.put("id", categoryId);
				ret = jdbc.queryForObject(MainpageDaoSqls.SELECT_PRODUCT_COUNT_BY_CATEGORY, params, Integer.class);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	public List<Promotion> selectPromotion() {
		List<Promotion> ret = Collections.emptyList();

		try {
			ret = jdbc.query(MainpageDaoSqls.SELECT_PROMOTIONS, Collections.emptyMap(),
					BeanPropertyRowMapper.newInstance(Promotion.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	public List<Category> selectCategory() {
		List<Category> ret = Collections.emptyList();

		try {
			ret = jdbc.query(MainpageDaoSqls.SELECT_CATEGORY, Collections.emptyMap(),
					BeanPropertyRowMapper.newInstance(Category.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	public List<Product> selectProduct(int categoryId, int turn, int limit) {
		Map<String, Integer> params = new HashMap<>();
		List<Product> ret = Collections.emptyList();

		try {
			if (categoryId == 0) {
				// 전체 상품정보 반환
				params.put("turn", turn);
				params.put("cnt", limit);
				ret = jdbc.query(MainpageDaoSqls.SELECT_ALL_PRODUCTS, params,
						BeanPropertyRowMapper.newInstance(Product.class));
			} else {
				// 카테고리별 상품정보 반환
				params.put("id", categoryId);
				params.put("turn", turn);
				params.put("cnt", limit);
				ret = jdbc.query(MainpageDaoSqls.SELECT_PRODUCTS_BY_CATEGORY, params,
						BeanPropertyRowMapper.newInstance(Product.class));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}
}
