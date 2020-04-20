package kr.or.connect.reservation.dto;

import lombok.Data;

@Data
public class ProductPrice {
	private int id;
	private String priceTypeName = "";
	private int price;
	private double discountRate;
	private double discountedPrice;
}
