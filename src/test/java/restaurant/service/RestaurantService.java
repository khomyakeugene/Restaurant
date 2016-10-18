package restaurant.service;

import com.company.restaurant.service.CourseService;
import com.company.restaurant.service.OrderService;
import com.company.restaurant.service.WarehouseService;
import org.junit.BeforeClass;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import restaurant.dao.common.RestaurantDao;

/**
 * Created by Yevhen on 17.08.2016.
 */
public class RestaurantService extends RestaurantDao {
    private final static String SERVICE_CONTEXT_NAME = "restaurant-service-context.xml";

    protected static CourseService courseService;
    protected static WarehouseService warehouseService;
    protected static OrderService orderService;

    private static void initServices(String configLocation) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(configLocation);

        courseService = applicationContext.getBean(CourseService.class);
        warehouseService = applicationContext.getBean(WarehouseService.class);
        orderService = applicationContext.getBean(OrderService.class);
    }

    private static void initServiceContext() throws Exception {
        initServices(SERVICE_CONTEXT_NAME);
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        initDaoContext();
        initServiceContext();
    }
}
