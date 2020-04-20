package kr.or.connect.reservation.dto;

import java.util.List;

import lombok.Data;

@Data
public class BookingHistory {
	private int ticketPrice;
	private int ticketCount;
	private String placeName = "";
	private String placeLot = "";
	private String placeStreet = "";
	private int productId;
	private String description = "";
	private int reservationId;
	private int cancelFlag;
	private String reservationDate;
	private List<TicketHistory> ticketHistory;
}
