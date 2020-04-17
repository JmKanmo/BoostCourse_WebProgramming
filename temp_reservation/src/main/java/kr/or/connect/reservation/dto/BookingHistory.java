package kr.or.connect.reservation.dto;

import java.util.List;

public class BookingHistory {
	private int ticketPrice;
	private int ticketCount;
	private String openingTerm = "";
	private String placeName = "";
	private String placeLot = "";
	private String placeStreet = "";
	private int productId;
	private String description = "";
	private int reservationId;
	private int cancelFlag;
	private String reservationDate;
	private List<TicketHistory> ticketHistory;

	public int getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(int ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

	public int getTicketCount() {
		return ticketCount;
	}

	public void setTicketCount(int ticketCount) {
		this.ticketCount = ticketCount;
	}

	public String getOpeningTerm() {
		return openingTerm;
	}

	public void setOpeningTerm(String openingTerm) {
		this.openingTerm = openingTerm;
	}

	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	public String getPlaceLot() {
		return placeLot;
	}

	public void setPlaceLot(String placeLot) {
		this.placeLot = placeLot;
	}

	public String getPlaceStreet() {
		return placeStreet;
	}

	public void setPlaceStreet(String placeStreet) {
		this.placeStreet = placeStreet;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getReservationId() {
		return reservationId;
	}

	public void setReservationId(int reservationId) {
		this.reservationId = reservationId;
	}

	public int getCancelFlag() {
		return cancelFlag;
	}

	public void setCancelFlag(int cancelFlag) {
		this.cancelFlag = cancelFlag;
	}

	public String getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(String reservationDate) {
		this.reservationDate = reservationDate;
	}

	@Override
	public String toString() {
		return "BookingHistory [ticketPrice=" + ticketPrice + ", ticketCount=" + ticketCount + ", openingTerm="
				+ openingTerm + ", placeName=" + placeName + ", placeLot=" + placeLot + ", placeStreet=" + placeStreet
				+ ", productId=" + productId + ", description=" + description + ", reservationId=" + reservationId
				+ ", cancelFlag=" + cancelFlag + ", reservationDate=" + reservationDate + "]";
	}

	public List<TicketHistory> getTicketHistory() {
		return ticketHistory;
	}

	public void setTicketHistory(List<TicketHistory> ticketHistory) {
		this.ticketHistory = ticketHistory;
	}
}
