package com.company.restaurant.web.rest;

import com.company.restaurant.model.Employee;
import com.company.restaurant.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Yevhen on 18.08.2016.
 */

@RestController
public class EmployeeRestController {
    private EmployeeService employeeService;

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @RequestMapping(value = "/employees", method = RequestMethod.GET)
    @ResponseBody
    public List<String> getEmployeeList() {
        return employeeService.findAllEmployeeNames();
    }

    @RequestMapping(value = "/employees/employee_id/{employeeId}", method = RequestMethod.GET)
    @ResponseBody
    public Employee getEmployeeById(@PathVariable int employeeId) {
        return employeeService.findEmployeeById(employeeId);
    }

    @RequestMapping(value = "/employees/employee_first_name/{employeeFirstName}", method = RequestMethod.GET)
    @ResponseBody
    public List<Employee> getEmployeeByFirstName(@PathVariable String employeeFirstName) {
        return employeeService.findEmployeeByFirstName(employeeFirstName);
    }

    @RequestMapping(value = "/employees/employee_second_name/{employeeSecondName}", method = RequestMethod.GET)
    @ResponseBody
    public List<Employee> findEmployeeBySecondName(@PathVariable String employeeSecondName) {
        return employeeService.findEmployeeBySecondName(employeeSecondName);
    }

    @RequestMapping(value = "/employees/employee_first_and_second_name/{employeeFirstAndSecondName}", method = RequestMethod.GET)
    @ResponseBody
    public List<Employee> findEmployeeByFirstAndSecondName(@PathVariable String employeeFirstAndSecondName) {
        String firstName = null;
        String secondName = null;
        if (employeeFirstAndSecondName != null) {
            String[] splitResult = employeeFirstAndSecondName.trim().split(" ");
            if (splitResult.length > 0) {
                firstName = splitResult[0];
            }
            if (splitResult.length > 1) {
                secondName = splitResult[1];
            }
        }

        return employeeService.findEmployeeByFirstAndSecondName(firstName, secondName);
    }

}
