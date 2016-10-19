package com.company.restaurant.service.impl;

import com.company.restaurant.dao.OrderDao;
import com.company.restaurant.dao.StateDao;
import com.company.restaurant.model.Course;
import com.company.restaurant.model.Order;
import com.company.restaurant.model.State;
import com.company.restaurant.service.OrderService;
import com.company.restaurant.service.impl.common.Service;
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
public class OrderServiceImpl extends Service implements OrderService {
    private static final String IMPOSSIBLE_TO_DELETE_ORDER_PATTERN =
            "It is impossible to delete order in <%s> state (<order_id> = %d)!";
    private static final String IMPOSSIBLE_TO_ADD_COURSE_TO_ORDER_PATTERN =
            "It is impossible to add course to order in <%s> state (<order_id> = %d)!";
    private static final String IMPOSSIBLE_TO_DEL_COURSE_FROM_ORDER_PATTERN =
            "It is impossible to delete course from order in <%s> state (<order_id> = %d)!";

    private OrderDao orderDao;
    private StateDao stateDao;
    private StateGraphRules stateGraphRules;

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public void setStateDao(StateDao stateDao) {
        this.stateDao = stateDao;
    }

    public void setStateGraphRules(StateGraphRules stateGraphRules) {
        this.stateGraphRules = stateGraphRules;
    }

    private String orderCreationStateType() {
        return stateGraphRules.creationState(orderDao.orderEntityName());
    }

    private String orderClosedStateType(Order order) {
        return stateGraphRules.closedState(orderDao.orderEntityName(), (order == null) ? null :
                order.getState().getType());
    }

    private String orderClosedStateType() {
        return orderClosedStateType(null);
    }

    private State orderClosedState(Order order) {
        return stateDao.findStateByType(orderClosedStateType(order));
    }

    private String orderDeletedState(Order order) {
        return stateGraphRules.deletedState(orderDao.orderEntityName(), order.getState().getType());
    }

    private boolean isFillingActionEnabled(Order order) {
        return stateGraphRules.isFillingActionEnabled(orderDao.orderEntityName(), order.getState().getType());
    }

    private State orderCreationState() {
        return stateDao.findStateByType(orderCreationStateType());
    }

    @Override
    public Order addOrder(Order order) {
        order.setState(orderCreationState());

        return orderDao.addOrder(order);
    }

    @Override
    public Order updOrder(Order order) {
        return orderDao.updOrder(order);
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
        order.setState(orderClosedState(order));

        return updOrder(order);
    }

    @Override
    public List<Order> findAllOrders() {
        return orderDao.findAllOrders();
    }

    @Override
    public void addCourseToOrder(Order order, Course course) {
        if (isFillingActionEnabled(order)) {
            orderDao.addCourseToOrder(order, course);
        } else {
            // Perhaps, to raise exception seems to be unnecessary and excessive, but let use such a "mechanism"!
            throwDataIntegrityException(String.format(
                    IMPOSSIBLE_TO_ADD_COURSE_TO_ORDER_PATTERN, order.getState().getName(), order.getOrderId()));
        }
    }

    @Override
    public void delCourseFromOrder(Order order, Course course) {
        if (isFillingActionEnabled(order)) {
            orderDao.delCourseFromOrder(order, course);
        } else {
            throwDataIntegrityException(String.format(
                    IMPOSSIBLE_TO_DEL_COURSE_FROM_ORDER_PATTERN, order.getState().getName(), order.getOrderId()));
        }
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
    public Set<Date> getOrderDates() {
        TreeSet<Date> result = new TreeSet<>();
        orderDao.findAllOrders().forEach(o -> result.add(DatetimeFormatter.getDateOnly(o.getOrderDatetime())));

        return result;
    }
}
