package com.company.restaurant.web.admin.application;

import com.company.restaurant.model.Employee;
import com.company.restaurant.service.EmployeeService;
import com.company.restaurant.web.admin.application.common.AdminCRUDController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.stream.Collectors;

/**
 * Created by Yevhen on 04.09.2016.
 */
@Controller
public class AdminEmployeeController extends AdminCRUDController<Employee> {
    private static final String ADMIN_EMPLOYEE_LIST_PAGE_VIEW_NAME = "admin-application/employee/admin-employee-list-page";
    private static final String ADMIN_SAVE_OR_DELETE_EMPLOYEE_PAGE_VIEW_NAME = "admin-application/employee/admin-save-or-delete-employee-page";
    private static final String ADMIN_CREATE_EMPLOYEE_PAGE_VIEW_NAME = "admin-application/employee/admin-create-employee-page";
    private static final String ADMIN_EMPLOYEE_LIST_REQUEST_MAPPING_VALUE = "/admin-employee-list";
    private static final String ADMIN_EMPLOYEE_REQUEST_MAPPING_VALUE = "/employee/{employeeId}";
    private static final String ADMIN_SAVE_OR_DELETE_EMPLOYEE_REQUEST_MAPPING_VALUE = "/save-or-delete-employee";
    private static final String ADMIN_PREPARE_NEW_EMPLOYEE_REQUEST_MAPPING_VALUE = "/prepare-new-employee";
    private static final String ADMIN_CREATE_EMPLOYEE_REQUEST_MAPPING_VALUE = "/create-employee";

    private static final String EMPLOYEES_VAR_NAME = "employees";
    private static final String EMPLOYEE_VAR_NAME = "employee";
    private static final String JOB_POSITION_NAME_VAR_NAME = "jobPositionName";
    private static final String JOB_POSITION_NAMES_VAR_NAME = "jobPositionNames";
    private static final String EMPLOYEE_ID_VAR_NAME = "employeeId";
    private static final String EMPLOYEE_FIRST_NAME_VAR_NAME = "employeeFirstName";
    private static final String EMPLOYEE_SECOND_NAME_VAR_NAME = "employeeSecondName";
    private static final String EMPLOYEE_JOB_POSITION_NAME_VAR_NAME = "jobPositionName";
    private static final String EMPLOYEE_PHONE_NUMBER_VAR_NAME = "employeePhoneNumber";
    private static final String EMPLOYEE_SALARY_VAR_NAME = "employeeSalary";
    //    private static final String EMPLOYEE_PHOTO_VAR_NAME = "employeePhoto";

    private static final String DEFAULT_JOB_POSITION_NAME_VALUE = "Waiter";

    private EmployeeService employeeService;

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    private Employee newEmployee() {
        Employee result = new Employee();
        result.setJobPosition(employeeService.findJobPositionByName(DEFAULT_JOB_POSITION_NAME_VALUE));

        return result;
    }

    private void prepareEmployeeEnvironment(int employeeId) {
        Employee employee;
        if (employeeId > 0) {
            employee = employeeService.findEmployeeById(employeeId);
            if (employee == null) {
                employee = newEmployee();
            }
        } else {
            employee = newEmployee();
        }

        // Current job position name - important to correct work of <form:select> in view
        String jobPositionName = employee.getJobPosition().getName();
        modelAndView.addObject(EMPLOYEE_VAR_NAME, employee);
        modelAndView.addObject(JOB_POSITION_NAME_VAR_NAME, jobPositionName);

        // Temporary solution: exclude <jobPositionName> from <jobPositionNames>  - important to correct work
        // of <form:select> in view
        modelAndView.addObject(JOB_POSITION_NAMES_VAR_NAME, employeeService.findAllJobPositionNames());
        modelAndView.addObject(JOB_POSITION_NAMES_VAR_NAME, employeeService.findAllJobPositionNames().stream().
                filter(n -> (!n.equals(jobPositionName))).collect(Collectors.toList()));
    }

    private void prepareEmployeeEnvironment() {
        prepareEmployeeEnvironment(0);

    }

    private Employee saveEmployee(int employeeId,
                                  String employeeFirstName,
                                  String employeeSecondName,
                                  String jobPositionName,
                                  String employeePhoneNumber,
                                  Float employeeSalary
//                              , byte[] employeePhoto
    ) {
        Employee employee = null;
        // Temporarily: get "old" image from database - because I do not know how to manipulate with image
        if (employeeId > 0) {
            employee = employeeService.findEmployeeById(employeeId);

        }
        if (employee == null) {
            employee = new Employee();
        }

        // Important to possibly called <ErrorHandler> that next redirect to "current course JSP-page" to show
        // error message and to have the possibility to correct editing parameters of "current object"
        setCurrentObject(employee);

        employee.setEmployeeId(employeeId);
        employee.setFirstName(employeeFirstName);
        employee.setSecondName(employeeSecondName);
        employee.setJobPosition(employeeService.findJobPositionByName(jobPositionName));
        employee.setPhoneNumber(employeePhoneNumber);
        employee.setSalary(employeeSalary);
//        employee.setPhoto(employeePhoto);

        return employeeService.updEmployee(employee);
    }

    private void deleteEmployee(int employeeId) {
        employeeService.delEmployee(employeeId);
    }

    @RequestMapping(value = ADMIN_EMPLOYEE_LIST_REQUEST_MAPPING_VALUE, method = RequestMethod.GET)
    public ModelAndView employeeListPage() {
        clearErrorMessage();

        modelAndView.addObject(EMPLOYEES_VAR_NAME, employeeService.findAllEmployees());
        modelAndView.setViewName(ADMIN_EMPLOYEE_LIST_PAGE_VIEW_NAME);

        return modelAndView;
    }

    @RequestMapping(value = ADMIN_EMPLOYEE_REQUEST_MAPPING_VALUE, method = RequestMethod.GET)
    public ModelAndView employee(@PathVariable int employeeId) {
        clearErrorMessage();

        prepareEmployeeEnvironment(employeeId);

        modelAndView.setViewName(ADMIN_SAVE_OR_DELETE_EMPLOYEE_PAGE_VIEW_NAME);

        return modelAndView;
    }

    @RequestMapping(value = ADMIN_SAVE_OR_DELETE_EMPLOYEE_REQUEST_MAPPING_VALUE, method = RequestMethod.POST)
    public ModelAndView saveOrDeleteEmployee(@RequestParam(EMPLOYEE_ID_VAR_NAME) int employeeId,
                                             @RequestParam(EMPLOYEE_FIRST_NAME_VAR_NAME) String employeeFirstName,
                                             @RequestParam(EMPLOYEE_SECOND_NAME_VAR_NAME) String employeeSecondName,
                                             @RequestParam(EMPLOYEE_JOB_POSITION_NAME_VAR_NAME) String jobPositionName,
                                             @RequestParam(EMPLOYEE_PHONE_NUMBER_VAR_NAME) String employeePhoneNumber,
                                             @RequestParam(EMPLOYEE_SALARY_VAR_NAME) Float employeeSalary,
//                                     @RequestParam(EMPLOYEE_PHOTO_VAR_NAME) byte[] employeePhoto,
                                             @RequestParam(SUBMIT_BUTTON_VAR_NAME) String submitButtonValue
    ) {
        if (isSubmitSave(submitButtonValue)) {
            saveEmployee(employeeId, employeeFirstName, employeeSecondName, jobPositionName, employeePhoneNumber,
                    employeeSalary);

        } else if (isSubmitDelete(submitButtonValue)) {
            deleteEmployee(employeeId);
        }

        return new ModelAndView(REDIRECT_PREFIX + ADMIN_EMPLOYEE_LIST_REQUEST_MAPPING_VALUE);
    }

    @RequestMapping(value = ADMIN_PREPARE_NEW_EMPLOYEE_REQUEST_MAPPING_VALUE, method = RequestMethod.POST)
    public ModelAndView prepareNewEmployee() {
        clearErrorMessage();

        prepareEmployeeEnvironment();

        modelAndView.setViewName(ADMIN_CREATE_EMPLOYEE_PAGE_VIEW_NAME);

        return modelAndView;
    }

    @RequestMapping(value = ADMIN_CREATE_EMPLOYEE_REQUEST_MAPPING_VALUE, method = RequestMethod.POST)
    public ModelAndView createEmployee(@RequestParam(EMPLOYEE_FIRST_NAME_VAR_NAME) String employeeFirstName,
                                       @RequestParam(EMPLOYEE_SECOND_NAME_VAR_NAME) String employeeSecondName,
                                       @RequestParam(EMPLOYEE_JOB_POSITION_NAME_VAR_NAME) String jobPositionName,
                                       @RequestParam(EMPLOYEE_PHONE_NUMBER_VAR_NAME) String employeePhoneNumber,
                                       @RequestParam(EMPLOYEE_SALARY_VAR_NAME) Float employeeSalary
//                                     , @RequestParam(EMPLOYEE_PHOTO_VAR_NAME) byte[] employeePhoto,
    ) {
        saveEmployee(0, employeeFirstName, employeeSecondName, jobPositionName, employeePhoneNumber,
                employeeSalary);

        return new ModelAndView(REDIRECT_PREFIX + ADMIN_EMPLOYEE_LIST_REQUEST_MAPPING_VALUE);
    }
}
