package restaurant.data;

import com.company.restaurant.model.Order;
import com.company.restaurant.model.State;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;
import restaurant.service.common.RestaurantDataGenerator;
import restaurant.service.common.RestaurantService;

import java.util.Random;

import static restaurant.util.Util.addDays;
import static restaurant.util.Util.getCurrentTimestamp;

/**
 * Created by Yevhen on 18.08.2016.
 */
public class RestaurantPrepareTestOrderData extends RestaurantService {
    private final static int MAX_COURSER_COUNT = 5;
    private final static int ORDER_COUNT = 30;
    private final static int MAX_DAY_DELTA = 10;

    static private Random random = new Random();

    @Transactional
    private void prepareOrder(Integer orderNumber) {
        State orderCreationState = orderService.orderCreationState();

        Order order = new Order();
        order.setOrderNumber(orderNumber.toString());
        order.setWaiter(RestaurantDataGenerator.getRandomEmployee());
        order.setTable(RestaurantDataGenerator.getRandomTable());
        order.setState(orderCreationState);
        order = orderService.addOrder(order);

        // Only through update it is possible to change "default-current" field value
        order.setOrderDatetime(addDays(getCurrentTimestamp(), random.nextInt(MAX_DAY_DELTA)));
        order = orderService.updOrder(order);

        int courseCount = random.nextInt(MAX_COURSER_COUNT) + 1;
        for (int i = 0; i < courseCount; i++) {
            orderService.addCourseToOrder(order, RestaurantDataGenerator.getRandomCourse());
        }

        if (random.nextBoolean()) {
            orderService.closeOrder(order);
        }
    }

    @Test
    public void prepareOrders() {
        System.out.println(getClass().getName() + ".prepareOrders ...");

        orderService.delAllOrders();

        for (int i = 0; i < ORDER_COUNT; i++) {
            prepareOrder(i + 1);
        }

        orderService.getOrderDates().forEach(System.out::println);
    }
}
