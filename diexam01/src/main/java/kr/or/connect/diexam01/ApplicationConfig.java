package kr.or.connect.diexam01;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {
	@Bean
	public Car car(Engine e) {
		return new Car(e);
	}

	@Bean
	public Engine engine() {
		return new Engine();
	}
}
