package kr.or.connect.reservation.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

@Configuration
@EnableTransactionManagement
public class DBConfig implements TransactionManagementConfigurer {
	private Environment env;

	public DBConfig(Environment env) {
		this.env = env;
	}

	@Bean
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(byEnvironment("datasource.mysql.driver"));
		dataSource.setUrl(byEnvironment("datasource.mysql.url"));
		dataSource.setUsername(byEnvironment("datasource.mysql.user"));
		dataSource.setPassword(byEnvironment("datasource.mysql.password"));
		return dataSource;
	}

	@Override
	public PlatformTransactionManager annotationDrivenTransactionManager() {
		return transactionManger();
	}

	@Bean
	public PlatformTransactionManager transactionManger() {
		return new DataSourceTransactionManager(dataSource());
	}

	private String byEnvironment(String before) {
		String key = before + "." + env.getProperty("environment");
		return env.getProperty(key);
	}
}