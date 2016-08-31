package com.company.restaurant.dao;

import com.company.restaurant.model.CourseCategory;

import java.util.List;

/**
 * Created by Yevhen on 21.05.2016.
 */
public interface CourseCategoryDao {
    CourseCategory addCourseCategory(String name);

    void delCourseCategory(String name);

    CourseCategory findCourseCategoryByName(String name);

    CourseCategory findCourseCategoryById(int courseCategoryId);

    List<CourseCategory> findAllCourseCategories();
}
