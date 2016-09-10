package com.company.restaurant.web.admin.application;

import com.company.restaurant.model.Table;
import com.company.restaurant.service.OrderService;
import com.company.restaurant.web.admin.application.common.AdminApplicationController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.TreeMap;

/**
 * Created by Yevhen on 04.09.2016.
 */
@Controller
public class AdminOrderHistoryController extends AdminApplicationController {
    private static final String ADMIN_ORDER_HISTORY_PAGE_VIEW_NAME = "admin-application/order-history/admin-order-history-page";
    private static final String ADMIN_ORDER_HISTORY_REQUEST_MAPPING_VALUE = "/admin-order-history";
    private static final String ORDERS_VAR_NAME = "orders";
    private static final String ORDER_VAR_NAME = "order";
    private static final String ORDER_DATES_VAR_NAME = "orderDates";
    private static final String ORDER_WAITERS_VAR_NAME = "orderWaiters";
    private static final String ORDER_TABLES_VAR_NAME = "orderTables";

    private OrderService orderService;

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    private void initOrderList() {
        modelAndView.addObject(ORDERS_VAR_NAME, orderService.findAllOrders());
    }

    private void initOrderDatesList() {
        ArrayList<String> dateList = new ArrayList<>();

        orderService.getOrderDates().forEach(date -> dateList.add(DateToDefaultStringPresentation(date)));
        modelAndView.addObject(ORDER_DATES_VAR_NAME, dateList);
    }

    private void initOrderWaiterList() {
        TreeMap<Integer, String> waiters = new TreeMap<>();
        orderService.findAllOrders().forEach(order -> waiters.
                put(order.getWaiter().getEmployeeId(), order.getWaiter().getName()));

        modelAndView.addObject(ORDER_WAITERS_VAR_NAME, waiters);
    }

    private void initOrderTableList() {
        TreeMap<Integer, String> orderTables = new TreeMap<>();
        orderService.findAllOrders().forEach(order -> {
            Table table = order.getTable();
            String tableName = String.valueOf(table.getNumber());
            String description = table.getDescription();
            if (description != null && !description.isEmpty()) {
                tableName = String.format("%s (%s)", tableName, description);
            }
            orderTables.put(table.getTableId(), tableName);});

        modelAndView.addObject(ORDER_TABLES_VAR_NAME, orderTables);
    }

    @RequestMapping(value = ADMIN_ORDER_HISTORY_REQUEST_MAPPING_VALUE, method = RequestMethod.GET)
    public ModelAndView ordersPage() {
        clearErrorMessage();

        initOrderList();
        initOrderDatesList();
        initOrderWaiterList();
        initOrderTableList();

        modelAndView.setViewName(ADMIN_ORDER_HISTORY_PAGE_VIEW_NAME);

        return modelAndView;
    }

}
