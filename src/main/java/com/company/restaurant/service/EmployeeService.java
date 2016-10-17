package com.company.restaurant.service;

import com.company.restaurant.model.Employee;
import com.company.restaurant.model.JobPosition;

import java.util.List;

/**
 * Created by Yevhen on 17.06.2016.
 */
public interface EmployeeService {

    JobPosition findJobPositionByName(String name);

    JobPosition findJobPositionById(int jobPositionId);

    List<JobPosition> findAllJobPositions();

    Employee updEmployee(Employee employee);

    void delEmployee(int employeeId);

    List<Employee> findAllEmployees();

    Employee findEmployeeById(int employeeId);

}
