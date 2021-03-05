package app;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.BeforeTestExecution;

import app.dao.EmpService;
import app.model.Employee;

@SpringBootTest
class JpaTutorialApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@Autowired
	private EmpService myserv;
	
	//private Employee emp;
	
	@Test
	void crudTest() {
		
		Employee emp = new Employee();
		emp.setEmployeeId(471);
		emp.setFirstName("Max");
		emp.setLastName("Mustermann");
		emp.setHireDate(new Date());
		emp.setJobId("IT_PROG");
		emp.setPhoneNumber("1111");
		emp.setSalary(new BigDecimal(5000l));
		emp.setEmail("Mustermann@murks.de" + 4711);
		
		
		myserv.createEmp(emp);
		
		Employee emp1 = myserv.readEmp(471);
		System.err.println(emp1);
		
		myserv.setSalaryEmp(471, new BigDecimal(8000L));
		
		emp1 = myserv.readEmp(471);
		System.err.println(emp1.getSalary());
		
		myserv.removeEmp(471);
		
		assertTrue(myserv.readEmp(471) == null);
		
		
	}
	
	

}
//Employee emp = new Employee();
//emp.setEmployeeId(471);
//emp.setFirstName("Max");
//emp.setLastName("Mustermann");
//emp.setHireDate(new Date());
//emp.setJobId("IT_PROG");
//emp.setPhoneNumber("1111");
//emp.setSalary(new BigDecimal(5000l));
//emp.setEmail("Mustermann@murks.de" + 4711);
//
//
//myserv.createEmp(emp);
//
//Employee emp1 = myserv.readEmp(471);
//System.err.println(emp1);
//
//myserv.setSalaryEmp(471, new BigDecimal(8000L));
//
//emp1 = myserv.readEmp(471);
//System.err.println(emp1.getSalary());
//
//myserv.removeEmp(471);
//
//myserv.getemps().forEach(System.err::println);