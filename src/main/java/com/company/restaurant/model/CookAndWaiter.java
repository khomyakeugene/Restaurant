package com.company.restaurant.model;

import java.util.Set;

/**
 * Created by Yevhen on 03.07.2016.
 */
public class CookAndWaiter extends Employee {
    private CookProperty cookProperty = new CookProperty();
    private WaiterProperty waiterProperty = new WaiterProperty();

    public CookProperty getCookProperty() {
        return cookProperty;
    }

    public WaiterProperty getWaiterProperty() {
        return waiterProperty;
    }

    public Set<CookedCourse> getCookedCourses() {
        return cookProperty.getCookedCourses();
    }

    public void setCookedCourses(Set<CookedCourse> cookedCourses) {
        cookProperty.setCookedCourses(cookedCourses);
    }

    public Set<Order> getOrders() {
        return waiterProperty.getOrders();
    }

    public void setOrders(Set<Order> orders) {
        waiterProperty.setOrders(orders);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CookAndWaiter)) return false;
        if (!super.equals(o)) return false;

        CookAndWaiter that = (CookAndWaiter) o;

        return cookProperty != null ? cookProperty.equals(that.cookProperty) :
                that.cookProperty == null && (waiterProperty != null ?
                        waiterProperty.equals(that.waiterProperty) : that.waiterProperty == null);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (cookProperty != null ? cookProperty.hashCode() : 0);
        result = 31 * result + (waiterProperty != null ? waiterProperty.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CookAndWaiter{" +
                super.toString() + "\n" +
                "cookProperty=" + cookProperty +
                ", waiterProperty=" + waiterProperty +
                '}';
    }
}
