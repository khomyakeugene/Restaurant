package restaurant.dao;

import com.company.restaurant.model.Order;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

/**
 * Created by Yevhen on 18.08.2016.
 */
public class RestaurantPrepareTestOrderData extends RestaurantService {
    private final static int MAX_COURSER_COUNT = 5;
    private final static int ORDER_COUNT = 10;

    static private Random random = new Random();

    @Transactional
    private void prepareOrder(Integer orderNumber) {
        Order order = new Order();
        order.setOrderNumber(orderNumber.toString());
        order.setWaiter(RestaurantDataGenerator.getRandomEmployee());
        order.setTable(RestaurantDataGenerator.getRandomTable());
        order.setState(stateDao.findStateByType("A"));
        order = orderDao.addOrder(order);

        int courseCount = random.nextInt(MAX_COURSER_COUNT) + 1;
        for (int i = 0; i < courseCount; i++) {
            orderDao.addCourseToOrder(order, RestaurantDataGenerator.getRandomCourse());
        }

        if (random.nextBoolean()) {
            orderService.closeOrder(order);
        }
    }

    @Test
    public void prepareOrders() {
        orderService.delAllOrders();

        for (int i = 0; i < ORDER_COUNT; i++) {
            prepareOrder(i + 1);
        }
    }
}
