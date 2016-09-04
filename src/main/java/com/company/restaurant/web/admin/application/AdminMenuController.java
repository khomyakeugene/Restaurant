package com.company.restaurant.web.admin.application;

import com.company.restaurant.service.MenuService;
import com.company.restaurant.web.admin.application.proto.AdminApplicationController;
import org.springframework.beans.factory.annotation.Autowired;
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
    private static final String ADMIN_MENU_REQUEST_MAPPING_VALUE = "/admin-menu";
    private static final String MENUS_VAR_NAME = "menus";
    private static final String MENU_VAR_NAME = "menu";

    private MenuService menuService;

    @Autowired
    public void setMenuService(MenuService menuService) {
        this.menuService = menuService;
    }

    @RequestMapping(value = ADMIN_MENU_REQUEST_MAPPING_VALUE, method = RequestMethod.GET)
    public ModelAndView menuPage() {
        modelAndView.clear();

        modelAndView.addObject(MENUS_VAR_NAME, menuService.findAllMenus());
        modelAndView.setViewName(ADMIN_MENU_PAGE_VIEW_NAME);

        return modelAndView;
    }
}
