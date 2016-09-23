package com.company.restaurant.service.impl;

import com.company.restaurant.dao.OrderDao;
import com.company.restaurant.model.Course;
import com.company.restaurant.model.Order;
import com.company.restaurant.service.OrderService;
import com.company.restaurant.service.impl.common.ObjectService;
import com.company.util.common.DatetimeFormatter;
import com.company.util.exception.DataIntegrityException;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * Created by Yevhen on 22.05.2016.
 */
public class OrderServiceImpl extends ObjectService<Order> implements OrderService {
    private static final String IMPOSSIBLE_TO_DELETE_ORDER_PATTERN =
            "It is impossible to delete order in <%s> state (<order_id> = %d)!";
    private static final String IMPOSSIBLE_TO_ADD_COURSE_TO_ORDER_PATTERN =
            "It is impossible to add course to order in <%s> state (<order_id> = %d)!";
    private static final String IMPOSSIBLE_TO_DEL_COURSE_FROM_ORDER_PATTERN =
            "It is impossible to delete course from order in <%s> state (<order_id> = %d)!";

    private OrderDao orderDao;
    private StateGraphRules stateGraphRules;

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public void setStateGraphRules(StateGraphRules stateGraphRules) {
        this.stateGraphRules = stateGraphRules;
    }

    private String orderCreationState() {
        return stateGraphRules.creationState(orderDao.orderEntityName());
    }

    private String orderClosedState(Order order) {
        return stateGraphRules.closedState(orderDao.orderEntityName(), (order == null) ? null :
                order.getState().getType());
    }

    private String orderClosedState() {
        return orderClosedState(null);
    }

    private String orderDeletedState(Order order) {
        return stateGraphRules.deletedState(orderDao.orderEntityName(), order.getState().getType());
    }

    private boolean isFillingActionEnabled(Order order) {
        return stateGraphRules.isFillingActionEnabled(orderDao.orderEntityName(), order.getState().getType());
    }

    @Override
    public Order addOrder(Order order) {
        order.getState().setType(orderCreationState());

        return orderDao.addOrder(order);
    }

    @Override
    public void delOrder(Order order) {
        if (orderDeletedState(order) != null) {
            orderDao.delOrder(order);
        } else {
            throw new DataIntegrityException(String.format(
                    IMPOSSIBLE_TO_DELETE_ORDER_PATTERN, order, order.getOrderId()));
        }
    }

    @Override
    public void delAllOrders() {
        orderDao.delAllOrders();
    }

    @Override
    public Order findOrderById(int id) {
        return orderDao.findOrderById(id);
    }

    @Override
    public Order closeOrder(Order order) {
        return orderDao.updOrderState(order, orderClosedState(order));
    }

    @Override
    public List<Order> findAllOrders() {
        return orderDao.findAllOrders();
    }

    @Override
    public List<Order> findAllOrders(String stateType) {
        return orderDao.findAllOrders(stateType);
    }

    @Override
    public List<Order> findAllOpenOrders() {
        return findAllOrders(orderCreationState());
    }

    @Override
    public List<Order> findAllClosedOrders() {
        return findAllOrders(orderClosedState());
    }

    @Override
    public String addCourseToOrder(Order order, Course course) {
        String result = null;

        try {
            if (isFillingActionEnabled(order)) {
                orderDao.addCourseToOrder(order, course);
            } else {
                // Perhaps, to raise exception seems to be unnecessary and excessive, but let use such a "mechanism"!
                throwDataIntegrityException(String.format(
                        IMPOSSIBLE_TO_ADD_COURSE_TO_ORDER_PATTERN, order.getState().getName(), order.getOrderId()));
            }
        } catch (Exception e) {
            result = e.getMessage();
        }

        return result;
    }

    @Override
    public String takeCourseFromOrder(Order order, Course course) {
        String result = null;

        try {
            if (isFillingActionEnabled(order)) {
                orderDao.takeCourseFromOrder(order, course);
            } else {
                throwDataIntegrityException(String.format(
                        IMPOSSIBLE_TO_DEL_COURSE_FROM_ORDER_PATTERN, order.getState().getName(), order.getOrderId()));
            }
        } catch (Exception e) {
            result = e.getMessage();
        }

        return result;
    }

    @Override
    public List<Course> findOrderCourses(Order order) {
        return orderDao.findOrderCourses(order);
    }

    @Override
    public List<Order> findOrderByNumber(String orderNumber) {
        return orderDao.findOrderByNumber(orderNumber);
    }

    @Override
    public List<Order> findOrdersByFilter(Date orderDate, int waiterId, int tableId) {
        return findAllOrders().stream().filter(order ->
                (((orderDate == null) || (DatetimeFormatter.getDateOnly(order.getOrderDatetime()).equals(orderDate))) &&
                        ((waiterId <= 0) || (order.getWaiter().getEmployeeId() == waiterId)) &&
                        ((tableId <= 0) || (order.getTable().getTableId() == tableId)))).
                collect(Collectors.toList());
    }

    @Override
    public Course findOrderCourseByCourseId(Order order, int courseId) {
        return orderDao.findOrderCourseByCourseId(order, courseId);
    }

    @Override
    public Order updOrderState(Order order, String stateType) {
        return orderDao.updOrderState(order, stateType);
    }

    @Override
    public Set<Date> getOrderDates() {
        TreeSet<Date> result = new TreeSet<>();
        orderDao.findAllOrders().forEach(o -> result.add(DatetimeFormatter.getDateOnly(o.getOrderDatetime())));

        return result;
    }
}
