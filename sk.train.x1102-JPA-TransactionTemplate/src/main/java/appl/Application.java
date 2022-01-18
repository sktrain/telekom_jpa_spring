package appl;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import domain.Account;
import services.AccountService;

public class Application {

	public static void main(String[] args) {
		
		try (final AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext("appl")) {
					
			final TransactionTemplate tt = ctx.getBean(TransactionTemplate.class);
			final AccountService accountService = ctx.getBean(AccountService.class);
			
			System.err.println("************************* Before ************************");
			List<Account> list = findAllAccounts(tt, accountService);
			list.forEach(System.err::println);
			
			createAccount(tt, accountService, 4711);
			createAccount(tt, accountService, 4712);
			deposit(tt, accountService, 4711, 5000);
			deposit(tt, accountService, 4712, 6000);
			withdraw(tt, accountService, 4711, 2000);
			transfer(tt, accountService, 4711, 4712, 500);
			try {
				transfer(tt, accountService, 4711, 4712, 50000);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

			System.err.println("************************* After ************************");
			list = findAllAccounts(tt, accountService);
			list.forEach(System.err::println);
			
		}
	}

	private static void createAccount(TransactionTemplate tt, AccountService accountService, int number) {
		tt.execute(new TransactionCallbackWithoutResult() {
			@Override
			public void doInTransactionWithoutResult(TransactionStatus ts) {
				accountService.createAccount(number);
			}
		});
	}

	private static void deposit(TransactionTemplate tt, AccountService accountService, int number, int amount) {
		tt.execute(new TransactionCallbackWithoutResult() {
			@Override
			public void doInTransactionWithoutResult(TransactionStatus ts) {
				accountService.deposit(number, amount);
			}
		});
	}

	private static void withdraw(TransactionTemplate tt, AccountService accountService, int number, int amount) {
		tt.execute(new TransactionCallbackWithoutResult() {
			@Override
			public void doInTransactionWithoutResult(TransactionStatus ts) {
				accountService.withdraw(number, amount);
			}
		});
	}

	private static void transfer(TransactionTemplate tt, AccountService accountService, int fromNumber, int toNumber,
			int amount) {
		tt.execute(new TransactionCallbackWithoutResult() {
			@Override
			public void doInTransactionWithoutResult(TransactionStatus ts) {
				accountService.transfer(fromNumber, toNumber, amount);
			}
		});
	}

	private static List<Account> findAllAccounts(TransactionTemplate tt, AccountService accountService) {
		return tt.execute(new TransactionCallback<List<Account>>() {
			@Override
			public List<Account> doInTransaction(TransactionStatus ts) {
				return accountService.findAllAccounts();
			}
		});
	}
}
