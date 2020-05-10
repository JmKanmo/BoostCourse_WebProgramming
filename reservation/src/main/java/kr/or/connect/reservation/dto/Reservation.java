package kr.or.connect.reservation.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
	private final String createDate = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss").format(LocalDateTime.now());
	private final String modifyDate = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss").format(LocalDateTime.now());
	private List<ReservationPrice> reservationPrice;
}
