package com.company.restaurant.service;

import com.company.restaurant.model.Course;
import com.company.restaurant.model.Order;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by Yevhen on 17.06.2016.
 */
public interface OrderService {
    Order addOrder(Order order);

    void delOrder(Order order);

    void delAllOrders();

    Order findOrderById(int orderId);

    Order closeOrder(Order order);

    List<Order> findAllOrders();

    List<Order> findAllOrders(String stateType);

    List<Order> findAllOpenOrders();

    List<Order> findAllClosedOrders();

    List<Order> findOrderByNumber(String orderNumber);

    List<Order> findOrdersByFilter(Date orderDate, int waiterId, int tableId);

    String addCourseToOrder(Order order, Course course);

    String takeCourseFromOrder(Order order, Course course);

    List<Course> findOrderCourses(Order order);

    Course findOrderCourseByCourseId(Order order, int courseId);

    Order updOrderState(Order order, String stateType);

    Set<Date> getOrderDates();
}
