package com.company.restaurant.dao.hibernate;

import com.company.restaurant.dao.OrderDao;
import com.company.restaurant.dao.hibernate.common.HDaoEntityCourseCollecting;
import com.company.restaurant.model.Course;
import com.company.restaurant.model.Order;
import com.company.restaurant.model.State;
import com.company.util.sql.SqlExpressions;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yevhen on 13.06.2016.
 */
public class HOrderDao extends HDaoEntityCourseCollecting<Order> implements OrderDao {
    private static final String ORDER_ID_ATTRIBUTE_NAME = "orderId";
    private static final String STATE_ATTRIBUTE_NAME = "state";
    private static final String ORDER_NUMBER_ATTRIBUTE_NAME = "orderNumber";
    private static final String ORDER_DATETIME_ATTRIBUTE_NAME = "orderDatetime";

    @Override
    protected void initMetadata() {
        super.initMetadata();

        orderByExpression = String.format(SqlExpressions.SQL_ORDER_BY_ONE_FIELD_CONDITION_PATTERN_DESC,
                ORDER_DATETIME_ATTRIBUTE_NAME);

    }

    @Override
    public String orderEntityName() {
        return getTableName();
    }

    @Transactional
    @Override
    public Order addOrder(Order order) {
        return save(order);
    }

    @Transactional
    @Override
    public Order updOrder(Order order) {
        return saveOrUpdate(order);
    }

    @Transactional
    @Override
    public void delOrder(Order order) {
        delete(order);
    }

    @Transactional
    @Override
    public void delAllOrders() {
        deleteAllObjects();
    }

    @Transactional
    @Override
    public Order findOrderById(int orderId) {
        return findObjectById(orderId);
    }

    @Transactional
    @Override
    public List<Order> findOrderByNumber(String orderNumber) {
        return findObjectsByAttributeValue(ORDER_NUMBER_ATTRIBUTE_NAME, orderNumber);
    }

    @Transactional
    @Override
    public List<Order> findAllOrders() {
        return findAllObjects();
    }

    @Transactional
    @Override
    public List<Order> findAllOrders(String stateType) {
        State state = new State();
        state.setStateType(stateType);

        return findObjectsByAttributeValue(STATE_ATTRIBUTE_NAME, state);
    }

    @Transactional
    @Override
    public Order updOrderState(Order order, String stateType) {
        order.getState().setType(stateType);
        getCurrentSession().update(order);

        return order;
    }

    @Transactional
    @Override
    public void addCourseToOrder(Order order, Course course) {
        order.getCourses().add(course);
        update(order);
    }

    @Transactional
    @Override
    public void takeCourseFromOrder(Order order, Course course) {
        while (order.getCourses().remove(course));

        update(order);
    }

    @Transactional
    @Override
    public List<Course> findOrderCourses(Order order) {
        return new ArrayList<>(findCourses(order));
    }

    @Transactional
    @Override
    public Course findOrderCourseByCourseId(Order order, int courseId) {
        return findCourseByCourseId(order, courseId);
    }
}
