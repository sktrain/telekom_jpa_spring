package sk.train.repositories;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import model.Employee;

public interface EmpRepository  extends JpaRepository<Employee, Long>{
	
	public abstract List<Employee> findBySalary(BigDecimal salary);
	
	public abstract List<Employee> findBySalaryLessThan(BigDecimal salary);
	
	public abstract Optional<Employee> findByEmployeeIdAndSalary(long id, BigDecimal salary);
	
	@Query("select sum(e.salary)from Employee e")
	public abstract BigDecimal getSumSalary();

}
