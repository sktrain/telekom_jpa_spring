package appl;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import domain.Account;
import domain.AccountDao;
import services.AccountService;

public class Application {

	public static void main(String[] args) {
		
		try (final AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(ApplConfig.class)) {
			
			AccountDao dao = ctx.getBean(AccountDao.class);
			System.err.println(dao);
			
			//final TransactionTemplate tt = ctx.getBean(TransactionTemplate.class);
			final AccountService accountService = ctx.getBean(AccountService.class);
			
			System.err.println("************************* Before ************************");
			List<Account> list = accountService.findAllAccounts();
			list.forEach(System.err::println);
			
			accountService.createAccount(4711);
			accountService.createAccount(4712);
			accountService.deposit(4711, 5000);
			accountService.deposit(4712, 6000);
			
			accountService.withdraw(4711, 2000);
			accountService.transfer(4711, 4712, 500);
			try {
				accountService.transfer(4711, 4712, 50000);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

			System.err.println("************************* After ************************");
			list = accountService.findAllAccounts();
			list.forEach(System.err::println);	
		
			
		}
	}

	
}
