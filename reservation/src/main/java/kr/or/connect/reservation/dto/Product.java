package kr.or.connect.reservation.dto;

import lombok.Data;

@Data
public class Product {
	private int id;
	private String description = "";
	private String placeName = "";
	private int displayInfoId;
	private String content = "";
	private String saveFileName = "";
}
