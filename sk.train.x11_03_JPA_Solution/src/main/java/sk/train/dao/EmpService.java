package sk.train.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import sk.train.model.Department;
import sk.train.model.Employee;
import sk.train.model.Name;

public class EmpService {
	
	//@PersistenceContext
	private EntityManager myem;

	
	//default-Konstruktor
	public EmpService(EntityManager myem) {
		super();
		this.myem = myem;
	}
	
	/********** Employee-Zugriffe ***********/
	
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
	
	
	/********** Department-Zugriffe ***********/
	
	public List<Department> getdepartments(){
		return myem.createNamedQuery("Department.findAll", Department.class).getResultList();
	}
	
	public Department readDep(long id){
		Department dep = myem.find(Department.class, id);
		return dep;
	}
	
	
	/********** Query-Beispiele ***********/
	
	public List<Employee> getempsBySalary(BigDecimal lowerbound){
		//alle Ang. mit Gehalt >= lowerbound
		TypedQuery<Employee> query = 
				myem.createQuery("select e from Employee e where e.salary >= :bound", Employee.class);
		query.setParameter("bound", lowerbound);
		return query.getResultList();
	}
	
	public BigDecimal getSumSalary() {
		return myem.createQuery("select sum(e.salary)from Employee e" , BigDecimal.class).getSingleResult();
	}
	
	public List<Name> getNames(){
		//Liste aller Nachnamen + Vorname mit Mapping
		Query query = myem.createQuery("SELECT NEW sk.train.model.Name(e.firstName, e.lastName) FROM Employee e");
		return query.getResultList();		
	}
	
	public List<Object[]> getNamesAsParts(){
		//Liste aller Nachnamen + Vorname
		Query query = myem.createQuery("SELECT e.firstName, e.lastName FROM Employee e");
		return query.getResultList();		
	}
	
	//als JPQL-Query
	public List<Employee> getChefs(){
		// select * from employees where 
		//  employee_id in (select distinct manager_id from employees)
		return myem.createQuery("select distinct e.employee from Employee e", Employee.class).getResultList();
	}
	
	//als JPQL-Query
	public List<Employee>  getIndianer(){
		// select * from employees where 
		//  employee_id in (select distinct manager_id from employees where manager_id is not null)
		return myem.createQuery("select distinct e from Employee e where e not in" 
	                             + "(select c.employee from Employee c)",
				Employee.class).getResultList();
	}
	
	//Liste der Chefs (Nativer Query), Vorsicht Schema muss qualifiziert werden
	public List<Employee> getChefsNative(){
		return myem.createNativeQuery("select * from hr.employees where employee_id in" +
				"(select distinct manager_id from hr.employees)", Employee.class)
				.getResultList();		
	}
	
	//Liste der Indianer (Nativer Query)
	public List<Employee> getIndianerNative(){
	   return myem.createNativeQuery("select * from hr.employees where employee_id  not in" +
				  "(select distinct manager_id from hr.employees where manager_id is not null)",
				  Employee.class).getResultList();
		}
	
}
