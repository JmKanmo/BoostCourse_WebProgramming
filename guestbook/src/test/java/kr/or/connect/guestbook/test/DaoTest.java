package kr.or.connect.guestbook.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import kr.or.connect.guestbook.config.ApplicationConfig;
import kr.or.connect.guestbook.dao.GuestBookDao;

public class DaoTest {

	@Test
	public void test() {
		// TODO Auto-generated method stub
		ApplicationContext ac = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		GuestBookDao dao = ac.getBean(GuestBookDao.class);
		System.out.println(dao.selectCount());
	}
}
