package kr.or.connect.reservation.test;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import jdk.nashorn.internal.ir.annotations.Ignore;

public class test {

	@Ignore
	public void test1() throws ParseException {
		RestTemplate restTemplate = new RestTemplate();
		String ret = restTemplate.getForObject("http://localhost:8080/reservation/api/categories", String.class);
		JSONParser parser = new JSONParser();
		JSONObject jsonObj = (JSONObject) parser.parse(ret);
		List<Map<String, Object>> list = (List<Map<String, Object>>) jsonObj.get("categories");

		for (Map<String, Object> item : list) {
			String name = (String) item.get("name");
			Long id = (Long) item.get("id");
			System.out.println(name);
			System.out.println(id);
		}
	}

	@Ignore
	public void test2() throws ParseException {
		RestTemplate restTemplate = new RestTemplate();
		String ret = restTemplate.getForObject("http://localhost:8080/reservation/api/promotions", String.class);
		JSONParser parser = new JSONParser();
		JSONObject jsonObj = (JSONObject) parser.parse(ret);
		List<Map<String, Object>> list = (List<Map<String, Object>>) jsonObj.get("promotions");

		for (Map<String, Object> item : list) {
			System.out.println(item.get("saveFileName"));
		}
	}

	@Test
	public void test3() throws ParseException, SQLException {
		RestTemplate restTemplate = new RestTemplate();
		String ret = restTemplate.getForObject("http://localhost:8080/myreservationpage/api/history", String.class);
		JSONParser parser = new JSONParser();
		JSONObject jsonObj = (JSONObject) parser.parse(ret);		
		System.out.println(jsonObj);
	}
}
