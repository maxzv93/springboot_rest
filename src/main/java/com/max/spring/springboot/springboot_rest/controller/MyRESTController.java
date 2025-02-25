package com.max.spring.springboot.springboot_rest.controller;

import com.max.spring.springboot.springboot_rest.entity.Employee;
import com.max.spring.springboot.springboot_rest.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST контроллер.
 *
 * @author ZuevMYu
 * @since 03.11.2024
 */
@RestController
@RequestMapping("/api")
public class MyRESTController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public List<Employee> shawAllEmployees() {
        List<Employee> allEmployees = employeeService.getAllEmployees();
        return allEmployees;
    }

    @GetMapping("/employees/{id}")
    public Employee shawAllEmployee(@PathVariable int id) {
        Employee employee = employeeService.getEmployee(id);
        return employee;
    }

    @PostMapping("/employees")
    public Employee addNewEmployee(@RequestBody Employee employee) {
        employeeService.saveEmployee(employee);
        //отправим сообщение в очередь AMQ
        ActiveMqService activeMqService = new ActiveMqServiceImpl();
        KafkaService kafkaService = new KafkaServiceImpl();
        //отправляем сообщение о регистрации нового сотрудника в ActiveMq
        String message = "Зарегистрирован новый сотрудник " + employee.toString();
        try {
            activeMqService.enqueueAMQMessage(message);
        }catch (Exception e){
            //skip
        }
        try{
            kafkaService.enqueueKafkaMessage(message);
        }catch (Exception e){
            //skip
        }
        return employee;
    }

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee) {
        employeeService.saveEmployee(employee);
        return employee;
    }

    @DeleteMapping("/employees/{id}")
    public String deleteEmployee(@PathVariable int id) {
        employeeService.deleteEmployee(id);
        return "Employee with id = " + id + " was deleted";
    }
}










