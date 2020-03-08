package kr.or.connect.reservation.dto;

public class Product {
	private int id;
	private String description = "";
	private String placeName = "";
	private String content = "";
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSaveFileName() {
		return saveFileName;
	}

	public void setSaveFileName(String saveFileName) {
		this.saveFileName = saveFileName;
	}

	@Override
	public String toString() {
		return "Product [description=" + description + ", placeName=" + placeName + ", content=" + content
				+ ", saveFileName=" + saveFileName + "]";
	}
}
