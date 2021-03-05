package app;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import app.dao.EmpService;
import app.model.Employee;

@SpringBootApplication
public class AccessingDataJpaApplication {

    public static void main(String[] args) {
    SpringApplication.run(AccessingDataJpaApplication.class);
  }
    
 // EmpService bereit stellen
 	@Bean
 	public EmpService getEmpService() {
 		return new EmpService();
 	}

  @Bean
  public CommandLineRunner demo(EmpService myserv) {
    return (args) -> {
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

		myserv.getemps().forEach(System.err::println);
    };
  }

}