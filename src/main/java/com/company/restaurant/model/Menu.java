package com.company.restaurant.model;

import com.company.restaurant.model.common.CourseCollecting;
import com.company.restaurant.model.common.SimpleDic;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Yevhen on 20.05.2016.
 */
public class Menu extends SimpleDic implements CourseCollecting {
    private Set<Course> courses = new HashSet<>();

    public int getMenuId() {
        return getId();
    }

    public void setMenuId(int menuId) {
        setId(menuId);
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }
}
