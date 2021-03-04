package sk.train.app;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import model.Employee;
import sk.train.dao.EmpService;



public class Application {
	public static void main(String[] args) {
		
		//Entity-Manager und EmpService anhand Context beziehen und nutzen
		
		
		try (final AnnotationConfigApplicationContext ctx = 
				new AnnotationConfigApplicationContext(sk.train.app.ApplConfig.class)) {
			
			EntityManager em = ctx.getBean(EntityManager.class);
			System.out.println(em);
			
			EmpService myserv = ctx.getBean(EmpService.class);
			 
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

	
}

