package kr.or.connect.mavenweb.securityexam.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.connect.mavenweb.calculator.config.ApplicationConfig;
import kr.or.connect.mavenweb.calculator.config.SecurityConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ApplicationConfig.class, SecurityConfig.class })
public class PasswordEncoderTest {
	@Autowired
	PasswordEncoder passwordEncoder;

	@Test
	public void passwordEncode() throws Exception {
		System.out.println(passwordEncoder.encode("1234"));
	}

	@Test
	public void passwordTest() throws Exception {
		String encodePasswd = "$2a$10$GeoP4hcHo1dsBkQuyxJ3qe9SI5F2orvfrHHUcQ3Ka643LLMolcHHG";
		String password = "1234";
		boolean test = passwordEncoder.matches(password, encodePasswd);
		System.out.println(test);
	}

}