package com.company.restaurant.dao.hibernate;

import com.company.restaurant.dao.MenuDao;
import com.company.restaurant.dao.hibernate.common.HDaoEntityCourseCollecting;
import com.company.restaurant.model.Course;
import com.company.restaurant.model.Menu;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Yevhen on 10.06.2016.
 */
public class HMenuDao extends HDaoEntityCourseCollecting<Menu> implements MenuDao {
    @Override
    protected void initMetadata() {
        this.orderByAttributeName = nameAttributeName;
    }

    @Transactional
    @Override
    public Menu addMenu(String name) {
        return save(name);
    }

    @Transactional
    @Override
    public void delMenu(String name) {
        delete(name);
    }

    @Transactional
    @Override
    public void delMenu(Menu menu) {
        delete(menu);
    }

    @Transactional
    @Override
    public void delMenu(int menuId) {
        delete(menuId);
    }

    @Transactional
    @Override
    public Menu findMenuByName(String name) {
        return findObjectByName(name);
    }

    @Transactional
    @Override
    public Menu findMenuById(int menuId) {
        return findObjectById(menuId);
    }

    @Transactional
    @Override
    public List<Menu> findAllMenus() {
        return findAllObjects();
    }

    @Transactional
    @Override
    public List<Menu> findMenusByNameFragment(String nameFragment) {
        return findObjectsByNameFragment(nameFragment);
    }

    @Transactional
    @Override
    public void addCourseToMenu(Menu menu, Course course) {
        menu.getCourses().add(course);
        update(menu);
    }

    @Transactional
    @Override
    public void delCourseFromMenu(Menu menu, Course course) {
        menu.getCourses().remove(course);
        update(menu);
    }

    @Transactional
    @Override
    public Set<Course> findMenuCourses(Menu menu) {
        return new HashSet<>(findCourses(menu));
    }

    @Transactional
    @Override
    public Course findMenuCourseByCourseId(Menu menu, int courseId) {
        return findCourseByCourseId(menu,courseId);
    }
}
