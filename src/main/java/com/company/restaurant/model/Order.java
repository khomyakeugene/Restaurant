package com.company.restaurant.model;

import com.company.restaurant.model.common.CourseCollecting;
import com.company.restaurant.model.common.SimpleObject;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yevhen on 12.06.2016.
 */
public class Order extends SimpleObject implements CourseCollecting {
    private String orderNumber;
    private Timestamp orderDatetime;

    private State state = new State();
    private Employee waiter = new Employee();
    private Table table = new Table();
    private List<Course> courses = new ArrayList<>();

    public int getOrderId() {
        return getId();
    }

    public void setOrderId(int orderId) {
        setId(orderId);
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Timestamp getOrderDatetime() {
        return orderDatetime;
    }

    public void setOrderDatetime(Timestamp orderDatetime) {
        this.orderDatetime = orderDatetime;
    }

    public Employee getWaiter() {
        return waiter;
    }

    public void setWaiter(Employee waiter) {
        this.waiter = waiter;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        if (!super.equals(o)) return false;

        Order order = (Order) o;

        return orderNumber != null ? orderNumber.equals(order.orderNumber) :
                order.orderNumber == null && (orderDatetime != null ? orderDatetime.equals(order.orderDatetime) :
                        order.orderDatetime == null && (state != null ? state.equals(order.state) :
                                order.state == null && (waiter != null ? waiter.equals(order.waiter) :
                                        order.waiter == null && (table != null ? table.equals(order.table) :
                                                order.table == null && (courses != null ?
                                                        courses.equals(order.courses) : order.courses == null)))));

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (orderNumber != null ? orderNumber.hashCode() : 0);
        result = 31 * result + (orderDatetime != null ? orderDatetime.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (waiter != null ? waiter.hashCode() : 0);
        result = 31 * result + (table != null ? table.hashCode() : 0);
        result = 31 * result + (courses != null ? courses.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + getOrderId() +
                ", orderNumber='" + orderNumber + '\'' +
                ", orderDatetime=" + orderDatetime +
                ", state=" + state +
                ", courses=" + courses +
                ", table=" + table +
                '}';
    }
}
