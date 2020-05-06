package kr.or.connect.reservation.dto;

public class ReservationUserCommentImage {
	private int id;
	private int reservationInfoId;
	private int reservationUserCommentId;
	private int fileId;

	public static class Builder {
		private int id_ = 0;
		private int reservationInfoId_ = 0;
		private int reservationUserCommentId_ = 0;
		private int fileId_ = 0;

		public Builder id_(int value) {
			id_ = value;
			return this;
		}

		public Builder reservationInfoId_(int value) {
			reservationInfoId_ = value;
			return this;
		}

		public Builder reservationUserCommentId_(int value) {
			reservationUserCommentId_ = value;
			return this;
		}

		public Builder fileId_(int value) {
			fileId_ = value;
			return this;
		}

		public ReservationUserCommentImage build() {
			return new ReservationUserCommentImage(this);
		}
	}

	private ReservationUserCommentImage(Builder builder) {
		id = builder.id_;
		reservationInfoId = builder.reservationInfoId_;
		reservationUserCommentId = builder.reservationUserCommentId_;
		fileId = builder.fileId_;
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

	public void setReservationInfoId(int reservationInfoId) {
		this.reservationInfoId = reservationInfoId;
	}

	public int getReservationUserCommentId() {
		return reservationUserCommentId;
	}

	public void setReservationUserCommentId(int reservationUserCommentId) {
		this.reservationUserCommentId = reservationUserCommentId;
	}

	public int getFileId() {
		return fileId;
	}

	public void setFileId(int fileId) {
		this.fileId = fileId;
	}

	@Override
	public String toString() {
		return "ReservationUserCommentImage [id=" + id + ", reservationInfoId=" + reservationInfoId
				+ ", reservationUserCommentId=" + reservationUserCommentId + ", fileId=" + fileId + "]";
	}
}
