package app.web;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
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
	public CollectionModel<EntityModel<Employee>> allemps() {
		List<EntityModel<Employee>> emps =
				repo.findAll().stream()
				.map(emp -> EntityModel.of(emp,
									WebMvcLinkBuilder.linkTo(
											WebMvcLinkBuilder.methodOn(EmployeeControllerXML.class)
											.empById(emp.getEmployeeId())).withSelfRel()))
				.collect(Collectors.toList());
		
		return CollectionModel.of(emps, 
				                  WebMvcLinkBuilder.linkTo(
											WebMvcLinkBuilder.methodOn(EmployeeControllerXML.class)
											.allemps()).withSelfRel());
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
