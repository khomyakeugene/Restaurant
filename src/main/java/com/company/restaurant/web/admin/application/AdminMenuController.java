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

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Created by Yevhen on 03.09.2016.
 */
@Controller
public class AdminMenuController extends AdminCRUDController<Menu> {
    private static final String SUBMIT_BUTTON_ADD_MENU_VALUE = "add-menu";
    private static final String SUBMIT_BUTTON_ADD_COURSE_VALUE = "add-course";

    private static final String ADMIN_MENU_REQUEST_MAPPING_VALUE = "/admin-menu";
    private static final String ADMIN_APPLICATION_MENU_REQUEST_MAPPING_VALUE = "/admin-menu/{menuId}";
    private static final String ADMIN_DELETE_MENU_REQUEST_MAPPING_VALUE = "/admin-menu/delete-menu/{menuId}";
    private static final String ADMIN_EDIT_MENUS_REQUEST_MAPPING_VALUE = "/edit-menus";
    private static final String ADMIN_DELETE_MENU_COURSE_REQUEST_MAPPING_VALUE =
            "/admin-menu/delete-menu-course/{menuId}/{courseId}";

    private static final String ADMIN_MENU_PAGE_VIEW_NAME = "admin-application/menu/admin-menu-page";

    private static final String MENUS_VAR_NAME = "menus";
    private static final String NEW_COURSES_VAR_NAME = "newCourses";

    private static final String MENU_NAME_PAR_NAME = "menuName";
    private static final String COURSE_ID_PAR_NAME = "courseId";

    private MenuService menuService;

    @Autowired
    public void setMenuService(MenuService menuService) {
        this.menuService = menuService;
    }

    private void initMenuList() {
        modelAndView.addObject(MENUS_VAR_NAME, menuService.findAllMenus());
    }

    private void initNewCourseList(Menu menu) {
        modelAndView.addObject(NEW_COURSES_VAR_NAME, (menu == null) ? new ArrayList<>() :
                courseService.findAllCourses().stream().filter(course -> !menu.getCourses().stream().
                        filter(menuCourse -> ((menuCourse != null) && menuCourse.equals(course))).findAny().isPresent()).
                        collect(Collectors.toList()));
    }

    private ModelAndView returnToMenuListPage() {
        return new ModelAndView(REDIRECT_PREFIX + ADMIN_MENU_REQUEST_MAPPING_VALUE);
    }

    private boolean isSubmitAddMenu(String submitButtonValue) {
        return isSubmitValue(submitButtonValue, SUBMIT_BUTTON_ADD_MENU_VALUE);
    }

    private boolean isSubmitAddCourse(String submitButtonValue) {
        return isSubmitValue(submitButtonValue, SUBMIT_BUTTON_ADD_COURSE_VALUE);
    }

    private Menu addMenu(String menuName) {
        return setCurrentObject(menuService.addMenu(menuName));
    }

    private void addCourseToMenu(int courseId) {
        Menu menu = getCurrentObject();

        if (menu != null) {
            menuService.addCourseToMenu(menu, courseService.findCourseById(courseId));
        }
    }

    private void deleteCourseFromMenu(int menuId, int courseId) {
        menuService.delCourseFromMenu(menuService.findMenuById(menuId), courseService.findCourseById(courseId));
    }

    @RequestMapping(value = ADMIN_MENU_REQUEST_MAPPING_VALUE, method = RequestMethod.GET)
    public ModelAndView menuListPage() {
        clearErrorMessage();

        initMenuList();
        initNewCourseList(getCurrentObject());

        modelAndView.setViewName(ADMIN_MENU_PAGE_VIEW_NAME);

        return modelAndView;
    }

    @RequestMapping(value = ADMIN_EDIT_MENUS_REQUEST_MAPPING_VALUE, method = RequestMethod.POST)
    public ModelAndView editMenus(
            @RequestParam(MENU_NAME_PAR_NAME) String menuName,
            @RequestParam(COURSE_ID_PAR_NAME) int courseId,
            @RequestParam(SUBMIT_BUTTON_PAR_NAME) String submitButtonValue
    ) {
        if (isSubmitAddMenu(submitButtonValue)) {
            addMenu(menuName);
        } else if (isSubmitAddCourse(submitButtonValue)) {
            addCourseToMenu(courseId);
        }

        return returnToMenuListPage();
    }

    @RequestMapping(value = ADMIN_DELETE_MENU_REQUEST_MAPPING_VALUE, method = RequestMethod.GET)
    public ModelAndView deleteMenu(@PathVariable int menuId) {
        menuService.delMenu(menuId);

        Menu currentMenu = getCurrentObject();
        if (currentMenu != null && menuService.findMenuById(currentMenu.getMenuId()) == null) {
            nullCurrentObject();
        }

        return returnToMenuListPage();
    }

    @RequestMapping(value = ADMIN_APPLICATION_MENU_REQUEST_MAPPING_VALUE, method = RequestMethod.GET)
    public ModelAndView menu(@PathVariable int menuId) {
        setCurrentObject(menuService.findMenuById(menuId));

        return returnToMenuListPage();
    }

    @RequestMapping(value = ADMIN_DELETE_MENU_COURSE_REQUEST_MAPPING_VALUE, method = RequestMethod.GET)
    public ModelAndView deleteMenuCourse(@PathVariable int menuId, @PathVariable int courseId) {
        deleteCourseFromMenu(menuId, courseId);

        // Re-read current menu to renew course list
        setCurrentObject(menuService.findMenuById(menuId));

        return returnToMenuListPage();
    }
}
