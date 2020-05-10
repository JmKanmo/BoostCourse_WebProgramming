package kr.or.connect.reservation.dao;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservation.dao.sql.ReviewWritepageDaoSqls;
import kr.or.connect.reservation.dto.FileInfo;
import kr.or.connect.reservation.dto.ReservationUserComment;
import kr.or.connect.reservation.dto.ReservationUserCommentImage;
import kr.or.connect.reservation.dto.ReviewData;

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
			return null;
		}
		return ret;
	}

	public int insertReservationUserCommentImage(int reservationInfoId, int reservationUserCommentId, int fileId) {
		int ret = 0;

		try {
			ReservationUserCommentImage reservationUserCommentImage = ReservationUserCommentImage.builder()
					.reservationInfoId(reservationInfoId).reservationUserCommentId(reservationUserCommentId)
					.fileId(fileId).build();

			SqlParameterSource params = new BeanPropertySqlParameterSource(reservationUserCommentImage);
			this.insertAction = new SimpleJdbcInsert(this.dataSoruce).withTableName("reservation_user_comment_image")
					.usingGeneratedKeyColumns("id");
			ret = this.insertAction.executeAndReturnKey(params).intValue();
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		return ret;
	}

	public int insertFileInfo(ReviewData reviewData) {
		int ret = 0;

		try {
			FileInfo fileInfo = new FileInfo.Builder().fileName_(reviewData.getFileName())
					.saveFileName_(reviewData.getSaveFileName()).conetentType_(reviewData.getContentType())
					.deleteFlag_(reviewData.getDeleteFlag()).build();

			SqlParameterSource params = new BeanPropertySqlParameterSource(fileInfo);
			this.insertAction = new SimpleJdbcInsert(this.dataSoruce).withTableName("file_info")
					.usingGeneratedKeyColumns("id");
			ret = this.insertAction.executeAndReturnKey(params).intValue();
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		return ret;
	}

	public int insertReservationUserComment(ReviewData reviewData) {
		int ret = 0;
		try {
			ReservationUserComment reservationUserComment = ReservationUserComment.builder()
					.productId(reviewData.getProductId()).reservationInfoId((reviewData.getReservationId()))
					.score(reviewData.getScore()).comment(reviewData.getComment()).build();

			SqlParameterSource params = new BeanPropertySqlParameterSource(reservationUserComment);
			this.insertAction = new SimpleJdbcInsert(this.dataSoruce).withTableName("reservation_user_comment")
					.usingGeneratedKeyColumns("id");
			ret = this.insertAction.executeAndReturnKey(params).intValue();
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		return ret;
	}
}
