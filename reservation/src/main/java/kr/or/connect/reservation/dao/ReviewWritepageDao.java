package kr.or.connect.reservation.dao;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservation.dao.sql.ReviewWritepageDaoSqls;

@Repository
public class ReviewWritepageDao {
	private NamedParameterJdbcTemplate jdbc;
	private DataSource dataSoruce;
	private SimpleJdbcInsert insertAction;

	public ReviewWritepageDao(DataSource dataSource) {
		this.dataSoruce = dataSource;
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public String selectImage(int imageId) {
		String ret = "";

		try {
			Map<String, Integer> params = new HashMap<>();
			params.put("imageId", imageId);
			ret = jdbc.queryForObject(ReviewWritepageDaoSqls.SELECT_IMAGE_BY_ID, params, String.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}
}
