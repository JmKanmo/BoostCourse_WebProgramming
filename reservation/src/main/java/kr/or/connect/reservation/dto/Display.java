package kr.or.connect.reservation.dto;

import lombok.Data;

@Data
public class Display {
	private String resrvDate = "";
	private String description = "";
	private String placeName = "";
	private String placeLot = "";
	private String placeStreet = "";
	private String tel = "";
	private String openingHours = "";
	private int fileId;
}
