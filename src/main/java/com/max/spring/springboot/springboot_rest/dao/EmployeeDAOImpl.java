package com.max.spring.springboot.springboot_rest.dao;

import com.max.spring.springboot.springboot_rest.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ZuevMYu
 * @since 17.07.2024
 */
@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Employee> getAllEmployees() {
//        Session session = entityManager.unwrap(Session.class);
//        List<Employee> allEmployees = session.createQuery("from Employee", Employee.class).getResultList();

//        Query<Employee> query = session.createQuery("from Employee", Employee.class);
//        List<Employee> resultList = query.getResultList();

        Query query = entityManager.createQuery("from Employee");
        List<Employee> employees = query.getResultList();
        return employees;
    }

    @Override
    public void saveEmployee(Employee employee) {
//        Session session = null;
//        try {
//            session = entityManager.unwrap(Session.class);
//        } catch ( Exception e) {
//            session = entityManager.unwrap(Session.class);
//        }

        Employee newEmp = entityManager.merge(employee);
        employee.setId(newEmp.getId());

    }

    @Override
    public Employee getEmployee(int id) {
//        Session session = null;
//        try {
//            session = entityManager.unwrap(Session.class);
//            Employee employee = session.get(Employee.class, id);
//            return employee;
//        } catch (Exception e) {
//            session = entityManager.unwrap(Session.class);
//        }

        Employee employee = entityManager.find(Employee.class, id);
        return employee;
    }

    @Override
    public void deleteEmployee(int id) {
//        Session session = null;
//        try {
//            session = entityManager.unwrap(Session.class);
//        } catch (Exception e) {
//            session = entityManager.unwrap(Session.class);
//        }
//        MutationQuery query = session.createMutationQuery("delete from Employee " + "where id = :employeeId");
//        query.setParameter("employeeId", id);
//        query.executeUpdate();

        Query query =entityManager.createQuery("delete from Employee " + "where id = :employeeId");
        query.setParameter("employeeId", id);
        query.executeUpdate();
    }
}
