package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class EmployeeServiceStarter {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(EmployeeServiceStarter.class);
//		String[] beans = ctx.getBeanDefinitionNames();
//		for (String s: beans) {
//			System.out.println(s);
//		}
	}
	
	
	

}