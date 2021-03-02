package sk.train.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import sk.train.model.Department;
import sk.train.model.Employee;

public class EmpService {
	
	//@PersistenceContext
	private EntityManager myem;

	
	//default-Konstruktor
	public EmpService(EntityManager myem) {
		super();
		this.myem = myem;
	}
	
	public void createEmp(Employee emp){
		myem.persist(emp);		
	}
	
	public Employee readEmp(long id){
		Employee emp = myem.find(Employee.class, id);
		return emp;
	}
	
	public void removeEmp(long id){
		Employee emp = myem.find(Employee.class, id);
		if (emp != null)
		myem.remove(emp);
	}
	
	public void setSalaryEmp(long id, BigDecimal sal){
		Employee emp = myem.find(Employee.class, id);
		if (emp != null)
			emp.setSalary(sal);
	}
	
	public List<Employee> getemps(){
		//alle Angestellten: Named Query nutzen
		return myem.createNamedQuery("Employee.findAll", Employee.class).getResultList();
	}
	
	
	public List<Department> getdepartments(){
		return myem.createNamedQuery("Department.findAll", Department.class).getResultList();
	}
	
	public Department readDep(long id){
		Department dep = myem.find(Department.class, id);
		return dep;
	}
	
}
