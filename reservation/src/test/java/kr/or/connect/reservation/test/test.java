package kr.or.connect.reservation.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;

public class test {

	@Value("${name}")
	private String password;

	@Test
	public void test() {
		System.out.println(password);
	}

}
