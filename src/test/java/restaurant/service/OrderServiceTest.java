package restaurant.service;

import com.company.restaurant.model.Course;
import com.company.restaurant.model.Order;
import com.company.util.exception.DataIntegrityException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import restaurant.service.common.RestaurantDataGenerator;
import restaurant.service.common.RestaurantService;
import restaurant.util.Util;

import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Created by Yevhen on 19.10.2016.
 */
public class OrderServiceTest extends RestaurantService {
    private static Order closedOrder;

    private static void prepareClosedOrder() throws Exception {
        Order order = new Order();
        order.setOrderNumber(Integer.toString(Util.getRandomInteger()));
        order.setWaiter(RestaurantDataGenerator.getRandomEmployee());
        order.setTable(RestaurantDataGenerator.getRandomTable());
        order = orderService.addOrder(order);

        orderService.addCourseToOrder(order, RestaurantDataGenerator.getRandomCourse());
        orderService.addCourseToOrder(order, RestaurantDataGenerator.getRandomCourse());
        closedOrder = orderService.closeOrder(order);
    }

    private static void clearClosedOrder() throws Exception {
        orderDao.delOrder(closedOrder);
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        initContext();
        prepareClosedOrder();
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        clearClosedOrder();
    }

    @Test(timeout = 2000)
    public void addUpdFindDelOrderTest() throws Exception {
        List<Order> orders = orderService.findAllOrders();
        int orderCount = orders.size();

        Order order = new Order();
        order.setOrderNumber(Integer.toString(Util.getRandomInteger()));
        order.setWaiter(RestaurantDataGenerator.getRandomEmployee());
        order.setTable(RestaurantDataGenerator.getRandomTable());
        order = orderService.addOrder(order);
        assertTrue(orderService.findAllOrders().size() == (orderCount + 1));

        String newOrderNumber = Integer.toString(Util.getRandomInteger());
        order.setOrderNumber(newOrderNumber);
        orderService.updOrder(order);
        assertTrue(orderService.findOrderById(order.getOrderId()).getOrderNumber().equals(newOrderNumber));

        Course course = RestaurantDataGenerator.getRandomCourse();
        orderService.addCourseToOrder(order, course);
        assertTrue(order.getCourses().size() == 1);

        orderDao.delOrder(order);
        assertTrue(orderService.findAllOrders().size() == orderCount);
    }

    @Test(timeout = 2000, expected = DataIntegrityException.class)
    public void delClosedOrderTest() throws Exception {
        orderService.delOrder(closedOrder);
    }

    @Test(expected = DataIntegrityException.class)
    public void changeClosedOrderTest() throws Exception {
        orderService.addCourseToOrder(closedOrder, RestaurantDataGenerator.getRandomCourse());
    }
}