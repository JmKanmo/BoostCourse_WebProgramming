package kr.or.connect.reservation.dto;

import lombok.Data;

@Data
public class Review {
	private String description = "";
	private String id = "";
	private double score;
	private String comment = "";
	private String modify_date = "";
	private int fileId;
}
