package kr.or.connect.reservation.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReservationUserComment {
	private int id;
	private int reservationInfoId;
	private int productId;
	private double score;
	private String comment;
	private String createDate;
	private String modifyDate;

	public static class Builder {
		private int id_ = 0;
		private int reservationInfoId_ = 0;
		private int productId_ = 0;
		private double score_ = 0.;
		private String comment_ = null;
		private String createDate_ = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss").format(LocalDateTime.now());
		private String modifyDate_ = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss").format(LocalDateTime.now());

		public Builder Id_(int value) {
			id_ = value;
			return this;
		}

		public Builder reservationInfoId_(int value) {
			reservationInfoId_ = value;
			return this;
		}

		public Builder productId_(int value) {
			productId_ = value;
			return this;
		}

		public Builder score_(double value) {
			score_ = value;
			return this;
		}

		public Builder comment_(String value) {
			comment_ = value;
			return this;
		}

		public ReservationUserComment build() {
			return new ReservationUserComment(this);
		}
	}

	private ReservationUserComment(Builder builder) {
		id = builder.id_;
		reservationInfoId = builder.reservationInfoId_;
		productId = builder.productId_;
		score = builder.score_;
		comment = builder.comment_;
		createDate = builder.createDate_;
		modifyDate = builder.modifyDate_;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getReservationInfoId() {
		return reservationInfoId;
	}

	public void setReservationId(int reservationInfoId) {
		this.reservationInfoId = reservationInfoId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}

	@Override
	public String toString() {
		return "ReservationUserComment [id=" + id + ", reservationInfoId=" + reservationInfoId + ", productId="
				+ productId + ", score=" + score + ", comment=" + comment + ", createDate=" + createDate
				+ ", modifyDate=" + modifyDate + "]";
	}
}
