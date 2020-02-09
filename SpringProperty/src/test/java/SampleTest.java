import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tistory.stove99.SampleBean;


public class SampleTest {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("config/spring/*-context.xml");
		
		SampleBean sample = context.getBean(SampleBean.class);
		
		// test
		System.out.println(sample.val("sample.prop1"));
		
		// value1 : test abc, value2 : 우쭈쭈~
		System.out.println(sample);
	}
}
