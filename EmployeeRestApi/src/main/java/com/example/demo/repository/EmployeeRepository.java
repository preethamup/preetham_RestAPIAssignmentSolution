package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
//	https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#query-by-example
	List<Employee> findByFirstNameContainsAndLastNameContainsAllIgnoreCase(String firstName,String lastName);	
	
	List<Employee> findByFirstNameContainsAllIgnoreCase(String firstName);

	List<Employee> findAllByOrderByFirstNameAsc();
	
	List<Employee> findAllByOrderByFirstNameDesc();
}
