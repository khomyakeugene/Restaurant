package com.company.restaurant.web;

import com.company.restaurant.service.EmployeeService;
import com.company.restaurant.web.proto.CommonDataController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Yevhen on 06.08.2016.
 */
@Controller
public class EmployeeController extends CommonDataController {
    private static final String PERSONNEL_PAGE_VIEW_NAME = "/personnel-page";
    private static final String EMPLOYEES_VAR_NAME = "employees";

    private EmployeeService employeeService;

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @RequestMapping(value = "/personnel", method = RequestMethod.GET)
    public ModelAndView employeePage() {
        initData();

        modelAndView.addObject(EMPLOYEES_VAR_NAME, employeeService.findAllEmployees());
        modelAndView.setViewName(PERSONNEL_PAGE_VIEW_NAME);

        return modelAndView;
    }
}
