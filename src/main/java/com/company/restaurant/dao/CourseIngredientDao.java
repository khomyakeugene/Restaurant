package com.company.restaurant.dao;

import com.company.restaurant.model.Course;
import com.company.restaurant.model.CourseIngredient;
import com.company.restaurant.model.Ingredient;
import com.company.restaurant.model.Portion;

import java.util.Set;

/**
 * Created by Yevhen on 04.07.2016.
 */
public interface CourseIngredientDao {
    Set<CourseIngredient> findCourseIngredients(Course course);

    CourseIngredient addCourseIngredient(Course course, Ingredient ingredient, Portion portion, Float amount);

    void delCourseIngredient(Course course, Ingredient ingredient);

    CourseIngredient addCourseIngredient(int courseId, int ingredientId, int portionId, Float amount);

    void delCourseIngredient(int courseId, int ingredientId);
}
