package kr.or.connect.diexam01;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationContextExam01 {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		System.out.println("초기화완료!");
		UserBean userBean = (UserBean) ac.getBean("userBean");
		userBean.setAge(125);
		userBean.setName("jmkanmo");
		userBean.setCountry("korea");

		UserBean userBean1 = (UserBean) ac.getBean("userBean");
		userBean1.setAge(55);
		userBean1.setName("jmmes");
		userBean1.setCountry("US");

		System.out.println(userBean.getName() + " " + userBean.getAge() + " " + userBean.getCountry());
		System.out.println(userBean1.getName() + " " + userBean1.getAge() + " " + userBean1.getCountry());
	}
}
