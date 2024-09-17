package com.example.Employee.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.Employee.entity.EmployeeEntity;
import com.example.Employee.model.Employee;
import com.example.Employee.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    //List<Employee> employees = new ArrayList<>();
    //EmployeeRepository employeeRepository = new EmployeeRepository();

    //Object without new keyword means it is made by IOC container; 

    @Autowired
    private EmployeeRepository employeeRepository;   //Dependency Injection;

    @Override
    public String createEmployee(Employee employee) {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        BeanUtils.copyProperties(employee, employeeEntity);

        employeeRepository.save(employeeEntity);
        return "Saved Successfully";
    }

    // @Override
    // public Employee readEmployee(Long id) {
    //     EmployeeEntity employeeEntity = employeeRepository.findById(id).get();
        
    //     Employee employee = new Employee();
    //     BeanUtils.copyProperties(employeeEntity, employee);

    //     return employee;
    // }

    @Override
    public Employee readEmployee(long id) {
        EmployeeEntity employeeEntity = employeeRepository.findById(id).get();
        
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeEntity, employee);

        return employee;
    }


    @Override
    public List<Employee> readEmployees() {
        List<EmployeeEntity> employeesList = employeeRepository.findAll();
        List<Employee> employees = new ArrayList<>();
        //for(int i=1;i<=employees.length();i++)
        for (EmployeeEntity employeeEntity : employeesList) {
            Employee emp = new Employee();
            emp.setId(employeeEntity.getId());     //entity se nikaal ke model me save kiya;
            emp.setName(employeeEntity.getName());
            emp.setPhone(employeeEntity.getPhone());
            emp.setEmail(employeeEntity.getEmail());

            employees.add(emp);
        }
        return employees;
    }

    @Override
    public boolean deleteEmployee(Long id) {
        // if (employeeRepository.existsById(id)) {
        //     employeeRepository.deleteById(id);
        //     return true;
        // }
        // return false;

        EmployeeEntity emp = employeeRepository.findById(id).get();
        employeeRepository.delete(emp);
        return true;

    }

    @Override
    public String updateEmployee(Long id, Employee employee) {
        EmployeeEntity existingEntity = employeeRepository.findById(id).get();

        existingEntity.setEmail(employee.getEmail());
        existingEntity.setName(employee.getName());
        existingEntity.setPhone(employee.getPhone());

        employeeRepository.save(existingEntity);
        return "Updated Successfully";
    }

}
