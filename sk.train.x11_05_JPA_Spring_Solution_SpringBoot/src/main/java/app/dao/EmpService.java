package app.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import app.model.Employee;


@Transactional(propagation = Propagation.REQUIRED)
public class EmpService {
	
	
//	@Autowired
	private EntityManager myem;

	
	public EmpService() {
		super();
		//this.myem = myem;
	}
	
	@PersistenceContext
	public void setEntityManager(EntityManager em) {
		System.err.println(em.getClass().getName());
		myem = em;
	}
	
	public void createEmp(Employee emp){
		//System.err.println(emp);
		myem.persist(emp);		
	}
	
	@Transactional(propagation = Propagation.SUPPORTS)
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
	
	@Transactional(propagation = Propagation.NEVER)
	public List<Employee> getemps(){
		//alle Angestellten: Named Query nutzen
		return myem.createNamedQuery("Employee.findAll", Employee.class).getResultList();
	}
	
	
}
