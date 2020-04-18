package kr.or.connect.reservation.dto;

public class Display {
	private String resrvDate = "";
	private String description = "";
	private String placeName = "";
	private String placeLot = "";
	private String placeStreet = "";
	private String tel = "";
	private String openingHours = "";
	private String saveFileName = "";

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

	public String getOpeningHours() {
		return openingHours;
	}

	public void setOpeningHours(String openingHours) {
		this.openingHours = openingHours;
	}

	@Override
	public String toString() {
		return "Display [resrvDate=" + resrvDate + ", description=" + description + ", placeName=" + placeName
				+ ", placeLot=" + placeLot + ", placeStreet=" + placeStreet + ", tel=" + tel + ", openingHours="
				+ openingHours + ", saveFileName=" + saveFileName + "]";
	}

	public String getSaveFileName() {
		return saveFileName;
	}

	public void setSaveFileName(String saveFileName) {
		this.saveFileName = saveFileName;
	}

	public String getResrvDate() {
		return resrvDate;
	}

	public void setResrvDate(String resrvDate) {
		this.resrvDate = resrvDate;
	}
}
