package com.company.restaurant.service.impl;

import com.company.restaurant.dao.CourseCategoryDao;
import com.company.restaurant.dao.CourseDao;
import com.company.restaurant.dao.IngredientDao;
import com.company.restaurant.model.Course;
import com.company.restaurant.model.CourseCategory;
import com.company.restaurant.model.Ingredient;
import com.company.restaurant.model.Portion;
import com.company.restaurant.service.CourseService;
import com.company.restaurant.service.impl.common.Service;

import java.util.List;

public class CourseServiceImpl extends Service implements CourseService {
    private static final String PORTION_TYPE_SHOULD_BE_GIVEN_MSG = "The portion type should be given!";
    private static final String PLEASE_ENTER_AMOUNT_MSG = "Please, enter amount";
    private static final String WEIGHT_PROPERTY_NAME = "weight";
    private static final String COST_PROPERTY_NAME = "cost";

    private CourseCategoryDao courseCategoryDao;
    private CourseDao courseDao;
    private IngredientDao ingredientDao;

    private void validateWeight(Float weight) {
        validateNotNullFloatPropertyPositiveness(WEIGHT_PROPERTY_NAME, weight);
    }

    private void validateCost(Float cost) {
        validateNotNullFloatPropertyPositiveness(COST_PROPERTY_NAME, cost);
    }

    private void validateCourse(Course course) {
        validateWeight(course.getWeight());
        validateCost(course.getCost());
    }

    private void validateCourseIngredient(Course course, Ingredient ingredient, Portion portion, Float amount) {
        validateAmountPositiveness(amount);

        if (amount != null && portion == null) {
            throwDataIntegrityException(PORTION_TYPE_SHOULD_BE_GIVEN_MSG);
        }
        if (amount == null && portion != null) {
            throwDataIntegrityException(PLEASE_ENTER_AMOUNT_MSG);
        }
    }

    public void setCourseCategoryDao(CourseCategoryDao courseCategoryDao) {
        this.courseCategoryDao = courseCategoryDao;
    }

    public void setCourseDao(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    public void setIngredientDao(IngredientDao ingredientDao) {
        this.ingredientDao = ingredientDao;
    }

    @Override
    public CourseCategory findCourseCategoryByName(String name) {
        return courseCategoryDao.findCourseCategoryByName(name);
    }

    @Override
    public CourseCategory findCourseCategoryById(int CourseCategoryId) {
        return courseCategoryDao.findCourseCategoryById(CourseCategoryId);
    }

    @Override
    public List<CourseCategory> findAllCourseCategories() {
        return courseCategoryDao.findAllCourseCategories();
    }

    @Override
    public Course updCourse(Course course) {
        validateCourse(course);

        return courseDao.updCourse(course);
    }

    @Override
    public void delCourse(int courseId) {
        courseDao.delCourse(courseId);
    }

    @Override
    public Course findCourseById(int courseId) {
        return courseDao.findCourseById(courseId);
    }

    @Override
    public List<Course> findAllCourses() {
        return courseDao.findAllCourses();
    }

    @Override
    public List<Course> findCoursesByNameFragment(String nameFragment) {
        return courseDao.findCoursesByNameFragment(nameFragment);
    }

    @Override
    public Course addCourseIngredient(Course course, Ingredient ingredient, Portion portion, Float amount) {
        validateCourseIngredient(course, ingredient, portion, amount);

        return courseDao.addCourseIngredient(course, ingredient, portion, amount);
    }

    @Override
    public void delCourseIngredient(int courseId, int ingredientId) {
        courseDao.delCourseIngredient(courseDao.findCourseById(courseId), ingredientDao.findIngredientById(ingredientId));
    }
}