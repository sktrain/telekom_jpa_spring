package sk.train.app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import sk.train.dao.EmpService;


@Configuration
//@PropertySource("classpath:db.properties")
public class ApplConfig {
	
	
	//db.properties nutzen
	
	//einfache DriverManagerDataSource nutzen
//	@Bean
//	public DataSource dataSource(@Value("${db.driver}") String driver, 
//			             @Value("${db.url}") String url,
//			             @Value("${db.user}") String user, 
//			             @Value("${db.password}") String pass) {
//		
//		DriverManagerDataSource dataSource = new DriverManagerDataSource();
//		dataSource.setDriverClassName(driver);
//		dataSource.setUrl(url);
//		dataSource.setUsername(user);
//		dataSource.setPassword(pass);
//
//		return dataSource;
//	}
	
	//Entity-Manager bereit stellen
	@Bean
	public EntityManager getEntityManager() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Muster_JPA_Hibernate_H2_Local");
		 EntityManager em = emf.createEntityManager();
		 return em;
	}
	
	//EmpService bereit stellen
	@Bean 
	public EmpService getEmpService() {
		return new EmpService(getEntityManager());
	}
	
	
		
//	//JdbcTemplate bereit stellen
//	@Bean 
//	@Autowired
//	public JdbcTemplate template(DataSource dataSource) {
//			   return new JdbcTemplate(dataSource);
//	}
}
