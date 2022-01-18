package daos;

import java.util.List;

import domain.Account;

public interface AccountDao {
	public abstract void insert(Account account);
	public abstract void update(Account account);
	public abstract void delete(Account account);
	public abstract Account get(int number);
	public abstract Account find(int number);
	public abstract List<Account> findAll();
}
