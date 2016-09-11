package com.company.restaurant.service;

import com.company.restaurant.model.Course;
import com.company.restaurant.model.Menu;

import java.util.List;
import java.util.Set;

/**
 * Created by Yevhen on 17.06.2016.
 */
public interface MenuService {
    Menu addMenu(String name);

    void delMenu(String name);

    void delMenu(int menuId);

    void delMenu(Menu menu);

    Menu findMenuByName(String name);

    Menu findMenuById(int menuId);

    List<Menu> findAllMenus();

    List<Menu> findMenusByNameFragment(String nameFragment);

    List<String> findAllMenuNames();

    void addCourseToMenu(Menu menu, Course course);

    void delCourseFromMenu(Menu menu, Course course);

    Set<Course> findMenuCourses(Menu menu);

    Course findMenuCourseByCourseId(Menu menu, int courseId);
}
