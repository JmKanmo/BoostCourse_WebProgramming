package kr.or.connect.reservation.test;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

public class test {

	@Test
	public void test() throws ParseException {
		RestTemplate restTemplate = new RestTemplate();
		String ret = restTemplate.getForObject("http://localhost:8080/reservation/api/categories", String.class);
		JSONParser parser = new JSONParser();
		JSONObject jsonObj = (JSONObject) parser.parse(ret);
		System.out.println(jsonObj);
	}
}
