package com.company.restaurant.dao;

import com.company.restaurant.model.Course;
import com.company.restaurant.model.Order;

import java.util.List;

/**
 * Created by Yevhen on 13.06.2016.
 */
public interface OrderCourseDao {
    <T extends Order> void addCourseToOrder(T order, Course course);

    <T extends Order> void takeCourseFromOrder(T order, Course course);

    List<Course> findOrderCourses(Order order);

    Course findOrderCourseByCourseId(Order order, int courseId);
}
