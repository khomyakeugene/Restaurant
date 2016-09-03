package com.company.restaurant.web.admin.application;

import com.company.restaurant.web.admin.application.proto.AdminApplicationController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Yevhen on 03.09.2016.
 */
@Controller
public class AdminMenuController extends AdminApplicationController {
    private static final String ADMIN_MENU_PAGE_VIEW_NAME = "admin-application/admin-menu-page";
    private static final String ADMIN_APPLICATION_MENU_REQUEST_MAPPING_VALUE = "/admin-application-menu";

    @RequestMapping(value = ADMIN_APPLICATION_MENU_REQUEST_MAPPING_VALUE, method = RequestMethod.GET)
    public ModelAndView mainPage() {
        initData();

        modelAndView.setViewName(ADMIN_MENU_PAGE_VIEW_NAME);

        return modelAndView;
    }

}
