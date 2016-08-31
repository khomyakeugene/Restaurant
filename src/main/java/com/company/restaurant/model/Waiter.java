package com.company.restaurant.model;

import java.util.Set;

/**
 * Created by Yevhen on 30.06.2016.
 */
public class Waiter extends Employee {
    private WaiterProperty waiterProperty = new WaiterProperty();

    public WaiterProperty getWaiterProperty() {
        return waiterProperty;
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
        if (!(o instanceof Waiter)) return false;
        if (!super.equals(o)) return false;

        Waiter waiter = (Waiter) o;

        return waiterProperty != null ? waiterProperty.equals(waiter.waiterProperty) : waiter.waiterProperty == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (waiterProperty != null ? waiterProperty.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Waiter{" +
                super.toString() + "\n" +
                "waiterProperty=" + waiterProperty +
                '}';
    }
}
