package com.company.restaurant.service;

import com.company.restaurant.model.CookedCourse;
import com.company.restaurant.model.Employee;
import com.company.restaurant.model.JobPosition;
import com.company.restaurant.model.Order;

import java.util.List;
import java.util.Set;

/**
 * Created by Yevhen on 17.06.2016.
 */
public interface EmployeeService {
    JobPosition addJobPosition(String name);

    void delJobPosition(String name);

    JobPosition findJobPositionByName(String name);

    JobPosition findJobPositionById(int jobPositionId);

    List<JobPosition> findAllJobPositions();

    List<String> findAllJobPositionNames();

    Employee addEmployee(Employee employee);

    void delEmployee(Employee employee);

    Employee updEmployee(Employee employee);

    void delEmployee(int employeeId);

    List<Employee> findAllEmployees();

    List<String> findAllEmployeeNames();

    List<Employee> findEmployeeByFirstName(String firstName);

    List<Employee> findEmployeeBySecondName(String secondName);

    List<Employee> findEmployeeByFirstAndSecondName(String firstName, String secondName);

    Employee findEmployeeById(int employeeId);

    Set<Order> getEmployeeOrders(Employee employee);

    Set<CookedCourse> getEmployeeCookedCourses(Employee employee);
}
