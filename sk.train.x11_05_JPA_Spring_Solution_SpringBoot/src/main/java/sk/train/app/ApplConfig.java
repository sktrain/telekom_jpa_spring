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

import app.dao.EmpService;

@Configuration
@EnableTransactionManagement
//@PropertySource("classpath:db.properties")
public class ApplConfig {



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
	
	
	


	// EmpService bereit stellen
	@Bean
	public EmpService getEmpService() {
		return new EmpService();
	}
}
