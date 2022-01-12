package sk.train.app;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import sk.train.app.model.Employee;



public class Application {
	public static void main(String[] args) {
		
		//JdbcTemplate anhand Context beziehen und nutzen
		//analog Projekt: x1003-JDBC-RowMapper
		
		try (final AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(sk.train.app.ApplConfig.class)) {
			final JdbcTemplate template = ctx.getBean(JdbcTemplate.class);
			
			try {
				demo(template);
			}
			catch (final Exception e) {
				System.out.println(e);
			}
		}
	}

	private static void demo(final JdbcTemplate template) {
		
		Employee emp = new Employee();
		emp.setEmployeeId(300L);
		emp.setFirstName("Stephan");
		emp.setLastName("Karrer");
		emp.setHireDate(Timestamp.valueOf(LocalDate.of(2000, 1, 1).atStartOfDay()));
		emp.setJobId("ST_CLERK");
		emp.setSalary(new BigDecimal("5000"));
		
		
		insertEmployee(template, emp);
		
		Employee emp2 = findEmployee(template, 300L);
		System.out.println(emp2);

		emp2.setSalary(new BigDecimal("20000"));
		updateSalary(template, emp2);
		
		findAllEmployees(template).forEach(System.out::println);
		
		deleteEmployee(template, emp);
		
		findAllEmployees(template).forEach(System.out::println);
		
	}

	private static void insertEmployee(JdbcTemplate template, Employee emp) {
		//todo
	}

	private static void updateSalary(JdbcTemplate template, Employee emp) {
		//todo
	}

	private static void deleteEmployee(JdbcTemplate template, Employee emp) {
		//todo
	}

	private static RowMapper<Employee> mapper = null;  //todo

	private static Employee findEmployee(JdbcTemplate template, long id) {
		//todo
		return null;
	}

	private static List<Employee> findAllEmployees(JdbcTemplate template) {
		//todo
		return null;
	}
}

