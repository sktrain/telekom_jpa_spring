package appl;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import daos.AccountDao;
import daos.jpa.AccountDaoImpl;
import services.AccountService;
import services.AccountServiceImpl;

@Configuration
public class ApplConfig {

	@Bean
	public LocalEntityManagerFactoryBean managerFactory() {
		return new LocalEntityManagerFactoryBean();
	}

	@Bean
	public PlatformTransactionManager transactionManager() {
		final JpaTransactionManager manager = new JpaTransactionManager();
		manager.setEntityManagerFactory(this.managerFactory().getObject());
		return manager;
	}

	@Bean
	public TransactionTemplate transactionTemplate() {
		return new TransactionTemplate(this.transactionManager());
	}

	@Bean
	public AccountDao accountDao() {
		// don't call setEntityManager - this was done via @PersistenceContext
		return new AccountDaoImpl();
	}

	@Bean
	public AccountService accountService() {
		return new AccountServiceImpl(this.accountDao());
	}
	
}
