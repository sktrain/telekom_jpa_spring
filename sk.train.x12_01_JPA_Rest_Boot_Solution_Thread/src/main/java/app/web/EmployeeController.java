package app.web;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import app.dao.EmpRepository;
import app.model.Employee;
import app.model.Warenkorb;

@RestController
@RequestMapping(path = "/employee")
public class EmployeeController {

	@Autowired
	EmpRepository repo;
	
	@Autowired
	Warenkorb korb;
	
	private List l;
	private String common;
	private ReentrantReadWriteLock rwlock= new ReentrantReadWriteLock(true);
	
	public EmployeeController() {
		super();
		System.err.println("Object: " + this + "/Thread: " + Thread.currentThread());
	}

	@GetMapping("/{id}")
	public Employee empById(@PathVariable("id") Long id) {
		try {
			rwlock.readLock().lock();
			System.err.println("Object: " + this + "/Thread: " + Thread.currentThread());
			common = "Hallo" + id;
			Optional<Employee> optemp = repo.findById(id);
			return optemp.get();
		} finally {
			rwlock.readLock().unlock();
		}
	}

	@GetMapping
	public List<Employee> allEmps() {
		return repo.findAll();
	}

	@PostMapping
	public Employee saveEmp(@RequestBody Employee emp) {
		return repo.save(emp);
	}

	@PutMapping("/{id}")
	public Employee updateEmp(@PathVariable("id") Long id, @RequestBody Employee emp) {
		if (repo.existsById(id)) {
			return repo.save(emp);
		} else {
			return emp;			//war kein Update !!, sollten wir Fehler produzieren?
		}
	}
	
	@DeleteMapping("/{id}")
	public void deleteEmp(@PathVariable("id") Long id) {
		repo.deleteById(id);
	}
	
	@GetMapping("/slowcall")
	@Async
	public Callable<String> execSlowTask(){
		System.err.println("Object: " + this + "/Thread: " + Thread.currentThread());
		Callable<String> call = () -> 
			{ System.err.println("Callable: " + this + "/Thread: " + Thread.currentThread());
			  Thread.sleep(60_000);
			  return "slowcall terminated";
			};
		//Spring erzeugt automatisch einen Hintergrund-Thread für uns	
		
			return call;		
	}

}
