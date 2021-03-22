package com.example.demo.client.consumeapi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.example.demo.model.Employee;

@SpringBootApplication
public class ClientApp {

	public static void main(String[] args) {
		SpringApplication.run(ClientApp.class, args);

		try {
			PeopleInformation person = new PeopleInformation();
			List<Object> all = person.getAll();
			System.out.println(all);
			Object found = person.getByName("Roman");
			System.out.println(found);
			person.createPerson("MarkHenry", "Kentucky");
			System.out.println("use postman client to check update on createPerson()");
			person.deletePerson("Roman");
			System.out.println("use postman client to check update on deletePerson()");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

/**
 *  Class to make request to server
 *
 */
class PeopleInformation {
	
	private static RestTemplate restTemplate;
	
	public PeopleInformation() {
		restTemplate = new RestTemplate();
	}
	
	public List<Object> getAll() {
		String url = "http://localhost:8080/employees/all";
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		
		HttpEntity<String> entity = new HttpEntity<String>(headers);
//		ResponseEntity<String> employeeList = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
		
		Object[] employeeList = restTemplate.getForObject(url, Object[].class, entity);
		
		if(employeeList.length > 0) {
			return Arrays.asList(employeeList);
		}
		else {
			List<Object> empty = new ArrayList<Object>();
			empty.add("No Employee in api");
			return empty;
		}
	}
	
	public Object getByName(String employeeName) {
		String url = "http://localhost:8080/employees/{name}";
		Map<String, String> param = new HashMap<String, String>();
		param.put("name", employeeName);
		
		Object employee = restTemplate.getForObject(url, Object.class, param);
		
		if(employee != null) {
			return employee;
		}
		else {
			return "Employee" + param.get("name")+ "is not present";
		}
	}
	
	public void createPerson(String name, String location) {
		String url = "http://localhost:8080/employees/load";
		Employee employee = new Employee(name, location);
		ResponseEntity<Employee> newEmployee = restTemplate.postForEntity(url, employee, Employee.class);
		System.out.println(newEmployee);
	}
	
	public void deletePerson(String employeeName) {
		String url = "http://localhost:8080/employees/{name}";
		Map<String, String> param = new HashMap<String, String>();
		param.put("name", employeeName);
		restTemplate.delete(url, param);
	}
	
}
