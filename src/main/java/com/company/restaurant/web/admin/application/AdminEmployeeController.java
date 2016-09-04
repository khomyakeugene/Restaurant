package com.company.restaurant.web.admin.application;

import com.company.restaurant.service.EmployeeService;
import com.company.restaurant.web.admin.application.proto.AdminApplicationController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Yevhen on 04.09.2016.
 */
@Controller
public class AdminEmployeeController extends AdminApplicationController {
    private static final String ADMIN_EMPLOYEE_LIST_PAGE_VIEW_NAME = "admin-application/admin-employee-list-page";
    private static final String ADMIN_EMPLOYEE_LIST_REQUEST_MAPPING_VALUE = "/admin-employee-list";
    private static final String EMPLOYEES_VAR_NAME = "employees";

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
}
