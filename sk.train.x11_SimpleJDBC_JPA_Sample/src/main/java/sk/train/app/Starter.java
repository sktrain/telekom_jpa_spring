package sk.train.app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import model.Konto;



public class Starter {

	public static void main(String[] args) {

				EntityManagerFactory emf = Persistence.createEntityManagerFactory("sk.train.x11_SimpleJDBC_JPA_Sample");
				EntityManager em = emf.createEntityManager();
				
				
				//Create: d.h. persistieren
				
				EntityTransaction tran = em.getTransaction();
				try {
					tran.begin();
						Konto k1 = new Konto(); k1.setKontonummer(1);k1.setSaldo(1000);
						em.persist(k1);
						Konto k2 = new Konto(); k2.setKontonummer(2);k2.setSaldo(2000);
						em.persist(k2);
					tran.commit();
				} catch (Exception e) {
					e.printStackTrace();
					tran.rollback();
				}
				
				//Delete: d.h. wieder löschen
				try {
					tran.begin();
					Konto k = em.find(Konto.class, 1); 
					em.remove(k);
					k = em.find(Konto.class,  2);
					em.remove(k);
					tran.commit();
				} catch (Exception e) {
					e.printStackTrace();
					tran.rollback();
				}
				
				emf.close();
			
	}

}
