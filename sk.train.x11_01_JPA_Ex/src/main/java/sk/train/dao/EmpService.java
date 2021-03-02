package sk.train.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import model.Employee;

public class EmpService {
	
	private EntityManager myem;

	
	public EmpService(EntityManager myem) {
		super();
		this.myem = myem;
	}
	
	public void createEmp(Employee emp){		
		//todo	
	}
	
	public Employee readEmp(long id){
		//todo
	}
	
	public void removeEmp(long id){
		//todo
	}
	
	public void setSalaryEmp(long id, BigDecimal sal){
		//todo
	}
	
	public List<Employee> getemps(){
		//alle Angestellten: Named Query nutzen
		
	}
	
	
}
