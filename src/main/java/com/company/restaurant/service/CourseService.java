package com.company.restaurant.service;

import com.company.restaurant.model.Course;
import com.company.restaurant.model.CourseCategory;

import java.util.List;

/**
 * Created by Yevhen on 17.06.2016.
 */
public interface CourseService {
    CourseCategory addCourseCategory(String name);

    void delCourseCategory(String name);

    CourseCategory findCourseCategoryByName(String name);

    CourseCategory findCourseCategoryById(int CourseCategoryId);

    List<CourseCategory> findAllCourseCategories();

    List<String> findAllCourseCategoryNames();

    Course addCourse(Course course);

    Course updCourse(Course course);

    void delCourse(Course course);

    void delCourse(int courseId);

    void delCourse(String name);

    Course findCourseByName(String name);

    Course findCourseById(int courseId);

    List<Course> findAllCourses();

    List<Course> findCoursesByNameFragment(String nameFragment);
}
