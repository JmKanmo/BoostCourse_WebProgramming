package kr.or.connect.reservation.dto;

import lombok.Data;

@Data
public class TicketHistory {
	private String priceTypeName = "";
	private int price;
	private int count;
}
