package app;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import app.model.Employee;

@SpringBootApplication
//steht für
//@Configuration
//@EnableAutoConfiguration
//@ComponentScan
public class Application implements CommandLineRunner {
	
	//das lädt den Spring-Context
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Autowired
	private JdbcTemplate template;

	@Override
	public void run(String... args) throws Exception {
		
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
		final String sql = "INSERT INTO hr.employees (employee_id, first_name, last_name, hire_date, job_id, salary)"
				+ "VALUES (?, ?, ?, ?, ?, ?)";
		final Object result = template.update(sql, emp.getEmployeeId(), emp.getFirstName(), emp.getLastName(),
				emp.getHireDate(), emp.getJobId(), emp.getSalary());
		if ((Integer) result != 1)
			throw new DataRetrievalFailureException(emp.toString());
	}

	private static void updateSalary(JdbcTemplate template, Employee emp) {
		final String sql = "UPDATE hr.employees SET salary =? WHERE employee_id = ?";
		final Object result = template.update(sql, emp.getSalary(), emp.getEmployeeId());
		if ((Integer) result != 1)
			throw new DataRetrievalFailureException(emp.toString());
	}

	private static void deleteEmployee(JdbcTemplate template, Employee emp) {
		final String sql = "DELETE FROM hr.employees WHERE  employee_id = ?";
		final Object result = template.update(sql, emp.getEmployeeId());
		if ((Integer) result != 1)
			throw new DataRetrievalFailureException(emp.toString());
	}

	private static RowMapper<Employee> mapper = (rs, rowNumber) -> {
		Employee emp = new Employee();
		emp.setEmployeeId(rs.getLong("employee_id"));
		emp.setFirstName(rs.getString("first_name"));
		emp.setLastName(rs.getString("last_name"));
		emp.setHireDate(rs.getTimestamp("hire_date"));
		emp.setJobId(rs.getString("job_id"));
		emp.setSalary(rs.getBigDecimal("salary"));
		return emp;
	};

	private static Employee findEmployee(JdbcTemplate template, long id) {
		final String sql = "SELECT employee_id, first_name, last_name, hire_date, job_id, salary FROM hr.employees WHERE employee_id = ?";
		return template.queryForObject(sql, mapper, id);
	}

	private static List<Employee> findAllEmployees(JdbcTemplate template) {
		final String sql = "SELECT employee_id, first_name, last_name, hire_date, job_id, salary FROM hr.employees";
		return template.query(sql, mapper);
	}

}
