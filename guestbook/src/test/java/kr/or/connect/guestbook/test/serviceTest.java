package kr.or.connect.guestbook.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import kr.or.connect.guestbook.config.ApplicationConfig;
import kr.or.connect.guestbook.service.GuestBookService;

public class serviceTest {

	@Test
	public void test() {
		ApplicationContext ac = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		GuestBookService service = ac.getBean(GuestBookService.class);
	}
}
