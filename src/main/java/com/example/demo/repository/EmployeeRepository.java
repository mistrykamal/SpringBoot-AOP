package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.example.demo.model.Employee;

@Component
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	Employee findByName(String name);

}
