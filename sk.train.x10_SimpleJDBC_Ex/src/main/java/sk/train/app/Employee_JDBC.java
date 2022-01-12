package sk.train.app;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

import sk.train.app.model.Employee;

public class Employee_JDBC {

	public static void main(String[] args) {

		try {
			Class.forName("org.h2.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try (Connection con = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "")) {

			System.out.println(con.getMetaData().getDatabaseProductName());
			
			findAllEmployees(con).forEach(System.out::println);
			
			Employee emp = new Employee();
			emp.setEmployeeId(300L);
			emp.setFirstName("Stephan");
			emp.setLastName("Karrer");
			emp.setHireDate(Timestamp.valueOf(LocalDate.of(2000, 1, 1).atStartOfDay()));
			emp.setJobId("ST_CLERK");
			emp.setSalary(new BigDecimal("5000"));
			
			insertEmp(con, emp);
			
			Employee emp2 = findEmployee(con, 300L);
			System.out.println(emp2);

			emp2.setSalary(new BigDecimal("20000"));
			updateSalary(con, emp2);
			
			findAllEmployees(con).forEach(System.out::println);
			
			deleteEmployee(con, emp2);
			
			findAllEmployees(con).forEach(System.out::println);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	
	/* z.B. INSERT INTO hr.employees (employee_id, first_name, last_name, hire_date, job_id, salary) 
		VALUES (301, 'Stephan', 'Karrer', TO_DATE('01-01-1960', 'dd-MM-yyyy'), 'ST_CLERK', 5000);
	*/
	public static void insertEmp(Connection con, Employee emp) throws SQLException {
		//todo
	}
	

	
	/* z.B. UPDATE hr.employees SET salary =20000 WHERE employee_id = 300; 
	 */
	public static void updateSalary(Connection con, Employee emp) throws SQLException {
		 
		//todo
	}
	
	
	/* z.B. DELETE FROM hr.employees WHERE  employee_id = 300; 
	 */
	public static void deleteEmployee(Connection con, Employee emp) throws SQLException {
		//todo
	}

	/* z.B. SELECT first_name, last_name, hire_date, job_id, salary FROM hr.employees WHERE employee_id = 100;
	 * 
	 */
	public static Employee findEmployee(Connection con, long id) throws SQLException {
		//todo
		return null;
			
		
	}

	private static List<Employee> findAllEmployees(Connection con) throws SQLException {
		//todo
		return null;
	}

}
