package app.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

//@XmlRootElement
@Entity
public class Konto {
	
	@Id
	private int kontonummer;
	private int saldo;
	
	public Konto() {
		super();
	}
	
	
	public Konto(int kontonummer, int saldo) {
		super();
		this.kontonummer = kontonummer;
		this.saldo = saldo;
	}


	public int getKontonummer() {
		return kontonummer;
	}
	public void setKontonummer(int kontonummer) {
		this.kontonummer = kontonummer;
	}
	public int getSaldo() {
		return saldo;
	}
	public void setSaldo(int saldo) {
		this.saldo = saldo;
	}
	@Override
	public String toString() {
		return "Konto [kontonummer=" + kontonummer + ", saldo=" + saldo + "]";
	}
	
	

}
