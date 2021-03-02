package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the KONTO database table.
 * 
 */
@Entity
@Table(schema="HR")
@NamedQuery(name="Konto.findAll", query="SELECT k FROM Konto k")
public class Konto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int kontonummer;

	private int saldo;

	public Konto() {
	}

	public int getKontonummer() {
		return this.kontonummer;
	}

	public void setKontonummer(int kontonummer) {
		this.kontonummer = kontonummer;
	}

	public int getSaldo() {
		return this.saldo;
	}

	public void setSaldo(int saldo) {
		this.saldo = saldo;
	}

}