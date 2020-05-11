package kr.or.connect.mavenweb.calculator.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = { "kr.or.connect.mavenweb.calculator.controller.service" })
public class ApplicationConfig {
}