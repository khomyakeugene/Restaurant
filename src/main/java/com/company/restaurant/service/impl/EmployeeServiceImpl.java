package com.company.restaurant.service.impl;

import com.company.restaurant.dao.EmployeeDao;
import com.company.restaurant.dao.JobPositionDao;
import com.company.restaurant.model.Employee;
import com.company.restaurant.model.JobPosition;
import com.company.restaurant.service.EmployeeService;
import com.company.restaurant.service.impl.common.ObjectService;

import java.util.List;

public class EmployeeServiceImpl extends ObjectService<Employee> implements EmployeeService {
    private static final String OPERATION_IS_NOT_SUPPORTED_PATTERN =
            "<%s>: operation is not supported for <employee> with id <%d> (instance of <%s>)";
    private static final String NAME_PROPERTY_NAME = "name";
    private static final String SALARY_PROPERTY_NAME = "salary";

    private JobPositionDao jobPositionDao;
    private EmployeeDao employeeDao;

    private void operationIsNotSupportedMessage(String message, Employee employee) {
        throwDataIntegrityException(String.format(OPERATION_IS_NOT_SUPPORTED_PATTERN, message, employee.getEmployeeId(),
                employee.getClass().getSimpleName()));
    }

    private void validateName(String name) {
        validateNotNullProperty(NAME_PROPERTY_NAME, name);
    }

    private void validateSalary(Float salary) {
        validateFloatPropertyPositiveness(SALARY_PROPERTY_NAME, salary);
    }

    private void validateEmployee(Employee employee) {
        if (employee != null) {
            validateName(employee.getName());
            validateSalary(employee.getSalary());
        }
    }

    public void setJobPositionDao(JobPositionDao jobPositionDao) {
        this.jobPositionDao = jobPositionDao;
    }

    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Override
    public JobPosition findJobPositionByName(String name) {
        return jobPositionDao.findJobPositionByName(name);
    }

    @Override
    public JobPosition findJobPositionById(int jobPositionId) {
        return jobPositionDao.findJobPositionById(jobPositionId);
    }

    @Override
    public List<JobPosition> findAllJobPositions() {
        return jobPositionDao.findAllJobPositions();
    }

    @Override
    public Employee updEmployee(Employee employee) {
        validateEmployee(employee);

        return employeeDao.updEmployee(employee);
    }

    @Override
    public void delEmployee(int employeeId) {
        employeeDao.delEmployee(employeeId);
    }

    @Override
    public List<Employee> findAllEmployees() {
        return employeeDao.findAllEmployees();
    }

    @Override
    public Employee findEmployeeById(int employeeId) {
        return employeeDao.findEmployeeById(employeeId);
    }

}