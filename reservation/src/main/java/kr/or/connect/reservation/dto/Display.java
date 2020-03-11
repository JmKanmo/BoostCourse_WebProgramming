package kr.or.connect.reservation.dto;

public class Display {
	private String description = "";
	private String placeName = "";
	private String placeLot = "";
	private String placeStreet = "";
	private String tel = "";

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@Override
	public String toString() {
		return "Display [description=" + description + ", placeName=" + placeName + ", placeLot=" + placeLot
				+ ", placeStreet=" + placeStreet + ", tel=" + tel + "]";
	}
}
