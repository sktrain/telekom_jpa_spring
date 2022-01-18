package services;

import java.util.List;

import domain.Account;

public interface AccountService {
	public abstract void createAccount(int number);
	public abstract void deleteAccount(int number);
	public abstract Account getAccount(int number);
	public abstract Account findAccount(int number);
	public abstract List<Account> findAllAccounts();
	public abstract void deposit(int number, int amount);
	public abstract void withdraw(int number, int amount);
	public abstract void transfer(int fromNumber, int toNumber, int amount);
}
