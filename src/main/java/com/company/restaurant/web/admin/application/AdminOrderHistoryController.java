package com.company.restaurant.web.admin.application;

import com.company.restaurant.model.Order;
import com.company.restaurant.model.Table;
import com.company.restaurant.service.OrderService;
import com.company.restaurant.web.admin.application.common.AdminCRUDController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.TreeMap;

/**
 * Created by Yevhen on 04.09.2016.
 */
@Controller
public class AdminOrderHistoryController extends AdminCRUDController<Order> {
    private static final String ADMIN_ORDER_HISTORY_REQUEST_MAPPING_VALUE = "/admin-order-history";
    private static final String ADMIN_APPLICATION_COURSE_REQUEST_MAPPING_VALUE = "/admin-order/{orderId}";
    private static final String ADMIN_SEARCH_ORDERS_REQUEST_MAPPING_VALUE = "/admin-search-orders";
    private static final String ADMIN_ORDER_HISTORY_PAGE_VIEW_NAME = "admin-application/order-history/admin-order-history-page";

    private static final String ORDERS_VAR_NAME = "orders";
    private static final String ORDER_VAR_NAME = "order";
    private static final String ORDER_DATE_VAR_NAME = "orderDate";
    private static final String ORDER_WAITERS_VAR_NAME = "orderWaiters";
    private static final String ORDER_TABLES_VAR_NAME = "orderTables";
    private static final String ORDER_DATES_VAR_NAME = "orderDates";
    private static final String ORDER_WAITER_ID_VAR_NAME = "waiterId";
    private static final String ORDER_TABLE_ID_VAR_NAME = "tableId";

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
            orderTables.put(table.getTableId(), tableName);
        });

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

    @RequestMapping(value = ADMIN_SEARCH_ORDERS_REQUEST_MAPPING_VALUE, method = RequestMethod.POST)
    public ModelAndView searchOrders(@RequestParam(ORDER_DATE_VAR_NAME) String orderDateString,
                                     @RequestParam(ORDER_WAITER_ID_VAR_NAME) int waiterId,
                                     @RequestParam(ORDER_TABLE_ID_VAR_NAME) int tableId) {
        clearErrorMessage();
        // clearCurrentObject();

        // Filter the data
        modelAndView.addObject(ORDERS_VAR_NAME, orderService.findOrdersByFilter(
                parseDateFromDefaultStringPresentation(orderDateString), waiterId, tableId));

        // Return to the current page (order history page)
        return modelAndView;
    }

    @RequestMapping(value = ADMIN_APPLICATION_COURSE_REQUEST_MAPPING_VALUE, method = RequestMethod.GET)
    public ModelAndView course(@PathVariable int orderId) {
        clearErrorMessage();

        Order order = orderService.findOrderById(orderId);
        setCurrentObject(order);
        modelAndView.addObject(COURSES_VAR_NAME, order.getCourses());

        // Return to the current page (order history page)
        return modelAndView;
    }
}
