package com.company.restaurant.service;

import com.company.restaurant.model.Course;
import com.company.restaurant.model.Menu;

import java.util.List;

/**
 * Created by Yevhen on 17.06.2016.
 */
public interface MenuService {
    Menu addMenu(String name);

    void delMenu(int menuId);

    Menu findMenuById(int menuId);

    List<Menu> findAllMenus();

    void addCourseToMenu(Menu menu, Course course);

    void delCourseFromMenu(Menu menu, Course course);
}
