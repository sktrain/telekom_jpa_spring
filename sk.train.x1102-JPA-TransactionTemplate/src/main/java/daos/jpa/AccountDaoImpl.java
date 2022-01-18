package daos.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.dao.DataRetrievalFailureException;

import daos.AccountDao;
import domain.Account;

public class AccountDaoImpl implements AccountDao {

	private EntityManager manager;

	@PersistenceContext
	public void setEntityManager(EntityManager manager) {
		this.manager = manager;
	}
	
	@Override
	public void insert(Account account) {
		this.manager.persist(account);
	}

	@Override
	public void update(Account account) {
		this.manager.merge(account);
	}

	@Override
	public void delete(Account account) {
		this.manager.merge(account);
		this.manager.remove(account);
	}

	@Override
	public Account get(int number) {
		final Account account = this.find(number);
		if (account == null)
			throw new DataRetrievalFailureException("account with number " + number + " not found");
		return account;
	}

	@Override
	public Account find(int number) {
		return this.manager.find(Account.class, number);
	}

	@Override
	public List<Account> findAll() {
		return this.manager.createQuery("select a from Account a", Account.class).getResultList();
	}
}
