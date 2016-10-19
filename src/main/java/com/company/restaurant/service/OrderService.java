package com.company.restaurant.service;

import com.company.restaurant.model.Course;
import com.company.restaurant.model.Order;
import com.company.restaurant.model.State;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by Yevhen on 17.06.2016.
 */
public interface OrderService {
    State orderCreationState();

    Order addOrder(Order order);

    Order updOrder(Order order);

    void delOrder(Order order);

    void delAllOrders();

    Order findOrderById(int orderId);

    Order closeOrder(Order order);

    List<Order> findAllOrders();

    List<Order> findOrdersByFilter(Date orderDate, int waiterId, int tableId);

    String addCourseToOrder(Order order, Course course);

    Set<Date> getOrderDates();
}
