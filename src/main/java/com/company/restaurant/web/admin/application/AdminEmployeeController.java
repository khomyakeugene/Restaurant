package com.company.restaurant.web.admin.application;

import com.company.restaurant.model.Employee;
import com.company.restaurant.web.admin.application.common.AdminCRUDController;
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
public class AdminEmployeeController extends AdminCRUDController<Employee> {
    private static final String ADMIN_EMPLOYEE_LIST_PAGE_VIEW_NAME = "admin-application/employee/admin-employee-list-page";
    private static final String ADMIN_SAVE_OR_DELETE_EMPLOYEE_PAGE_VIEW_NAME = "admin-application/employee/admin-save-or-delete-employee-page";
    private static final String ADMIN_EMPLOYEE_LIST_REQUEST_MAPPING_VALUE = "/admin-employee-list";
    private static final String ADMIN_EMPLOYEE_REQUEST_MAPPING_VALUE = "/employee/{employeeId}";
    private static final String ADMIN_SAVE_OR_DELETE_EMPLOYEE_REQUEST_MAPPING_VALUE = "/save-or-delete-employee";
    private static final String ADMIN_PREPARE_NEW_EMPLOYEE_REQUEST_MAPPING_VALUE = "/prepare-new-employee";
    private static final String ADMIN_UPLOAD_EMPLOYEE_PHOTO_REQUEST_MAPPING_VALUE = "/upload-employee-photo";

    private static final String EMPLOYEES_VAR_NAME = "employees";

    private static final String EMPLOYEE_ID_PAR_NAME = "employeeId";
    private static final String EMPLOYEE_FIRST_NAME_PAR_NAME = "employeeFirstName";
    private static final String EMPLOYEE_SECOND_NAME_PAR_NAME = "employeeSecondName";
    private static final String EMPLOYEE_JOB_POSITION_ID_PAR_NAME = "jobPositionId";
    private static final String EMPLOYEE_PHONE_NUMBER_PAR_NAME = "employeePhoneNumber";
    private static final String EMPLOYEE_SALARY_PAR_NAME = "employeeSalary";
    //    private static final String EMPLOYEE_PHOTO_VAR_NAME = "employeePhoto";

    private static final String DEFAULT_JOB_POSITION_NAME_VALUE = "Waiter";

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
        setCurrentObject(employee);
    }

    private void prepareEmployeeEnvironment() {
        prepareEmployeeEnvironment(0);

    }

    private Employee saveEmployee(int employeeId,
                                  String employeeFirstName,
                                  String employeeSecondName,
                                  int jobPositionId,
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
        employee.setJobPosition(employeeService.findJobPositionById(jobPositionId));
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
    public ModelAndView saveOrDeleteEmployee(@RequestParam(EMPLOYEE_ID_PAR_NAME) int employeeId,
                                             @RequestParam(EMPLOYEE_FIRST_NAME_PAR_NAME) String employeeFirstName,
                                             @RequestParam(EMPLOYEE_SECOND_NAME_PAR_NAME) String employeeSecondName,
                                             @RequestParam(EMPLOYEE_JOB_POSITION_ID_PAR_NAME) int jobPositionId,
                                             @RequestParam(EMPLOYEE_PHONE_NUMBER_PAR_NAME) String employeePhoneNumber,
                                             @RequestParam(EMPLOYEE_SALARY_PAR_NAME) Float employeeSalary,
//                                     @RequestParam(EMPLOYEE_PHOTO_VAR_NAME) byte[] employeePhoto,
                                             @RequestParam(SUBMIT_BUTTON_PAR_NAME) String submitButtonValue
    ) {
        if (isSubmitSave(submitButtonValue)) {
            saveEmployee(employeeId, employeeFirstName, employeeSecondName, jobPositionId, employeePhoneNumber,
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

        modelAndView.setViewName(ADMIN_SAVE_OR_DELETE_EMPLOYEE_PAGE_VIEW_NAME);

        return modelAndView;
    }

    @RequestMapping(value = ADMIN_UPLOAD_EMPLOYEE_PHOTO_REQUEST_MAPPING_VALUE, method = RequestMethod.POST)
    public ModelAndView uploadEmployeePhoto() {
        System.out.println("uploadEmployeePhoto");

        return modelAndView;
    }
}
