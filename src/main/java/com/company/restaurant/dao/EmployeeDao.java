package com.company.restaurant.dao;

import com.company.restaurant.model.Employee;

import java.util.List;

/**
 * Created by Yevhen on 19.05.2016.
 */
public interface EmployeeDao {
    Employee addEmployee(Employee employee);

    Employee updEmployee(Employee employee);

    void updEmployeePhoto(int employeeId, byte[] photo);

    void delEmployee(Employee employee);

    void delEmployee(int employeeId);

    Employee findEmployeeById(int employeeId);

    List<Employee> findEmployeeByFirstName(String firstName);

    List<Employee> findEmployeeBySecondName(String secondName);

    List<Employee> findEmployeeByFirstAndSecondName(String firstName, String secondName);

    List<Employee> findAllEmployees();

    byte[] getEmployeePhoto(int employeeId);
}
