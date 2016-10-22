package restaurant.dao.common;

import com.company.restaurant.dao.*;
import org.junit.BeforeClass;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Yevhen on 16.08.2016.
 */
public class RestaurantDao {
    private final static String DAO_CONTEXT_NAME = "restaurant-hibernate-context.xml";

    protected static JobPositionDao jobPositionDao;
    protected static EmployeeDao employeeDao;
    protected static MenuDao menuDao;
    protected static TableDao tableDao;
    protected static CourseDao courseDao;
    protected static CourseCategoryDao courseCategoryDao;
    protected static CookedCourseDao cookedCourseDao;
    protected static StateDao stateDao;
    protected static StateGraphDao stateGraphDao;
    protected static OrderDao orderDao;
    protected static IngredientDao ingredientDao;
    protected static PortionDao portionDao;
    protected static WarehouseDao warehouseDao;
    protected static CommonDataDao commonDataDao;

    private static void initDataSource(String configLocation) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(configLocation);

        menuDao = applicationContext.getBean(MenuDao.class);
        tableDao = applicationContext.getBean(TableDao.class);
        employeeDao = applicationContext.getBean(EmployeeDao.class);
        jobPositionDao = applicationContext.getBean(JobPositionDao.class);
        courseDao = applicationContext.getBean(CourseDao.class);
        courseCategoryDao = applicationContext.getBean(CourseCategoryDao.class);
        cookedCourseDao = applicationContext.getBean(CookedCourseDao.class);
        stateDao = applicationContext.getBean(StateDao.class);
        stateGraphDao = applicationContext.getBean(StateGraphDao.class);
        orderDao = applicationContext.getBean(OrderDao.class);
        ingredientDao = applicationContext.getBean(IngredientDao.class);
        portionDao = applicationContext.getBean(PortionDao.class);
        warehouseDao = applicationContext.getBean(WarehouseDao.class);
        commonDataDao = applicationContext.getBean(CommonDataDao.class);
    }

    protected static void initDaoContext() {
        initDataSource(DAO_CONTEXT_NAME);
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        initDaoContext();
    }
}
