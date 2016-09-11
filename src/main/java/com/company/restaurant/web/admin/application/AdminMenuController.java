package com.company.restaurant.web.admin.application;

import com.company.restaurant.model.Menu;
import com.company.restaurant.service.MenuService;
import com.company.restaurant.web.admin.application.common.AdminCRUDController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Yevhen on 03.09.2016.
 */
@Controller
public class AdminMenuController extends AdminCRUDController<Menu> {
    private static final String SUBMIT_BUTTON_ADD_MENU_VALUE = "add-menu";

    private static final String ADMIN_MENU_REQUEST_MAPPING_VALUE = "/admin-menu";
    private static final String ADMIN_APPLICATION_COURSE_REQUEST_MAPPING_VALUE = "/admin-menu/{menuId}";
    private static final String ADMIN_DELETE_MENU_REQUEST_MAPPING_VALUE = "/admin-menu/delete-menu/{menuId}";
    private static final String ADMIN_EDIT_MENUS_REQUEST_MAPPING_VALUE = "/edit-menus";

    private static final String ADMIN_MENU_PAGE_VIEW_NAME = "admin-application/menu/admin-menu-page";

    private static final String MENUS_VAR_NAME = "menus";
    private static final String MENU_VAR_NAME = "menu";
    private static final String MENU_NAME_VAR_NAME = "menuName";

    private MenuService menuService;

    @Autowired
    public void setMenuService(MenuService menuService) {
        this.menuService = menuService;
    }

    private void initMenuList() {
        modelAndView.addObject(MENUS_VAR_NAME, menuService.findAllMenus());
    }

    private Menu addMenu(String menuName) {
        return menuService.addMenu(menuName);
    }

    private ModelAndView returnToMenuListPage() {
        return new ModelAndView(REDIRECT_PREFIX + ADMIN_MENU_REQUEST_MAPPING_VALUE);
    }

    private boolean isSubmitAddMenu(String submitButtonValue) {
        return isSubmitValue(submitButtonValue, SUBMIT_BUTTON_ADD_MENU_VALUE);
    }

    @RequestMapping(value = ADMIN_MENU_REQUEST_MAPPING_VALUE, method = RequestMethod.GET)
    public ModelAndView menuListPage() {
        clearErrorMessage();

        initMenuList();

        modelAndView.setViewName(ADMIN_MENU_PAGE_VIEW_NAME);

        return modelAndView;
    }

    @RequestMapping(value = ADMIN_EDIT_MENUS_REQUEST_MAPPING_VALUE, method = RequestMethod.POST)
    public ModelAndView editMenus(
            @RequestParam(MENU_NAME_VAR_NAME) String menuName,
            @RequestParam(SUBMIT_BUTTON_VAR_NAME) String submitButtonValue
    ) {
        if (isSubmitSave(submitButtonValue)) {
        } else if (isSubmitDelete(submitButtonValue)) {
        } else if (isSubmitAddMenu(submitButtonValue)) {
            addMenu(menuName);
        }

        return returnToMenuListPage();
    }

    @RequestMapping(value = ADMIN_DELETE_MENU_REQUEST_MAPPING_VALUE, method = RequestMethod.GET)
    public ModelAndView deleteMenu(@PathVariable int menuId) {
        menuService.delMenu(menuId);

        return returnToMenuListPage();
    }
}
