package com.company.restaurant.web.user.application;

import com.company.restaurant.service.EmployeeService;
import com.company.restaurant.web.user.application.common.UserApplicationController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Yevhen on 06.08.2016.
 */
@Controller
public class UserEmployeeController extends UserApplicationController {
    private static final String PERSONNEL_PAGE_VIEW_NAME = "user-application/personnel-page";
    private static final String PERSONNEL_REQUEST_MAPPING_VALUE = "user-personnel";
    private static final String EMPLOYEES_VAR_NAME = "employees";

    private EmployeeService employeeService;

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @RequestMapping(value = PERSONNEL_REQUEST_MAPPING_VALUE, method = RequestMethod.GET)
    public ModelAndView employeePage() {
        modelAndView.addObject(EMPLOYEES_VAR_NAME, employeeService.findAllEmployees());
        modelAndView.setViewName(PERSONNEL_PAGE_VIEW_NAME);

        return modelAndView;
    }
}
