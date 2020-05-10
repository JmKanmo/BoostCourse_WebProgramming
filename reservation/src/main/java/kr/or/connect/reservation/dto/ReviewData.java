package kr.or.connect.reservation.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
public class ReviewData {
	private int reservationId;
	private int productId;
	private double score;
	private String comment;
	private String fileName;
	private String saveFileName;
	private String contentType;
	private int deleteFlag;
	private final String createDate = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss").format(LocalDateTime.now());
	private final String modifyDate = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss").format(LocalDateTime.now());
}
