package com.company.restaurant.service.impl;

import com.company.restaurant.dao.CourseCategoryDao;
import com.company.restaurant.dao.CourseDao;
import com.company.restaurant.dao.CourseIngredientDao;
import com.company.restaurant.model.*;
import com.company.restaurant.service.CourseService;
import com.company.restaurant.service.impl.common.Service;

import java.util.ArrayList;
import java.util.List;

public class CourseServiceImpl extends Service implements CourseService {
    private CourseCategoryDao courseCategoryDao;
    private CourseDao courseDao;
    private CourseIngredientDao courseIngredientDao;

    public void setCourseCategoryDao(CourseCategoryDao courseCategoryDao) {
        this.courseCategoryDao = courseCategoryDao;
    }

    public void setCourseDao(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    public void setCourseIngredientDao(CourseIngredientDao courseIngredientDao) {
        this.courseIngredientDao = courseIngredientDao;
    }

    @Override
    public CourseCategory addCourseCategory(String name) {
        return courseCategoryDao.addCourseCategory(name);
    }

    @Override
    public void delCourseCategory(String name) {
        courseCategoryDao.delCourseCategory(name);
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
    public List<String> findAllCourseCategoryNames() {
        List<String> result = new ArrayList<>();
        findAllCourseCategories().forEach(courseCategory -> result.add(courseCategory.getName()));

        return result;
    }

    @Override
    public Course addCourse(Course course) {
        return courseDao.addCourse(course);
    }

    @Override
    public Course updCourse(Course course) {
        return courseDao.updCourse(course);
    }

    @Override
    public void delCourse(Course course) {
        courseDao.delCourse(course);
    }

    @Override
    public void delCourse(int courseId) {
        courseDao.delCourse(courseId);
    }

    @Override
    public void delCourse(String name) {
        courseDao.delCourse(name);
    }

    @Override
    public Course findCourseByName(String name) {
        return courseDao.findCourseByName(name);
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
    public CourseIngredient addCourseIngredient(Course course, Ingredient ingredient, Portion portion, Float amount) {
        return courseIngredientDao.addCourseIngredient(course, ingredient, portion, amount);
    }

    @Override
    public void delCourseIngredient(Course course, Ingredient ingredient) {
        courseIngredientDao.delCourseIngredient(course, ingredient);
    }

    @Override
    public void delCourseIngredient(int courseId, int ingredientId) {
        courseIngredientDao.delCourseIngredient(courseId, ingredientId);
    }
}