package restaurant.service.common;

import com.company.restaurant.model.Portion;
import com.company.restaurant.service.*;
import org.junit.BeforeClass;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import restaurant.dao.common.RestaurantDao;

/**
 * Created by Yevhen on 17.08.2016.
 */
public class RestaurantService extends RestaurantDao {
    private final static String SERVICE_CONTEXT_NAME = "restaurant-service-context.xml";

    private static int KG_PORTION_ID = 1001;

    protected static EmployeeService employeeService;
    protected static CourseService courseService;
    protected static MenuService menuService;
    protected static WarehouseService warehouseService;
    protected static OrderService orderService;
    protected static TableService tableService;

    private Portion kgPortion;

    protected Portion getKgPortion() {
        if (kgPortion == null) {
            kgPortion = warehouseService.findPortionById(KG_PORTION_ID);
        }

        return kgPortion;
    }

    private static void initServices(String configLocation) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(configLocation);

        employeeService = applicationContext.getBean(EmployeeService.class);
        menuService = applicationContext.getBean(MenuService.class);
        courseService = applicationContext.getBean(CourseService.class);
        warehouseService = applicationContext.getBean(WarehouseService.class);
        orderService = applicationContext.getBean(OrderService.class);
        tableService = applicationContext.getBean(TableService.class);
    }

    private static void initServiceContext() throws Exception {
        initServices(SERVICE_CONTEXT_NAME);
    }

    protected static void initContext() throws Exception  {
        initDaoContext();
        initServiceContext();
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        initContext();
    }
}
