package com.company.restaurant.web.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Yevhen on 17.09.2016.
 */
@Controller
public class StartupController extends CommonDataController  {
    private static final String STARTUP_PAGE_REQUEST_MAPPING_VALUE = "/index";
    private static final String STARTUP_PAGE_VIEW_NAME = "startup-page";

    @RequestMapping(value = STARTUP_PAGE_REQUEST_MAPPING_VALUE, method = RequestMethod.GET)
    public ModelAndView mainPage() {
        modelAndView.setViewName(STARTUP_PAGE_VIEW_NAME);

        return modelAndView;
    }
}

