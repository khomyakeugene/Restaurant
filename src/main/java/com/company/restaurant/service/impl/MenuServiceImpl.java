package com.company.restaurant.service.impl;

import com.company.restaurant.dao.MenuDao;
import com.company.restaurant.model.Course;
import com.company.restaurant.model.Menu;
import com.company.restaurant.service.MenuService;
import com.company.restaurant.service.impl.common.ObjectService;
import com.company.util.exception.DataIntegrityException;

import java.util.List;

/**
 * Created by Yevhen on 19.05.2016.
 */
public class MenuServiceImpl extends ObjectService<Menu> implements MenuService {
    private static final String SPECIFY_MENU_NAME_MSG = "Please, specify the menu name";
    private static final String PLEASE_SPECIFY_A_MENU_MSG = "Please, specify a menu";
    private static final String PLEASE_SPECIFY_A_COURSE_MSG = "Please, specify a course";

    private MenuDao menuDao;

    public void setMenuDao(MenuDao menuDao) {
        this.menuDao = menuDao;
    }

    @Override
    public Menu addMenu(String name) {
        if (name != null) {
            name = name.trim();
        }
        if (name == null || name.isEmpty()) {
            throw new DataIntegrityException(SPECIFY_MENU_NAME_MSG);
        }

        return menuDao.addMenu(name);
    }

    @Override
    public void delMenu(int menuId) {
        menuDao.delMenu(menuId);
    }

    @Override
    public Menu findMenuById(int menuId) {
        return menuDao.findMenuById(menuId);
    }

    @Override
    public List<Menu> findAllMenus() {
        return menuDao.findAllMenus();
    }

    @Override
    public void addCourseToMenu(Menu menu, Course course) {
        if (menu == null) {
            throw new DataIntegrityException(PLEASE_SPECIFY_A_MENU_MSG);
        }
        if (course == null) {
            throw new DataIntegrityException(PLEASE_SPECIFY_A_COURSE_MSG);
        }

        menuDao.addCourseToMenu(menu, course);
    }

    @Override
    public void delCourseFromMenu(Menu menu, Course course) {
        menuDao.delCourseFromMenu(menu, course);
    }

}
