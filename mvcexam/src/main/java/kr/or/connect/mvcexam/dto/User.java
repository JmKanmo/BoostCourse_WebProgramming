package kr.or.connect.mvcexam.dto;

import java.io.File;

public class User {
	private String name;
	private String email;
	private int age;
	private File reviewImg;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", email=" + email + ", age=" + age + "]";
	}

	public File getReviewImg() {
		return reviewImg;
	}

	public void setReviewImg(File reviewImg) {
		this.reviewImg = reviewImg;
	}
}
