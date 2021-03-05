package sk.train.app;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.jpa.repository.Query;

import model.Employee;
import sk.train.repositories.EmpRepository;

public class Application {
	public static void main(String[] args) {

		try (final AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(
				sk.train.app.ApplConfig.class)) {

			EmpRepository myserv = ctx.getBean(EmpRepository.class);

			Employee emp = new Employee();
			emp.setEmployeeId(471);
			emp.setFirstName("Max");
			emp.setLastName("Mustermann");
			emp.setHireDate(new Date());
			emp.setJobId("IT_PROG");
			emp.setPhoneNumber("1111");
			emp.setSalary(new BigDecimal(5000l));
			emp.setEmail("Mustermann@murks.de" + 4711);


			Employee erg = myserv.save(emp);
			System.err.println(erg == emp);


			Optional<Employee> emp1 = myserv.findById(471L);
			System.err.println(emp1);


			emp.setSalary(new BigDecimal(8000L));
			erg = myserv.save(emp);
			System.err.println(erg == emp);
			
			emp1 = myserv.findById(471L);
			if (emp1.isPresent()) {
				System.err.println(emp1.get().getSalary());
			}
			
			myserv.deleteById(471L);

			myserv.findAll().forEach(System.err::println);
			
			//mal die zusätzlichen Methoden testen
			
//			public abstract List<Employee> findBySalary(BigDecimal salary);
//			
//			public abstract List<Employee> findBySalaryLessThan(BigDecimal salary);
//			
//			public abstract Optional<Employee> findByEmployeeIdAndSalary(long id, BigDecimal salary);
//			
//			@Query("select sum(e.salary)from Employee e")
//			public abstract BigDecimal getSumSalary();
			
			myserv.findBySalary(new BigDecimal(5000)).forEach(System.err::println);
			
			myserv.findBySalaryLessThan(new BigDecimal(5000)).forEach(System.err::println);
			
			System.err.println(myserv.findByEmployeeIdAndSalary(100L, new BigDecimal(24000)));  // Steven King
			
			System.err.println(myserv.findByEmployeeIdAndSalary(105L, new BigDecimal(24000)));	// kein Treffer
			
			System.err.println(myserv.getSumSalary());

		}
	}

}
