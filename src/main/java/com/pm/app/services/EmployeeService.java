package com.pm.app.services;

import java.util.List;

import com.pm.app.models.Employee;

public interface EmployeeService {
    //return all employee
    List<Employee> getAllEmployees();
    //function for save employee
    void saveEmployee(Employee employee);
    //return of the entity
    Employee getEmployeeById(Long id);

    //Delelet Employee
    void deleteEmployeeById(Long id);
}
