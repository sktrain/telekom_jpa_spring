package app.web;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.dao.EmpRepository;
import app.model.Employee;

@RestController
@RequestMapping(path="/xmlemployee") //, produces=MediaType.APPLICATION_XML_VALUE, consumes=MediaType.APPLICATION_XML_VALUE)
									//wenn ich es hier mache funktioniert es nicht!!
public class EmployeeControllerXML {

	@Autowired
	EmpRepository repo;
	

	@GetMapping(value="/{id}", produces=MediaType.APPLICATION_XML_VALUE)
	public Employee empById(@PathVariable("id") Long id) {
		Optional<Employee> optemp = repo.findById(id);
		return optemp.orElseThrow(() -> new EmployeeNotFoundException("No Employee for id: " + id));
	}

	@GetMapping(produces=MediaType.APPLICATION_XML_VALUE)
	public List<Employee> allEmps() {
		return repo.findAll();
	}
	

	@PostMapping(produces=MediaType.APPLICATION_XML_VALUE, consumes=MediaType.APPLICATION_XML_VALUE )
	public Employee saveEmp(@RequestBody Employee emp) {
		if (repo.existsById(emp.getEmployeeId())) {
			throw new EmployeeExistsException("Employee exists for id: " + emp.getEmployeeId());
		}
		return repo.save(emp);
	}

	@PutMapping(value="/{id}", produces=MediaType.APPLICATION_XML_VALUE, consumes=MediaType.APPLICATION_XML_VALUE )
	public Employee updateEmp(@PathVariable("id") Long id, @RequestBody Employee emp) {
		if (repo.existsById(id)) {
			return repo.save(emp);
		} else {
			throw new EmployeeNotFoundException("No Employee for id: "+ id);
		}
	}
	
	@DeleteMapping("/{id}")
	public void deleteEmp(@PathVariable("id") Long id) {
		if (repo.existsById(id)) {
			repo.deleteById(id);  //wirft nur IllegalArgumentException falls ID null
		} else {
			throw new EmployeeNotFoundException("No Employee for id: "+ id);
		}
	}

}
