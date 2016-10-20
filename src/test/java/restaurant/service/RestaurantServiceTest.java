package restaurant.service;

import com.company.restaurant.model.*;
import com.company.util.exception.DataIntegrityException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;
import restaurant.service.common.RestaurantDataGenerator;
import restaurant.service.common.RestaurantService;
import restaurant.util.Util;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertTrue;

/**
 * Created by Yevhen on 18.10.2016.
 */
public class RestaurantServiceTest extends RestaurantService {
    private static final Float TEST_AMOUNT = 1.0f;

    private List<Ingredient> allIngredients;
    private static Order closedOrder;

    private Ingredient getFirstNewIngredient(Course course) {
        if (allIngredients == null) {
            allIngredients = warehouseService.findAllIngredients();
        }

        Optional<Ingredient> ingredientOptional = allIngredients.stream().filter(ingredient -> !course.getCourseIngredients().stream().
                filter(courseIngredient -> courseIngredient.getIngredient().equals(ingredient)).
                findAny().isPresent()).findFirst();
        return ingredientOptional.isPresent() ? ingredientOptional.get() : null;
    }

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

    private static void initEnvironment() throws Exception {
        prepareClosedOrder();
    }

    private static void tearDownEnvironment() throws Exception {
        clearClosedOrder();
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        initContext();
        initEnvironment();
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        tearDownEnvironment();
    }

    @Test(timeout = 2000)
    public void courseCategoryFindTest() throws Exception {
        List<CourseCategory> courseCategories = courseService.findAllCourseCategories();
        assertTrue(courseCategories.size() > 0);

        for (CourseCategory courseCategory : courseCategories) {
            assertTrue(courseService.findCourseCategoryByName(courseCategory.getName()).equals(courseCategory));
            assertTrue(courseService.findCourseCategoryById(courseCategory.getId()).equals(courseCategory));
        }
    }

    @Test(timeout = 2000)
    @Transactional
    public void addUpdDelFindCoursesTest() throws Exception {
        Portion kgPortion = getKgPortion();
        List<Ingredient> ingredients = warehouseService.findAllIngredients();

        List<Course> courses = courseService.findAllCourses();
        int coursesCount = courses.size();
        assertTrue(coursesCount > 0);

        for (Course course : courses) {
            assertTrue(courseService.findCourseById(course.getCourseId()).equals(course));

            // Add / del course ingredient
            Ingredient newIngredient = getFirstNewIngredient(course);
            if (newIngredient != null) {
                int ingredientsCount = course.getCourseIngredients().size();
                courseService.addCourseIngredient(course, newIngredient, kgPortion, TEST_AMOUNT);
                assertTrue(courseService.findCourseById(course.getCourseId()).getCourseIngredients().size() == (ingredientsCount + 1));

                courseService.delCourseIngredient(course.getCourseId(), newIngredient.getIngredientId());
                assertTrue(courseService.findCourseById(course.getCourseId()).getCourseIngredients().size() == ingredientsCount);
                break;
            }

        }

        // Add / upd / del course
        Course course = RestaurantDataGenerator.getRandomCourse();
        do {
            String newCourseName = Util.getRandomString(course.getName().length());
            if (courseService.findCoursesByNameFragment(newCourseName).size() == 0) {
                String courseName = course.getName();
                // Change current course name ...
                course.setName(newCourseName);
                Course newCourse = courseService.updCourse(course);
                assertTrue(courseService.findAllCourses().size() == coursesCount);
                assertTrue(courseService.findCoursesByNameFragment(newCourse.getName()).size() == 1);
                // ... and restore it
                newCourse.setName(courseName);
                newCourse = courseService.updCourse(newCourse);
                assertTrue(courseService.findAllCourses().size() == coursesCount);
                assertTrue(courseService.findCoursesByNameFragment(newCourse.getName()).size() == 1);

                // Save / delete new course
                course.setId(0);
                course.setName(newCourseName);
                newCourse = courseService.updCourse(course);
                assertTrue(courseService.findAllCourses().size() == (coursesCount + 1));
                assertTrue(courseService.findCoursesByNameFragment(newCourseName).size() == 1);

                courseService.delCourse(newCourse.getCourseId());
                assertTrue(courseService.findAllCourses().size() == coursesCount);
                assertTrue(courseService.findCoursesByNameFragment(newCourseName).size() == 0);
                break;
            }
        } while (true);
    }

    @Test(timeout = 2000)
    public void findJobPositionTest() throws Exception {
        for (JobPosition jobPosition : employeeService.findAllJobPositions()) {
            assertTrue(employeeService.findJobPositionById(jobPosition.getId()).equals(jobPosition));
            assertTrue(employeeService.findJobPositionByName(jobPosition.getName()).equals(jobPosition));
        }
    }

    @Test(timeout = 2000)
    @Transactional
    public void findAddUpdDelEmployeeTest() throws Exception {
        List<Employee> employees = employeeService.findAllEmployees();
        int employeeCount = employees.size();

        for (Employee employee : employees) {
            assertTrue(employeeService.findEmployeeById(employee.getEmployeeId()).equals(employee));
        }

        Employee employee = RestaurantDataGenerator.getRandomEmployee();
        String firstName = employee.getFirstName();
        do {
            String newFirstName = Util.getRandomString(firstName.length());
            if (employeeDao.findEmployeeByFirstName(newFirstName).size() == 0) {
                employee.setFirstName(newFirstName);
                employeeService.updEmployee(employee);
                assertTrue(employeeService.findEmployeeById(employee.getEmployeeId()).getFirstName().equals(newFirstName));
                employee.setFirstName(firstName);
                employeeService.updEmployee(employee);
                assertTrue(employeeService.findEmployeeById(employee.getEmployeeId()).getFirstName().equals(firstName));

                employee.setEmployeeId(0);
                employee.setFirstName(newFirstName);
                Employee newEmployee = employeeService.updEmployee(employee);
                assertTrue(employeeService.findAllEmployees().size() == (employeeCount + 1));
                employeeService.delEmployee(newEmployee.getEmployeeId());
                assertTrue(employeeService.findAllEmployees().size() == employeeCount);
                break;
            }
        } while (true);
    }

    @Test
    @Transactional
    public void findAddUpdDelMenuTest() throws Exception {
        List<Menu> menus = menuService.findAllMenus();
        int menuCount = menus.size();

        for (Menu menu : menus) {
            assertTrue(menuService.findMenuById(menu.getMenuId()).equals(menu));
        }

        do {
            String menuName = Util.getRandomString();
            if (menuDao.findMenuByName(menuName) == null) {
                Menu newMenu = menuService.addMenu(menuName);
                assertTrue(menuService.findAllMenus().size() == (menuCount + 1));

                Course course = RestaurantDataGenerator.getRandomCourse();
                menuService.addCourseToMenu(newMenu, course);
                assertTrue(newMenu.getCourses().size() == 1);
                menuService.delCourseFromMenu(newMenu,course);
                assertTrue(newMenu.getCourses().size() == 0);

                menuService.delMenu(newMenu.getMenuId());
                assertTrue(menuService.findAllMenus().size() == menuCount);
                break;
            }
        } while (true);
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

    @Test(timeout = 2000, expected = DataIntegrityException.class)
    public void changeClosedOrderTest() throws Exception {
        orderService.addCourseToOrder(closedOrder, RestaurantDataGenerator.getRandomCourse());
    }

    @Test(timeout = 2000)
    public void findTableTest() throws Exception {
        for (Table table : tableService.findAllTables()) {
            assertTrue(tableService.findTableById(table.getTableId()).equals(table));
        }
    }
}