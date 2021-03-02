package sk.train.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

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
		return null;
	}
	
	public BigDecimal getSumSalary() {
		//Summe der Gehälter
		return null;
	}
	
	public List<Name> getNames(){
		//Liste aller Nachnamen + Vorname mit Mapping auf passende Klasse
		
		return null;		
	}
	
	public List<Object[]> getNamesAsParts(){
		//Liste aller Nachnamen + Vorname
		
		return null;		
	}
	
	//als JPQL-Query
	public List<Employee> getChefs(){
		// Liste aller Chefs, d.h. alle die irgendjemands Chef sins
		// select * from employees where 
		//  employee_id in (select distinct manager_id from employees)
		return null;
	}
	
	//als JPQL-Query
	public List<Employee>  getIndianer(){
		//alle, die keine Chefs sind
		return null;
	}
	
	//Liste der Chefs (Nativer Query), Vorsicht Schema muss qualifiziert werden
	public List<Employee> getChefsNative(){
		return null;		
	}
	
	//Liste der Indianer (Nativer Query)
	public List<Employee> getIndianerNative(){
	   return null;
		}
	
}
