package com.company.restaurant.service;

import com.company.restaurant.model.*;

import java.util.List;

/**
 * Created by Yevhen on 17.06.2016.
 */
public interface CourseService {
    CourseCategory findCourseCategoryByName(String name);

    CourseCategory findCourseCategoryById(int CourseCategoryId);

    List<CourseCategory> findAllCourseCategories();

    Course updCourse(Course course);

    void delCourse(int courseId);

    Course findCourseById(int courseId);

    List<Course> findAllCourses();

    List<Course> findCoursesByNameFragment(String nameFragment);

    CourseIngredient addCourseIngredient(Course course, Ingredient ingredient, Portion portion, Float amount);

    void delCourseIngredient(int courseId, int ingredientId);
}
