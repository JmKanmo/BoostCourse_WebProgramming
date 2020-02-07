package kr.or.connect.guestbook.test;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import kr.or.connect.guestbook.config.ApplicationConfig;
import kr.or.connect.guestbook.dao.LogDao;
import kr.or.connect.guestbook.dto.Log;

public class LogTest {
	@Test
	public void test() {
		ApplicationContext ac = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		LogDao logdao = ac.getBean(LogDao.class);
		Log log = new Log();
		log.setIp("127.0.0.1");
		log.setMethod("insert");
		log.setRegdate(new Date());
		Long ret = 4L;
		assertEquals(ret, logdao.insert(log));
	}
}
