package sk.train.app;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.Query;

import model.Department;
import model.Employee;
import sk.train.repositories.DepRepository;
import sk.train.repositories.EmpRepository;

public class Application {
	public static void main(String[] args) {

		try (final AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(
				sk.train.app.ApplConfig.class)) {

			EmpRepository emprepo = ctx.getBean(EmpRepository.class);
			DepRepository deprepo = ctx.getBean(DepRepository.class);

			Employee emp = new Employee();
			emp.setEmployeeId(471);
			emp.setFirstName("Max");
			emp.setLastName("Mustermann");
			emp.setHireDate(new Date());
			emp.setJobId("IT_PROG");
			emp.setPhoneNumber("1111");
			emp.setSalary(new BigDecimal(5000l));
			emp.setEmail("Mustermann@murks.de" + 4711);


			Employee erg = emprepo.save(emp);
			System.err.println(erg == emp);


			Optional<Employee> emp1 = emprepo.findById(471L);
			System.err.println(emp1);


			emp.setSalary(new BigDecimal(8000L));
			erg = emprepo.save(emp);
			System.err.println(erg == emp);
			
			emp1 = emprepo.findById(471L);
			if (emp1.isPresent()) {
				System.err.println(emp1.get().getSalary());
			}
			
			emprepo.deleteById(471L);

			emprepo.findAll().forEach(System.err::println);
			
			//mal die zusätzlichen Methoden für Employee testen
			
			emprepo.findBySalary(new BigDecimal(5000)).forEach(System.err::println);
			
			emprepo.findBySalaryLessThan(new BigDecimal(5000)).forEach(System.err::println);
			
			System.err.println(emprepo.findByEmployeeIdAndSalary(100L, new BigDecimal(24000)));  // Steven King
			
			System.err.println(emprepo.findByEmployeeIdAndSalary(105L, new BigDecimal(24000)));	// kein Treffer
			
			System.err.println(emprepo.getSumSalary());
			
			//und jetzt auch die Abteilungsdaten bzw. Chef
			
			System.out.println("\n ********************* jetzt zu den Departments und den Chefs *************\n");
			 
			 List<Department> deplist = deprepo.findAll();
			 deplist.forEach(System.out::println);
			 
			 Optional<Department> dep50 = deprepo.findById(50L);
			 System.out.print("Hier kommt der Abteilungsleiter: ");  
			 dep50.ifPresent(dep -> System.out.println(dep.getDepmanager().getLastName()));
			 
			 System.out.print("Hier kommt der Chef vom Abteilungsleiter: "); 
			 dep50.ifPresent(dep -> System.out.println(dep.getDepmanager().getManager()));
			 //jetzt mal alle Angestellten in der Abteilung
			 //funktioniert nicht für Lazy-Beziehung!!
//			 Department dep = dep50.get();
//			 dep.getEmployees().forEach(System.out::println);
//			 List<Employee> empsByDep = dep.getEmployees();
//			 empsByDep.forEach(System.out::println);
//			 dep50.ifPresent(dep -> dep.getEmployees().forEach(employee -> System.out.println(employee.getLastName())));
			 
			 List<Employee> empsByDep = deprepo.findEmployeesByDepartment(50L);
			 empsByDep.forEach(System.out::println);

		}
	}

}
