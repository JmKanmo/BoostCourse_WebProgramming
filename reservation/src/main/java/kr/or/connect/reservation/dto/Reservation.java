package kr.or.connect.reservation.dto;

import java.util.List;

import lombok.Data;

@Data
public class Reservation {
	private int id;
	private int productId;
	private int displayInfoId;
	private String reservationName = "";
	private String reservationTel = "";
	private String reservationEmail = "";
	private String reservationDate = "";
	private int cancelFlag;
	private String createDate = "";
	private String modifyDate = "";
	private List<ReservationPrice> reservationPrice;
}
