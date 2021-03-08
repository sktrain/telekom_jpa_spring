package app.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
@RequestMapping(path = "/employee")
public class EmployeeController {

	@Autowired
	EmpRepository repo;

	@GetMapping("/{id}")
	public Employee empById(@PathVariable("id") Long id) {
		Optional<Employee> optemp = repo.findById(id);
		return optemp.get();
	}

	@GetMapping
	public List<Employee> allEmps() {
		return repo.findAll();
	}

	@PostMapping
	public Employee saveEmp(@RequestBody Employee emp) {
		return repo.save(emp);
	}

	@PutMapping
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

}
