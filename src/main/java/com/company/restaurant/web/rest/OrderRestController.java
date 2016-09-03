package com.company.restaurant.web.rest;

import com.company.restaurant.model.Order;
import com.company.restaurant.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Yevhen on 18.08.2016.
 */

@RestController
public class OrderRestController {
    private OrderService orderService;

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    @ResponseBody
    public List<Order> getOrderList() {
        return orderService.findAllOrders();
    }

    @RequestMapping(value = "/open_orders", method = RequestMethod.GET)
    @ResponseBody
    public List<Order> getOpenOrderList() {
        return orderService.findAllOpenOrders();
    }

    @RequestMapping(value = "/closed_orders", method = RequestMethod.GET)
    @ResponseBody
    public List<Order> getClosedOrderList() {
        return orderService.findAllClosedOrders();
    }

    @RequestMapping(value = "/orders/order_id/{orderId}", method = RequestMethod.GET)
    @ResponseBody
    public Order getOrderById(@PathVariable int orderId) {
        return orderService.findOrderById(orderId);
    }
}

