package sk.train.client;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import sk.train.dao.EmpService;
import sk.train.model.Department;
import sk.train.model.Employee;

public class Starter {

	public static void main(String[] args) {
		 EntityManagerFactory emf = Persistence.createEntityManagerFactory("Muster_JPA_Hibernate_H2_Local");
		 EntityManager em = emf.createEntityManager();
		 
		 EmpService myserv = new EmpService(em);
		 
		 Employee emp = new Employee();
			emp.setEmployeeId(471);
			emp.setFirstName("Max");
			emp.setLastName("Mustermann");
			emp.setHireDate(new Date());
			emp.setJobId("IT_PROG");
			emp.setPhoneNumber("1111");
			emp.setSalary(new BigDecimal(5000l));
			emp.setEmail("Mustermann@murks.de" + 4711);
		 
		// mal Funktionalitaet von EmpService austesten insbesondere auch zu den Abteilungen
		 
		// z.B. mal alle Abteilungen holen und ausgeben
		 
		 
		 //z.B. Abteilungsleiter zu Abteilung 50
		 
		 
		 //z. b. alle Angestellten aus Abteilung 50
		 
		 

	}

}
