package domain;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Account implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	private int number;

	private int balance;

	public Account() {
	}
	public Account(int number) {
		this.number = number;
	}
	public Account(int number, int balance) {
		this.number = number;
		this.balance = balance;
	}
	
	public int getNumber() {
		return this.number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	
	public int getBalance() {
		return this.balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	
	@Override
	public String toString() {
		return this.getClass().getName() + " [" + this.number + ", " + this.balance + "]";
	}
}
