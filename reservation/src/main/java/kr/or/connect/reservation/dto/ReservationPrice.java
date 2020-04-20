package kr.or.connect.reservation.dto;

import lombok.Data;

@Data
public class ReservationPrice {
	private int id;
	private int reservationInfoId;
	private int productPriceId;
	private int count;
}
