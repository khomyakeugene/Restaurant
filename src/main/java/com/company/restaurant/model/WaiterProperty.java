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
    public String toString() {
        return "WaiterProperty{" +
                "orders=" + orders +
                '}';
    }
}