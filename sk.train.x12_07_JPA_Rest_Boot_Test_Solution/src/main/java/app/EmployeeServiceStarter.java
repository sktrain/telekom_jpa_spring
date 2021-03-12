package app;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import app.dao.EmpRepository;
import app.model.Employee;

@SpringBootApplication
public class EmployeeServiceStarter {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(EmployeeServiceStarter.class);
//		String[] beans = ctx.getBeanDefinitionNames();
//		for (String s: beans) {
//			System.out.println(s);
//		}
	}

}