package sk.train.client;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import model.Employee;
import sk.train.dao.EmpService;

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
		 
		 EntityTransaction t = em.getTransaction();
		 t.begin();
		 myserv.createEmp(emp);
		 t.commit();
		 
		 t = em.getTransaction();
		 t.begin();
		 Employee emp1 = myserv.readEmp(471);
		 System.out.println(emp1);
		 t.commit();
		 
		 t = em.getTransaction();
		 t.begin();
		 myserv.setSalaryEmp(471, new BigDecimal(8000L));
		 t.commit();
		 
		 t = em.getTransaction();
		 t.begin();
		 emp1 = myserv.readEmp(471);
		 System.out.println(emp1.getSalary());
		 t.commit();
		 
		 t = em.getTransaction();
		 t.begin();
		 myserv.removeEmp(471);
		 t.commit();
		 
		 myserv.getemps().forEach(System.out::println);		 

	}

}
