package app.web;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
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
@RequestMapping(value = "/employee")
public class EmployeeControllerJSON {

	@Autowired
	EmpRepository repo;

	@GetMapping("/{id}")
	public EntityModel<Employee> empById(@PathVariable Long id) {
		Employee emp = repo.findById(id).orElseThrow(() -> new EmployeeNotFoundException("gibts nicht") );
		EntityModel<Employee> empmodel = EntityModel.of(
				emp,
				//WebMvcLinkBuilder.linkTo(EmployeeControllerJSON.class).slash("konten").slash(id.toString()).withSelfRel(),
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EmployeeControllerJSON.class).empById(id)).withSelfRel(),
				WebMvcLinkBuilder.linkTo(EmployeeControllerJSON.class).withRel("all"));
		
		return empmodel;
		
	}

	//new Link(linkTo(EventController.class).toString() + "{/eventId}", "event") 
	@GetMapping
	public CollectionModel<Employee> allEmps() {
		CollectionModel<Employee> model = CollectionModel.of(
				repo.findAll(),
				WebMvcLinkBuilder.linkTo(EmployeeControllerJSON.class).withRel("all"),
				Link.of(WebMvcLinkBuilder.linkTo(EmployeeControllerJSON.class).toString()+ "{/id}", "employee"));
		        //weitere Links sind natürlich möglich
		return model;
	}

	@PostMapping
	public Employee saveEmp(@RequestBody Employee emp) {
		if (repo.existsById(emp.getEmployeeId())) {
			throw new EmployeeExistsException("Employee exists for id: " + emp.getEmployeeId());
		}
		return repo.save(emp);
	}

	@PutMapping("/{id}")
	public Employee updateEmp(@PathVariable("id") Long id, @RequestBody Employee emp) {
		if (repo.existsById(id)) {
			return repo.save(emp);
		} else {
			throw new EmployeeNotFoundException("No Employee for id: " + id);
		}
	}

	@DeleteMapping("/{id}")
	public void deleteEmp(@PathVariable("id") Long id) {
		if (repo.existsById(id)) {
			repo.deleteById(id); // wirft nur IllegalArgumentException falls ID null
		} else {
			throw new EmployeeNotFoundException("No Employee for id: " + id);
		}
	}

}
