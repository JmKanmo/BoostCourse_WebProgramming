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

import kr.or.connect.reservation.dao.sql.DetailpageDaoSqls;
import kr.or.connect.reservation.dto.Product;
import kr.or.connect.reservation.dto.Promotion;
import kr.or.connect.reservation.dto.Review;
import kr.or.connect.reservation.dto.ReviewAvgCnt;

@Repository
public class DetailpageDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<Promotion> promotionMapper = BeanPropertyRowMapper.newInstance(Promotion.class);
	private RowMapper<Product> productMapper = BeanPropertyRowMapper.newInstance(Product.class);
	private RowMapper<ReviewAvgCnt> reviewAvgCntMapper = BeanPropertyRowMapper.newInstance(ReviewAvgCnt.class);
	private RowMapper<Review> reviewMapper = BeanPropertyRowMapper.newInstance(Review.class);

	public DetailpageDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public List<Product> selectProduct(int productId) {
		Map<String, Integer> params = new HashMap<>();
		List<Product> ret = Collections.emptyList();

		try {
			params.put("productId", productId);
			ret = jdbc.query(DetailpageDaoSqls.SELECT_PRODUCT_BY_ID, params, productMapper);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	public List<Promotion> selectPromotion(int productId) {
		Map<String, Integer> params = new HashMap<>();
		List<Promotion> ret = Collections.emptyList();

		try {
			params.put("productId", productId);
			ret = jdbc.query(DetailpageDaoSqls.SELECT_PROMOTION_BY_ID, params, promotionMapper);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	public int getEtcImageCount(int productId) {
		int ret = 0;
		Map<String, Integer> params = new HashMap<>();

		try {
			params.put("productId", productId);
			ret = jdbc.queryForObject(DetailpageDaoSqls.ETC_IMAGE_COUNT_BY_ID, params, Integer.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	public ReviewAvgCnt getUserReviewAvgCnt(int productId) {
		ReviewAvgCnt ret = new ReviewAvgCnt();
		Map<String, Integer> params = new HashMap<>();

		try {
			params.put("productId", productId);
			ret = jdbc.queryForObject(DetailpageDaoSqls.USER_REVIEW_AVG_CNT, params, reviewAvgCntMapper);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	public List<Review> selectReview(int productId) {
		Map<String, Integer> params = new HashMap<>();
		List<Review> ret = Collections.emptyList();

		try {
			params.put("productId", productId);
			ret = jdbc.query(DetailpageDaoSqls.SELECT_USER_RIVIEW, params, reviewMapper);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}
}
