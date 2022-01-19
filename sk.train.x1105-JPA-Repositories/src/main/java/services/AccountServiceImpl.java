package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.transaction.annotation.Transactional;

import domain.AccountDao;
import domain.Account;

@Transactional
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountDao accountDao;
	
	public AccountServiceImpl() {	
		//System.out.println(accountDao.getClass());
	}
	
	@Override
	public void createAccount(int number) {
		this.accountDao.save(new Account(number));
	}
	
	@Override
	public void deleteAccount(int number) {
		final Account account = this.accountDao.findById(number).get();
		if (account.getBalance() != 0)
			throw new RuntimeException("balance must be 0");
		this.accountDao.delete(account);
	}
	@Override
	public Account getAccount(int number) {
		return this.accountDao.findById(number).get();
	}
	@Override
	public Account findAccount(int number) {
		return this.accountDao.findById(number).get();
	}
	@Override
	public List<Account> findAllAccounts() {
		return this.accountDao.findAll();
	}
	@Override
	public void deposit(int number, int amount) {
		if (amount <= 0)
			throw new RuntimeException("bad amount: " + amount);
		final Account account = this.accountDao.findById(number).get();
		account.setBalance(account.getBalance() + amount);
		this.accountDao.save(account);
	}
	@Override
	public void withdraw(int number, int amount) {
		if (amount <= 0)
			throw new RuntimeException("bad amount: " + amount);
		final Account account = this.accountDao.findById(number).get();
		if (amount > account.getBalance())
			throw new RuntimeException("cannot withdraw: " + amount);
		account.setBalance(account.getBalance() - amount);
		this.accountDao.save(account);
	}
	@Override
	public void transfer(int fromNumber, int toNumber, int amount) {
		this.deposit(toNumber, amount);
		this.withdraw(fromNumber, amount);
	}
}
