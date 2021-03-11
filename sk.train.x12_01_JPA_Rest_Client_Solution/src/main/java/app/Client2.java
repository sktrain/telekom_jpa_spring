package app;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.springframework.http.ResponseEntity;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;

import app.model.Employee;

public class Client2{
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		AsyncRestTemplate template = new AsyncRestTemplate();
		ListenableFuture<ResponseEntity<Employee>> future = template.getForEntity("http://localhost:8080/employee/100", Employee.class);
		Employee e = future.get().getBody();
		

		
//		for (int i = 0; i<10; ++i) {
//			Thread t = new Thread(new MyRunnable());
//			t.start();
//		}		
	}	
}

class MyRunnable implements Runnable {
	
	private RestTemplate template = new RestTemplate();

	@Override
	public void run() {
		for (int i = 0; i< 10; ++i) {
			template.getForObject("http://localhost:8080/employee/100", Employee.class);
		}
		
	}
}

//RestTemplate template = new RestTemplate();
//Future s = template.getForObject("http://localhost:8080/employee/slowcall", Future.class);
//try {
//	System.out.println(s.get());
//} catch (InterruptedException e) {
//	// TODO Auto-generated catch block
//	e.printStackTrace();
//} catch (ExecutionException e) {
//	// TODO Auto-generated catch block
//	e.printStackTrace();
//}