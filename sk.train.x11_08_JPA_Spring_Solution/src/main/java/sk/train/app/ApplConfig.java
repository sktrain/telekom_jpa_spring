package sk.train.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "sk.train.repositories")
public class ApplConfig {


	// Erweiterte Entity-Manager-Factory bereit stellen
	// LocalContainerEntityManagerFactoryBean ist eine Factory für die Factory!!
	// für die Repository-Generierung muss hier scheibar die Namenskonvention "entityManagerFactory()"
	// eingehalten werden !!
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean fb = new LocalContainerEntityManagerFactoryBean();
		fb.setPersistenceUnitName("Muster_JPA_Hibernate_H2_Local");
		return fb;
	}
	

	
	// Transaktions-Unterstützung bereit stellen
	@Bean
	public PlatformTransactionManager transactionManager() {
		final JpaTransactionManager manager = new JpaTransactionManager();
		manager.setEntityManagerFactory(this.entityManagerFactory().getObject());
		return manager;
	}
	

}
