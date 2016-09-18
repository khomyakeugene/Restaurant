package com.company.restaurant.web.admin.application;

import com.company.restaurant.model.Employee;
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
    private static final String ORDER_WAITERS_VAR_NAME = "orderWaiters";
    private static final String ORDER_TABLES_VAR_NAME = "orderTables";
    private static final String ORDER_DATES_VAR_NAME = "orderDates";
    private static final String ORDER_DATE_PRESENTATION_VAR_NAME = "orderDatePresentation";
    private static final String ORDER_WAITER_NAME_PRESENTATION_VAR_NAME = "orderWaiterNamePresentation";
    private static final String ORDER_TABLE_NUMBER_PRESENTATION_VAR_NAME = "orderTableNumberPresentation";

    private static final String ORDER_DATE_PAR_NAME = "orderDate";
    private static final String ORDER_WAITER_ID_PAR_NAME = "waiterId";
    private static final String ORDER_TABLE_ID_PAR_NAME = "tableId";

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

        orderService.getOrderDates().forEach(date -> dateList.add(DateToStringPresentation(date)));
        modelAndView.addObject(ORDER_DATES_VAR_NAME, dateList);
    }

    private void initOrderWaiterList() {
        TreeMap<Integer, String> waiters = new TreeMap<>();
        orderService.findAllOrders().forEach(order -> waiters.
                put(order.getWaiter().getEmployeeId(), order.getWaiter().getName()));

        modelAndView.addObject(ORDER_WAITERS_VAR_NAME, waiters);
    }

    private String getTableDescription(Table table) {
        String tableName = String.valueOf(table.getNumber());
        String description = table.getDescription();
        if (description != null && !description.isEmpty()) {
            tableName = String.format("%s (%s)", tableName, description);
        }

        return tableName;
    }

    private void initOrderTableList() {
        TreeMap<Integer, String> orderTables = new TreeMap<>();
        orderService.findAllOrders().forEach(order -> {
            Table table = order.getTable();
            orderTables.put(table.getTableId(), getTableDescription(table));
        });

        modelAndView.addObject(ORDER_TABLES_VAR_NAME, orderTables);
    }

    private String buildPresentationImage(String value) {
        if (value != null) {
            value = value.trim();
        }
        return (value == null || value.isEmpty()) ? value : String.format("(%s)", value);
    }

    private void addOrderDatePresentation(String value) {
        modelAndView.addObject(ORDER_DATE_PRESENTATION_VAR_NAME, buildPresentationImage(value));
    }

    private void addWaiterPresentation(Employee waiter) {
        modelAndView.addObject(ORDER_WAITER_NAME_PRESENTATION_VAR_NAME, (waiter == null) ? "" :
                buildPresentationImage(waiter.getName()));
    }

    private void addTablePresentation(Table table) {
        modelAndView.addObject(ORDER_TABLE_NUMBER_PRESENTATION_VAR_NAME, (table == null) ? "" :
                buildPresentationImage(getTableDescription(table)));
    }

    private void initFilterData() {
        addOrderDatePresentation(null);
        addWaiterPresentation(null);
        addTablePresentation(null);
    }


    @RequestMapping(value = ADMIN_ORDER_HISTORY_REQUEST_MAPPING_VALUE, method = RequestMethod.GET)
    public ModelAndView ordersPage() {
        clearErrorMessage();
        setCurrentObject(new Order());

        initOrderList();
        initOrderDatesList();
        initOrderWaiterList();
        initOrderTableList();
        initFilterData();

        modelAndView.setViewName(ADMIN_ORDER_HISTORY_PAGE_VIEW_NAME);

        return modelAndView;
    }

    @RequestMapping(value = ADMIN_SEARCH_ORDERS_REQUEST_MAPPING_VALUE, method = RequestMethod.POST)
    public ModelAndView searchOrders(@RequestParam(ORDER_DATE_PAR_NAME) String orderDateString,
                                     @RequestParam(ORDER_WAITER_ID_PAR_NAME) int waiterId,
                                     @RequestParam(ORDER_TABLE_ID_PAR_NAME) int tableId) {
        clearErrorMessage();
        setCurrentObject(new Order());

        // Filter the data
        modelAndView.addObject(ORDERS_VAR_NAME, orderService.findOrdersByFilter(
                parseDateFromStringPresentation(orderDateString), waiterId, tableId));

        // Add variables to show filter conditions on the view (jsp-page)
        addOrderDatePresentation(orderDateString);
        addWaiterPresentation(employeeService.findEmployeeById(waiterId));
        addTablePresentation(tableService.findTableById(tableId));

        // Return to the current page (order history page)
        return modelAndView;
    }

    @RequestMapping(value = ADMIN_APPLICATION_COURSE_REQUEST_MAPPING_VALUE, method = RequestMethod.GET)
    public ModelAndView course(@PathVariable int orderId) {
        clearErrorMessage();
        setCurrentObject(orderService.findOrderById(orderId));

        // Return to the current page (order history page)
        return modelAndView;
    }
}
