package app;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.client.RestTemplate;

import app.model.Employee;

public class Client{
	
	public static void main(String[] args) {
		
		RestTemplate template = new RestTemplate();
		
		Employee emp = template.getForObject("http://localhost:8080/employee/100", Employee.class);
		
		System.out.println(emp);
		
//		List<Employee> emplist = template.getForObject("http://localhost:8080/employee", List.class);
//		
//		System.out.println(emplist.getClass());
//		
//		System.out.println(emplist == null);
//		
//		System.out.println(emplist.get(0));
//		
//		System.out.println(emplist.size());
//		
//		for (int i=0; i < 114; ++i) {
//			System.out.println(emplist.get(i));
//		}
		
		//Interessant die forEach-Schleife und die Lambda-Methodenreferenz funktioniert nicht
		//vermutlich weil Jackson da intern dazwischen fummelt
		
//		for (Employee e : emplist) {
//			System.out.println(e);
//		}
		
//		e.forEach(System.out::println);
		
//		emp.setLastName("Koenig");
//		
//		//template schickt das mit MediaType XML
//		template.put("http://localhost:8080/employee/100", emp);
//		
		
		
	}
	
}