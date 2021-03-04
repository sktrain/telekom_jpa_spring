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
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.TransactionTemplate;

import sk.train.dao.EmpService;

@Configuration
@EnableTransactionManagement
//@PropertySource("classpath:db.properties")
public class ApplConfig {

	// db.properties nutzen

	// einfache DriverManagerDataSource nutzen
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

	// Erweiterte Entity-Manager-Factory bereit stellen
	// LocalContainerEntityManagerFactoryBean ist eine Factory für die Factory!!
	@Bean
	public LocalContainerEntityManagerFactoryBean getEntityManagerFactory() {
		LocalContainerEntityManagerFactoryBean fb = new LocalContainerEntityManagerFactoryBean();
		fb.setPersistenceUnitName("Muster_JPA_Hibernate_H2_Local");
		return fb;
	}
	

	
	// Transaktions-Unterstützung bereit stellen
	@Bean
	public PlatformTransactionManager transactionManager() {
		final JpaTransactionManager manager = new JpaTransactionManager();
		manager.setEntityManagerFactory(this.getEntityManagerFactory().getObject());
		return manager;
	}
	
	
	@Bean
	public TransactionTemplate transactionTemplate() {
		return new TransactionTemplate(this.transactionManager());
	}

//	@Bean
//	public AccountDao accountDao() {
//		// don't call setEntityManager - this was done via @PersistenceContext
//		return new AccountDaoImpl();
//	}

	// EmpService bereit stellen
	@Bean
	public EmpService getEmpService() {
		return new EmpService();
	}
}
