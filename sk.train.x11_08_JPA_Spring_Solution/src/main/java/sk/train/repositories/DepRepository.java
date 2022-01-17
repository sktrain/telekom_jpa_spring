package sk.train.repositories;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import model.Department;
import model.Employee;

public interface DepRepository extends JpaRepository<Department, Long>{	

	@Query("SELECT d.employees FROM Department d WHERE d.departmentId = ?1")
	public abstract List<Employee> findEmployeesByDepartment(Long depid); 
}