package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.example.demo.controller.EmployeesController;
import com.example.demo.model.Employee;

@SpringBootApplication
public class YoutubeLearningApplication {

	public static void main(String[] args) {
		SpringApplication.run(YoutubeLearningApplication.class, args);

	}

}
