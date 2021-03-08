package app;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import app.dao.EmpRepository;
import app.model.Employee;

@SpringBootApplication
public class EmployeeServiceStarter {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(EmployeeServiceStarter.class);
//		String[] beans = ctx.getBeanDefinitionNames();
//		for (String s: beans) {
//			System.out.println(s);
//		}
	}

	// generierte EmpRepository-Implementierung bereit stellen
	// und im CommandLineRunner nutzen (ist an sich Test)
//	@Bean
//	@Autowired
//	public CommandLineRunner demo(EmpRepository myserv) {
//		return (args) -> {
//			Employee emp = new Employee();
//			emp.setEmployeeId(471);
//			emp.setFirstName("Max");
//			emp.setLastName("Mustermann");
//			emp.setHireDate(new Date());
//			emp.setJobId("IT_PROG");
//			emp.setPhoneNumber("1111");
//			emp.setSalary(new BigDecimal(5000l));
//			emp.setEmail("Mustermann@murks.de" + 4711);
//
//			Employee erg = myserv.save(emp);
//			System.err.println(erg == emp);
//
//			Optional<Employee> emp1 = myserv.findById(471L);
//			System.err.println(emp1);
//
//			emp.setSalary(new BigDecimal(8000L));
//			erg = myserv.save(emp);
//			System.err.println(erg == emp);
//
//			emp1 = myserv.findById(471L);
//			if (emp1.isPresent()) {
//				System.err.println(emp1.get().getSalary());
//			}
//
//			myserv.deleteById(471L);
//
//			myserv.findAll().forEach(System.err::println);
//
//			myserv.findBySalary(new BigDecimal(5000)).forEach(System.err::println);
//
//			myserv.findBySalaryLessThan(new BigDecimal(5000)).forEach(System.err::println);
//
//			System.err.println(myserv.findByEmployeeIdAndSalary(100L, new BigDecimal(24000))); // Steven King
//
//			System.err.println(myserv.findByEmployeeIdAndSalary(105L, new BigDecimal(24000))); // kein Treffer
//
//			System.err.println(myserv.getSumSalary());
//
//			myserv.getChefsNative().forEach(System.err::println);
//
//			myserv.getIndianerNative().forEach(System.err::println);
//
//			myserv.getNames().forEach(System.err::println);
//
//		};
//	}

}