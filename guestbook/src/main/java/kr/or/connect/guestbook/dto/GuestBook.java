package kr.or.connect.guestbook.dto;

import java.util.Date;

public class GuestBook {
	private Long id;
	private String name;
	private String content;
	private Date regdate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getRegDate() {
		return regdate;
	}

	public void setRegDate(Date regdate) {
		this.regdate = regdate;
	}

	@Override
	public String toString() {
		return "GuestBook [id=" + id + ", name=" + name + ", content=" + content + ", regDate=" + regdate + "]";
	}

}
