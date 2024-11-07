package com.max.spring.springboot.springboot_rest.service;


import com.max.spring.springboot.springboot_rest.entity.Employee;

import java.util.List;

/**
 * @author ZuevMYu
 * @since 25.07.2024
 */
public interface EmployeeService {

    public List<Employee> getAllEmployees();

    public void saveEmployee(Employee employee);

    public Employee getEmployee(int id);

    public void deleteEmployee(int id);
}
