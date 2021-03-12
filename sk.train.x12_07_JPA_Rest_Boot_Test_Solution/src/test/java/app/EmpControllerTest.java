/* Full Stack Test */

package app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;

import app.model.Employee;
import app.web.EmployeeController;
import app.web.EmployeeExistsException;
import app.web.EmployeeNotFoundException;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class EmpControllerTest {
	
//	@Test
//	public void contextLoads() throws Exception {
//		assertTrue(controller != null);
//	}
	
	@Autowired
	private EmployeeController controller;
	
	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test
	public void testempByiD(){
		Employee e =restTemplate.getForObject("http://localhost:" + port + "/employee/100",	Employee.class);
		assertNotNull(e);
	}
	
	@Test    //(expected=EmployeeNotFoundException.class) nur bei JUnit4
	public void testException() {
		//übers Netz kommt keine Exception, stattdessen Statuscode bzw. meine individuelle Rückgabe prüfen
		//		assertThrows(EmployeeNotFoundException.class, () ->
		//		restTemplate.getForObject("http://localhost:" + port + "/employee/-1",	Employee.class));
		ResponseEntity entity = restTemplate.getForEntity("http://localhost:" + port + "/employee/-1",	Employee.class);
		assertEquals(HttpStatus.NOT_FOUND, entity.getStatusCode());
		
		entity = restTemplate.exchange("http://localhost:" + port + "/employee/-1", HttpMethod.DELETE, null, void.class);
		assertEquals(HttpStatus.NOT_FOUND, entity.getStatusCode());
		
		Employee emp = new Employee(1L, new BigDecimal(50),"Stephan",new Date(),"ST_CLERK", "Karrer", new BigDecimal(10000));
		RequestEntity<Employee> request = RequestEntity
				     .put("http://localhost:" + port + "/employee/1")
				     .accept(MediaType.APPLICATION_JSON)
		     	     .body(emp);
		entity = restTemplate.exchange(request, Employee.class);
		assertEquals(HttpStatus.NOT_FOUND, entity.getStatusCode());
		
		
	}

	@Test
	public void testallEmps() {
		assertTrue(restTemplate.getForObject("http://localhost:" + port + "/employee", List.class).size() > 0);
	}
	
	// noch zu Testen:

//	@PostMapping
//	public Employee saveEmp(@RequestBody Employee emp) {
//		if (repo.existsById(emp.getEmployeeId())) {
//			throw new EmployeeExistsException("Employee exists for id: " + emp.getEmployeeId());
//		}
//		return repo.save(emp);
//	}
//
//	@PutMapping("/{id}")
//	public Employee updateEmp(@PathVariable("id") Long id, @RequestBody Employee emp) {
//		if (repo.existsById(id)) {
//			return repo.save(emp);
//		} else {
//			throw new EmployeeNotFoundException("No Employee for id: "+ id);
//		}
//	}
//	
//	@DeleteMapping("/{id}")
//	public void deleteEmp(@PathVariable("id") Long id) {
//		if (repo.existsById(id)) {
//			repo.deleteById(id);  //wirft nur IllegalArgumentException falls ID null
//		} else {
//			throw new EmployeeNotFoundException("No Employee for id: "+ id);
//		}
//	}


	
}
