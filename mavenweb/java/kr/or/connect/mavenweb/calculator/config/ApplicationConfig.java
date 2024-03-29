package kr.or.connect.mavenweb.calculator.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

@Configuration
@ComponentScan(basePackages = { "kr.or.connect.mavenweb.securityexam.dao",
		"kr.or.connect.mavenweb.securityexam.service", "kr.or.connect.mavenweb.calculator.controller.service" })
@EnableTransactionManagement
public class ApplicationConfig implements TransactionManagementConfigurer {
	// mysql 드라이버 클래스 이름은 "com.mysql.jdbc.Driver"에서 버전 6이후에는
	// "com.mysql.cj.jdbc.Driver"로 변경되었다.
	private String driverClassName = "com.mysql.cj.jdbc.Driver";

	// java.sql.SQLException: Cannot create PoolableConnectionFactory (The server
	// time zone value 'KST' is unrecognized or represents more than one time zone.
	// You must configure either the server or JDBC driver (via the 'serverTimezone'
	// configuration property) to use a more specifc time zone value if you want to
	// utilize time zone support.)
	// DB연결시 위와 같은 오류가 발생한다면 &serverTimezone=UTC 를 url에 붙여줘야 합니다.
	private String url = "jdbc:mysql://localhost:3306/connectdb?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC";
	private String username = "connectuser";
	private String password = "connect123!@#";

	/**
	 * 커넥션 풀과 관련된 Bean을 생성한다.
	 * 
	 * @return
	 */
	@Bean
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(driverClassName);
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);

		return dataSource;
	}

	/**
	 * 트랜잭션 관리자를 생성한다.
	 * 
	 * @return
	 */
	@Bean
	public PlatformTransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}

	@Override
	public PlatformTransactionManager annotationDrivenTransactionManager() {
		return transactionManager();
	}
}