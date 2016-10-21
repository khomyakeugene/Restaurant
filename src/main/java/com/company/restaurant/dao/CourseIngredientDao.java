package com.company.restaurant.dao;

import com.company.restaurant.model.Course;
import com.company.restaurant.model.CourseIngredient;
import com.company.restaurant.model.Ingredient;
import com.company.restaurant.model.Portion;

/**
 * Created by Yevhen on 04.07.2016.
 */
public interface CourseIngredientDao {
    CourseIngredient addCourseIngredient(Course course, Ingredient ingredient, Portion portion, Float amount);

    void delCourseIngredient(Course course, Ingredient ingredient);
}
