package kr.or.connect.reservation.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import kr.or.connect.reservation.config.ApplicationConfig;
import kr.or.connect.reservation.service.MainpageService;

public class test {

	@Test
	public void test() {
		ApplicationContext ac = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		MainpageService service = ac.getBean(MainpageService.class);
		System.out.println(service.getProduct(2,4));
	}
}
