package kr.or.connect.reservation.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReviewData {
	private int reservationId;
	private int productId;
	private double score;
	private String comment;
	private String fileName;
	private String saveFileName;
	private String contentType;
	private int deleteFlag;
	private String createDate;
	private String modifyDate;

	public static class Builder {
		private int reservationId_ = 0;
		private int productId_ = 0;
		private double score_ = 0.;
		private String comment_ = null;
		private String fileName_ = null;
		private String saveFileName_ = null;
		private String contentType_ = null;
		private int deleteFlag_ = 0;
		private String createDate_ = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss").format(LocalDateTime.now());
		private String modifyDate_ = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss").format(LocalDateTime.now());

		public Builder reservationId_(int value) {
			reservationId_ = value;
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

		public Builder fileName_(String value) {
			fileName_ = value;
			return this;
		}

		public Builder saveFileName_(String value) {
			saveFileName_ = value;
			return this;
		}

		public Builder conetentType_(String value) {
			contentType_ = value;
			return this;
		}

		public Builder deleteFlag_(int value) {
			deleteFlag_ = value;
			return this;
		}

		public ReviewData build() {
			return new ReviewData(this);
		}
	}

	private ReviewData(Builder builder) {
		reservationId = builder.reservationId_;
		productId = builder.productId_;
		score = builder.score_;
		comment = builder.comment_;
		fileName = builder.fileName_;
		saveFileName = builder.saveFileName_;
		contentType = builder.contentType_;
		deleteFlag = builder.deleteFlag_;
		createDate = builder.createDate_;
		modifyDate = builder.modifyDate_;
	}

	public int getReservationId() {
		return reservationId;
	}

	public void setReservationId(int reservationId) {
		this.reservationId = reservationId;
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

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getSaveFileName() {
		return saveFileName;
	}

	public void setSaveFileName(String saveFileName) {
		this.saveFileName = saveFileName;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public int getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(int deleteFlag) {
		this.deleteFlag = deleteFlag;
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
		return "ReviewData [reservationId=" + reservationId + ", productId=" + productId + ", score=" + score
				+ ", comment=" + comment + ", fileName=" + fileName + ", saveFileName=" + saveFileName
				+ ", contentType=" + contentType + ", deleteFlag=" + deleteFlag + ", createDate=" + createDate
				+ ", modifyDate=" + modifyDate + "]";
	}
}
