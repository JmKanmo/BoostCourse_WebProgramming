package kr.or.connect.reservation.dto;

public class Review {
	private String description;
	private String id;
	private double score;
	private String comment;
	private String modify_date;
	private String fileURL;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getModify_date() {
		return modify_date;
	}

	public void setModify_date(String modify_date) {
		this.modify_date = modify_date;
	}

	public String getFileURL() {
		return fileURL;
	}

	public void setFileURL(String fileURL) {
		this.fileURL = fileURL;
	}

	@Override
	public String toString() {
		return "Review [description=" + description + ", id=" + id + ", score=" + score + ", comment=" + comment
				+ ", modify_date=" + modify_date + ", fileURL=" + fileURL + "]";
	}
}
