package kr.or.connect.reservation.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileInfo {
	private int id;
	private String fileName;
	private String saveFileName;
	private String contentType;
	private int deleteFlag;
	private String createDate;
	private String modifyDate;

	public static class Builder {
		private int id_ = 0;
		private String fileName_ = null;
		private String saveFileName_ = null;
		private String contentType_ = null;
		private int deleteFlag_ = 0;
		private String createDate_ = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss").format(LocalDateTime.now());
		private String modifyDate_ = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss").format(LocalDateTime.now());

		public Builder id_(int value) {
			id_ = value;
			return this;
		}

		public Builder fileName_(String value) {
			fileName_ = value;
			return this;
		}

		public Builder saveFileName_(String value) {
			saveFileName_ = value;
			return this;
		}

		public Builder conetentType_(String value) {
			contentType_ = value;
			return this;
		}

		public Builder deleteFlag_(int value) {
			deleteFlag_ = value;
			return this;
		}

		public FileInfo build() {
			return new FileInfo(this);
		}
	}

	private FileInfo(Builder builder) {
		id = builder.id_;
		fileName = builder.fileName_;
		saveFileName = builder.saveFileName_;
		contentType = builder.contentType_;
		deleteFlag = builder.deleteFlag_;
		createDate = builder.createDate_;
		modifyDate = builder.modifyDate_;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getSaveFileName() {
		return saveFileName;
	}

	public void setSaveFileName(String saveFileName) {
		this.saveFileName = saveFileName;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public int getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(int deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}

	@Override
	public String toString() {
		return "FileInfo [id=" + id + ", fileName=" + fileName + ", saveFileName=" + saveFileName + ", contentType="
				+ contentType + ", deleteFlag=" + deleteFlag + ", createDate=" + createDate + ", modifyDate="
				+ modifyDate + "]";
	}
}
