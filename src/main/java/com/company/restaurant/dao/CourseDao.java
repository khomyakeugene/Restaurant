package com.company.restaurant.dao;

import com.company.restaurant.model.Course;
import com.company.restaurant.model.Ingredient;
import com.company.restaurant.model.Portion;

import java.util.List;

/**
 * Created by Yevhen on 19.05.2016.
 */
public interface CourseDao {
    Course addCourse(Course course);

    Course updCourse(Course course);

    void updCoursePhoto(int courseId, byte[] photo);

    void delCourse(Course course);

    void delCourse(int courseId);

    void delCourse(String name);

    Course findCourseById(int courseId);

    Course findCourseByName(String name);

    List<Course> findAllCourses();

    List<Course> findCoursesByNameFragment(String nameFragment);

    Course addCourseIngredient(Course course, Ingredient ingredient, Portion portion, Float amount);

    void delCourseIngredient(Course course, Ingredient ingredient);
}
