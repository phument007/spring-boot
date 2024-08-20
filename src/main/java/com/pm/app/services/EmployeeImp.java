package com.pm.app.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pm.app.models.Employee;
import com.pm.app.repository.EmployeeRepository;

@Service
public class EmployeeImp implements EmployeeService {

    //Dependency injection
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public void saveEmployee(Employee employee) {
        this.employeeRepository.save(employee);
    }

    @Override
    public Employee getEmployeeById(Long id){
        Optional<Employee> optional =  employeeRepository.findById(id);

        Employee employee = null;

        if(optional.isPresent()){
            employee = optional.get();
        }else{
            throw new RuntimeException("Employee not found for id :: " + id);
        }

        return employee;
    }

    
    @Override
    public void deleteEmployeeById(Long id) {
        this.employeeRepository.deleteById(id);
    }


    
}
