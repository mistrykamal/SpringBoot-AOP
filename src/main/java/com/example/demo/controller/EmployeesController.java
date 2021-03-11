package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;

@RestController
@RequestMapping("/employees")
public class EmployeesController {
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(EmployeesController.class);
	
//	List<Employee> emplList = new ArrayList<>(Arrays.asList(
//			new Employee("Javed", "Surat"),
//			new Employee("Kamal", "Dallas"),
//			new Employee("Minesh", "Irving")));
	
	
	@GetMapping(value = "/all")
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	@GetMapping(value = "/{name}")
	public Employee getByName(@PathVariable String name) {
		return employeeRepository.findByName(name);
	}
	
	@PostMapping(value = "/load")
	public void createEmployee(@RequestBody final Employee employee) {
		//emplList.stream().forEach(empl -> employeeRepository.save(empl));
		employeeRepository.save(employee);
		
	}
	
	@PutMapping(value = "/{name}")
	public void updateEmployee(@PathVariable String name, @RequestBody Employee employee) {
		Employee editEmp = employeeRepository.findByName(name);
		editEmp.setName(employee.getName());
		editEmp.setLocation(employee.getLocation());
		employeeRepository.save(editEmp);
	}
	
	@DeleteMapping(value = "/{name}")
	public void deleteEmployee(@PathVariable String name) {
		employeeRepository.delete(employeeRepository.findByName(name));
	}
}
