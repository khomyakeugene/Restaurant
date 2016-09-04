package com.company.restaurant.web.admin.application;

import com.company.restaurant.model.Employee;
import com.company.restaurant.service.EmployeeService;
import com.company.restaurant.web.admin.application.proto.AdminApplicationController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Yevhen on 04.09.2016.
 */
@Controller
public class AdminEmployeeController extends AdminApplicationController {
    private static final String ADMIN_EMPLOYEE_LIST_PAGE_VIEW_NAME = "admin-application/admin-employee-list-page";
    private static final String ADMIN_EMPLOYEE_PAGE_VIEW_NAME = "admin-application/admin-employee-page";
    private static final String ADMIN_EMPLOYEE_LIST_REQUEST_MAPPING_VALUE = "/admin-employee-list";
    private static final String ADMIN_EMPLOYEE_REQUEST_MAPPING_VALUE = "/employee/{employeeId}";
    private static final String SAVE_EMPLOYEE_REQUEST_MAPPING_VALUE = "/save-employee";

    private static final String EMPLOYEES_VAR_NAME = "employees";
    private static final String EMPLOYEE_VAR_NAME = "employee";
    private static final String JOB_POSITIONS_VAR_NAME = "jobPositions";
    private static final String JOB_POSITION_NAME_VAR_NAME = "jobPositionName";
    private static final String JOB_POSITION_NAMES_VAR_NAME = "jobPositionNames";
    private static final String EMPLOYEE_ID_VAR_NAME = "employeeId";
    private static final String EMPLOYEE_FIRST_NAME_VAR_NAME = "employeeFirstName";
    private static final String EMPLOYEE_SECOND_NAME_VAR_NAME = "employeeSecondName";
    private static final String EMPLOYEE_JOB_POSITION_NAME_VAR_NAME = "jobPositionName";
    private static final String EMPLOYEE_PHONE_NUMBER_VAR_NAME = "employeePhoneNumber";
    private static final String EMPLOYEE_SALARY_VAR_NAME = "employeeSalary";
    private static final String EMPLOYEE_PHOTO_VAR_NAME = "employeePhoto";

    private EmployeeService employeeService;

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @RequestMapping(value = ADMIN_EMPLOYEE_LIST_REQUEST_MAPPING_VALUE, method = RequestMethod.GET)
    public ModelAndView employeeListPage() {
        modelAndView.addObject(EMPLOYEES_VAR_NAME, employeeService.findAllEmployees());
        modelAndView.setViewName(ADMIN_EMPLOYEE_LIST_PAGE_VIEW_NAME);

        return modelAndView;
    }

    @RequestMapping(value = ADMIN_EMPLOYEE_REQUEST_MAPPING_VALUE, method = RequestMethod.GET)
    public ModelAndView employee(@PathVariable int employeeId) {
        Employee employee = employeeService.findEmployeeById(employeeId);

        modelAndView.addObject(EMPLOYEE_VAR_NAME, employee);
        modelAndView.addObject(JOB_POSITION_NAME_VAR_NAME, employee.getJobPosition().getName());

        modelAndView.addObject(JOB_POSITIONS_VAR_NAME, employeeService.findAllJobPositions());
        modelAndView.addObject(JOB_POSITION_NAMES_VAR_NAME, employeeService.findAllJobPositionNames());
        modelAndView.setViewName(ADMIN_EMPLOYEE_PAGE_VIEW_NAME);

        return modelAndView;
    }

    @RequestMapping(value = SAVE_EMPLOYEE_REQUEST_MAPPING_VALUE , method = RequestMethod.POST)
    public ModelAndView saveEmployee(@RequestParam(EMPLOYEE_ID_VAR_NAME) int employeeId,
                                     @RequestParam(EMPLOYEE_FIRST_NAME_VAR_NAME) String  employeeFirstName,
                                     @RequestParam(EMPLOYEE_SECOND_NAME_VAR_NAME) String  employeeSecondName,
                                     @RequestParam(EMPLOYEE_JOB_POSITION_NAME_VAR_NAME) String jobPositionName,
                                     @RequestParam(EMPLOYEE_PHONE_NUMBER_VAR_NAME) String employeePhoneNumber,
                                     @RequestParam(EMPLOYEE_SALARY_VAR_NAME) Float employeeSalary
//                                     @RequestParam(EMPLOYEE_PHOTO_VAR_NAME) byte[] employeePhoto
    ) {
        Employee employee = new Employee();
        employee.setEmployeeId(employeeId);
        employee.setFirstName(employeeFirstName);
        employee.setSecondName(employeeSecondName);
        employee.setJobPosition(employeeService.findJobPositionByName(jobPositionName));
        employee.setPhoneNumber(employeePhoneNumber);
        employee.setSalary(employeeSalary);
//        employee.setPhoto(employeePhoto);
        employeeService.updEmployee(employee);

        modelAndView.setViewName(ADMIN_EMPLOYEE_LIST_PAGE_VIEW_NAME);

        return modelAndView;
    }
}
