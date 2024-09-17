package com.example.Employee.services;

import java.util.List;

import com.example.Employee.model.Employee;

public interface EmployeeService {
    
    String createEmployee(Employee employee);                //post method
    List<Employee> readEmployees();                         //get method
    boolean deleteEmployee(Long id);                       //delete method
    String updateEmployee(Long id, Employee employee);    //update method
    Employee readEmployee(long id);
}
