package com.company.restaurant.model;

import java.util.HashSet;
import java.util.Set;

public class WaiterProperty {
    private Set<Order> orders = new HashSet<>();

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WaiterProperty)) return false;

        WaiterProperty that = (WaiterProperty) o;

        return (orders == null && that.orders == null) ||
                (orders != null) && (that.orders != null) &&
                        (orders.size() == that.orders.size()) &&
                        !orders.stream().filter(order -> !that.orders.stream().
                                filter(thatOrder -> (thatOrder.getOrderId() == order.getOrderId())).
                                findAny().isPresent()).findAny().isPresent();
    }

    @Override
    public String toString() {
        return "WaiterProperty{orders=" + orders + "}";
    }
}