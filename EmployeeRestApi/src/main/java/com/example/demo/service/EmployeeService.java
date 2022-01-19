package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.Employee;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;

public interface EmployeeService {
	public List<Employee> findAll();
	public Employee findById(int id);
	public void save(Employee employee);
	public void deleteById(int id);
	
	public List<Employee> searchByFirstName(String firstName);
	
	public List<Employee> sortByFirstName(String order);
	
	public User saveUser(User user);
	public Role saveRole(Role role);
}