package sk.train.app;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
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
		final String sql = "INSERT INTO hr.employees (employee_id, first_name, last_name, hire_date, job_id, salary)"
				+ "		VALUES (?, ?, ?, ?, ?, ?)";
		try (final PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setLong(1, emp.getEmployeeId());
			ps.setString(2, emp.getFirstName());
			ps.setString(3, emp.getLastName());
			ps.setTimestamp(4, emp.getHireDate());
			ps.setString(5, emp.getJobId());
			ps.setBigDecimal(6,  emp.getSalary());
			if (ps.executeUpdate() != 1)
				throw new RuntimeException("employee not inserted: " + emp.getLastName());
		}
	}
	

	
	/* z.B. UPDATE hr.employees SET salary =20000 WHERE employee_id = 300; 
	 */
	public static void updateSalary(Connection con, Employee emp) throws SQLException {
		 
		final String sql = "UPDATE hr.employees SET salary =? WHERE employee_id = ?";
		try (final PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setBigDecimal(1, emp.getSalary());
			ps.setLong(2, emp.getEmployeeId());
			if (ps.executeUpdate() != 1)
				throw new RuntimeException("emp not updated: " + emp.getLastName());
		}
	}
	
	
	/* z.B. DELETE FROM hr.employees WHERE  employee_id = 300; 
	 */
	public static void deleteEmployee(Connection con, Employee emp) throws SQLException {
		final String sql = "DELETE FROM hr.employees WHERE  employee_id = ?";
		try (final PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setLong(1, emp.getEmployeeId());
			if (ps.executeUpdate() != 1)
				throw new RuntimeException("emp not deleted: " + emp.getLastName());
		}
	}

	/* z.B. SELECT first_name, last_name, hire_date, job_id, salary FROM hr.employees WHERE employee_id = 100;
	 * 
	 */
	public static Employee findEmployee(Connection con, long id) throws SQLException {
		final String sql = "SELECT first_name, last_name, hire_date, job_id, salary FROM hr.employees WHERE employee_id = ?";
		try (final PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setLong(1, id);
			try (final ResultSet rs = ps.executeQuery()) {
				if (!rs.next())
					return null;
				Employee emp = new Employee();
				emp.setEmployeeId(id);
				emp.setFirstName(rs.getString("first_name"));
				emp.setLastName(rs.getString("last_name"));
				emp.setHireDate(rs.getTimestamp("hire_date"));
				emp.setJobId(rs.getString("job_id"));
				emp.setSalary(rs.getBigDecimal("salary"));
				return emp;
			}
		}
	}

	private static List<Employee> findAllEmployees(Connection con) throws SQLException {
		final String sql = "SELECT employee_id, first_name, last_name, hire_date, job_id, salary FROM hr.employees";
		try (final PreparedStatement ps = con.prepareStatement(sql)) {
			try (final ResultSet rs = ps.executeQuery()) {
				final List<Employee> list = new ArrayList<>();
				while (rs.next()) {
					Employee emp = new Employee();
					emp.setEmployeeId(rs.getLong("employee_id"));
					emp.setFirstName(rs.getString("first_name"));
					emp.setLastName(rs.getString("last_name"));
					emp.setHireDate(rs.getTimestamp("hire_date"));
					emp.setJobId(rs.getString("job_id"));
					emp.setSalary(rs.getBigDecimal("salary"));
					list.add(emp);
				}
				return list;
			}
		}
	}

}
