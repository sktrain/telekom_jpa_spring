package sk.train.JPABootDemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sk.train.JPABootDemo.Customer;
import sk.train.JPABootDemo.CustomerRepository;

@RestController
@RequestMapping("/customer")
public class CustomerControler {
	
	@Autowired
	private CustomerRepository repo;
	
	@GetMapping("/list")
	public Iterable<Customer> getall(){
		return repo.findAll();
	}
	
	@GetMapping("/{id}")
	public Customer getById(@PathVariable Long id) {
		return repo.findById(id).get();
	}
	
	@PostMapping
	public Customer create(@RequestBody Customer c) {
		return repo.save(c);
		}
	
	@DeleteMapping
	public void delete(@RequestBody Customer c) {
		repo.delete(c);
		}
	
	

}
 