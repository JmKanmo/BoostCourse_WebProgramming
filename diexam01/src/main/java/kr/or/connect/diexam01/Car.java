package kr.or.connect.diexam01;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Car {
	private final Engine v8;

	@Autowired
	public Car(Engine v8) {
		System.out.println("Car 생성자 동작");
		this.v8 = v8;
	}

	public Engine getEngine() {
		return v8;
	}

	public void run() {
		System.out.println("엔진을 이용하여 달립니다");
		v8.exec();
	}
}
