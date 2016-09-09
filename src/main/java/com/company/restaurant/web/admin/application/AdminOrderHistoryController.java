package com.company.restaurant.web.admin.application;

import com.company.restaurant.service.OrderService;
import com.company.restaurant.web.admin.application.common.AdminApplicationController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Yevhen on 04.09.2016.
 */
@Controller
public class AdminOrderHistoryController extends AdminApplicationController {
    private static final String ADMIN_ORDER_HISTORY_PAGE_VIEW_NAME = "admin-application/order-history/admin-order-history-page";
    private static final String ADMIN_ORDER_HISTORY_REQUEST_MAPPING_VALUE = "/admin-order-history";
    private static final String ORDERS_VAR_NAME = "orders";
    private static final String ORDER_VAR_NAME = "order";

    private OrderService orderService;

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping(value = ADMIN_ORDER_HISTORY_REQUEST_MAPPING_VALUE, method = RequestMethod.GET)
    public ModelAndView ordersPage() {
        modelAndView.addObject(ORDERS_VAR_NAME, orderService.findAllOrders());
        modelAndView.setViewName(ADMIN_ORDER_HISTORY_PAGE_VIEW_NAME);

        return modelAndView;
    }

}
