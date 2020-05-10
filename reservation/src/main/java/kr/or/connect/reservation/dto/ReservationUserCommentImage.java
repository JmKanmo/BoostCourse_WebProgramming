package kr.or.connect.reservation.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
public class ReservationUserCommentImage {
	private int id;
	private int reservationInfoId;
	private int reservationUserCommentId;
	private int fileId;
}
