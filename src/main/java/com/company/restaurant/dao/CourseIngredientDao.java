package com.company.restaurant.dao;

import com.company.restaurant.model.Course;
import com.company.restaurant.model.CourseIngredient;

import java.util.Set;

/**
 * Created by Yevhen on 04.07.2016.
 */
public interface CourseIngredientDao {
    Set<CourseIngredient> findCourseIngredients(Course course);
}
