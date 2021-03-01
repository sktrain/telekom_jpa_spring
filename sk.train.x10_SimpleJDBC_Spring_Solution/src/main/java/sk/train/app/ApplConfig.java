package sk.train.app;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;


@Configuration
@PropertySource("classpath:db.properties")
public class ApplConfig {
	
	
	//db.properties nutzen
	
	//einfache DriverManagerDataSource nutzen
	@Bean
	public DataSource dataSource(@Value("${db.driver}") String driver, 
			             @Value("${db.url}") String url,
			             @Value("${db.user}") String user, 
			             @Value("${db.password}") String pass) {
		
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(driver);
		dataSource.setUrl(url);
		dataSource.setUsername(user);
		dataSource.setPassword(pass);

		return dataSource;
	}
	
	//JdbcTemplate bereit stellen
	@Bean 
	@Autowired
	public JdbcTemplate template(DataSource dataSource) {
			   return new JdbcTemplate(dataSource);
	}
}
