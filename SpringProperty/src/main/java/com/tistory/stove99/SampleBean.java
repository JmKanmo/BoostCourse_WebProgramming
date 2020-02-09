package com.tistory.stove99;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SampleBean {
	// Spring EL 을 쓰기 때문에 자유롭게 메소드 호출도 가능함. String 의 concat 메소드 호출
	@Value("#{prop['sample.prop1'].concat(' abc')}") private String value1;
	@Value("#{prop['sample.prop2']}") private String value2;
	
	
	
	// util:properties 로 생성된 빈은 java.util.Properties 의 인스턴스이기 때문에
	// 요렇게 Autowired 해서 쓸 수 있다.
	@Autowired Properties prop;
	
	
	
	
	public String val(String key){
		return prop.getProperty(key);
	}
	
	public String toString(){
		return String.format("value1 : %s, value2 : %s", value1, value2);
	}
}
