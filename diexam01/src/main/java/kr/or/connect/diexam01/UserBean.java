package kr.or.connect.diexam01;

public class UserBean {
	/*
	 * UserBean클래스 생성규칙
	 * 
	 * 1. 기본생성자(비어잇는) 작성 2. 필드는 private로 선언 3. 필드의 프로퍼티(getter,setter)를 꼭 작성한다
	 */

	private String name;
	private int age;
	private String country;

	public UserBean() {
	}

	public UserBean(String name, int age, String country) {
		this.name = name;
		this.age = age;
		this.country = country;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
}
