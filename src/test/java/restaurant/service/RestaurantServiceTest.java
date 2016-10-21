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
import java.util.Random;
import java.util.stream.Collectors;

import static org.junit.Assert.assertTrue;

/**
 * Created by Yevhen on 18.10.2016.
 */
public class RestaurantServiceTest extends RestaurantService {
    private List<Ingredient> allIngredients;
    private static Order closedOrder;

    private Random random = new Random();

    private List<Ingredient> getAllIngredients() {
        if (allIngredients == null) {
            allIngredients = warehouseService.findAllIngredients();
        }

        return allIngredients;
    }

    private Ingredient getFirstNewIngredient(Course course) {
        Optional<Ingredient> ingredientOptional = getAllIngredients().stream().filter(ingredient -> !course.getCourseIngredients().stream().
                filter(courseIngredient -> courseIngredient.getIngredient().equals(ingredient)).
                findAny().isPresent()).findFirst();
        return ingredientOptional.isPresent() ? ingredientOptional.get() : null;
    }

    private List<Ingredient> getNonWarehouseIngredientList() {
        List<Warehouse> allWarehouseIngredients = warehouseService.findAllWarehouseIngredients();

        return getAllIngredients().stream().filter(ingredient -> !allWarehouseIngredients.stream().
                filter(warehouse -> warehouse.getIngredient().equals(ingredient)).
                findAny().isPresent()).collect(Collectors.toList());
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
                courseService.addCourseIngredient(course, newIngredient, kgPortion, Util.getRandomFloat());
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

                // Add new course
                newCourse = new Course();
                newCourse.setName(newCourseName);
                newCourse.setCourseCategory(courseService.findAllCourseCategories().get(0));
                newCourse.setWeight(Util.getRandomFloat());
                newCourse.setCost(Util.getRandomFloat());
                newCourse = courseService.updCourse(newCourse);
                assertTrue(courseService.findAllCourses().size() == (coursesCount + 1));
                assertTrue(courseService.findCoursesByNameFragment(newCourseName).size() == 1);
                // Delete new course
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
                menuService.delCourseFromMenu(newMenu, course);
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
        orderService.delCourseFromOrder(order, course);
        assertTrue(order.getCourses().size() == 0);

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

    // -------------------------------------------------------------
    @Transactional
    private void changeAmountInWarehouseTest(Warehouse warehouse) throws Exception {
        assertTrue(warehouseService.findIngredientInWarehouse(warehouse.getIngredient(), warehouse.getPortion()).equals(warehouse));

        Ingredient ingredient = warehouse.getIngredient();
        assertTrue(warehouseService.findIngredientById(ingredient.getIngredientId()).equals(ingredient));

        Portion portion = warehouse.getPortion();
        Float amount = warehouse.getAmount();
        Float newAmount = Util.getRandomFloat();

        warehouseService.setAmountInWarehouse(warehouse, newAmount);
        Warehouse newWarehouse = warehouseService.findIngredientInWarehouse(ingredient, portion);
        assertTrue((newWarehouse != null) && newWarehouse.getAmount().equals(newAmount));
        warehouseService.setAmountInWarehouse(warehouse, newAmount);
        warehouseService.setAmountInWarehouse(warehouse, amount);
        newWarehouse = warehouseService.findIngredientInWarehouse(ingredient, portion);
        assertTrue((newWarehouse != null) && newWarehouse.getAmount().equals(amount));

        Float addAmount = Util.getRandomFloat();
        warehouseService.addIngredientToWarehouse(ingredient, portion, addAmount);
        newWarehouse = warehouseService.findIngredientInWarehouse(ingredient, portion);
        assertTrue((newWarehouse != null) && newWarehouse.getAmount().equals(amount + addAmount));
        warehouseService.addIngredientToWarehouse(ingredient.getIngredientId(), portion.getPortionId(), addAmount);
        newWarehouse = warehouseService.findIngredientInWarehouse(ingredient, portion);
        assertTrue((newWarehouse != null) && newWarehouse.getAmount().equals(amount + addAmount + addAmount));

        warehouseService.takeIngredientFromWarehouse(ingredient, portion, addAmount);
        newWarehouse = warehouseService.findIngredientInWarehouse(ingredient, portion);
        assertTrue((newWarehouse != null) && newWarehouse.getAmount().equals(amount + addAmount));
        warehouseService.takeIngredientFromWarehouse(ingredient.getIngredientId(), portion.getPortionId(), addAmount);
        newWarehouse = warehouseService.findIngredientInWarehouse(ingredient, portion);
        assertTrue((newWarehouse != null) && newWarehouse.getAmount().equals(amount));
    }

    @Test(timeout = 2000)
    public void findPortionTest() throws Exception {
        for (Portion portion : warehouseService.findAllPortions()) {
            assertTrue(warehouseService.findPortionById(portion.getPortionId()).equals(portion));
        }
    }

    @Test(timeout = 2000)
    @Transactional
    public void addFindDelWarehouseTest() throws Exception {
        List<Warehouse> warehouseList = warehouseService.findAllWarehouseIngredients();
        int warehouseSize = warehouseList.size();

        if (warehouseSize > 0 ) {
            changeAmountInWarehouseTest(warehouseList.get(random.nextInt(warehouseSize)));

            warehouseService.clearWarehouse();
            assertTrue(warehouseService.findAllWarehouseIngredients().size() == 0);

            for (Warehouse warehouse : warehouseList) {
                warehouseService.addIngredientToWarehouse(warehouse.getIngredient(), warehouse.getPortion(), warehouse.getAmount());
            }
            assertTrue(warehouseService.findAllWarehouseIngredients().size() == warehouseSize);
        }

        List<Ingredient> nonWarehouseIngredients = getNonWarehouseIngredientList();
        if (nonWarehouseIngredients.size() > 0) {
            Ingredient ingredient = nonWarehouseIngredients.get(0);
            Portion portion = getKgPortion();
            Float amount = Util.getRandomFloat();

            warehouseService.addIngredientToWarehouse(ingredient, portion, amount);
            assertTrue(warehouseService.findAllWarehouseIngredients().size() == (warehouseSize + 1));
            Warehouse warehouse = warehouseService.findIngredientInWarehouse(ingredient, portion);
            assertTrue((warehouse != null) && warehouse.getAmount().equals(amount));

            if (warehouseSize == 0) {
                changeAmountInWarehouseTest(warehouse);
            }

            warehouseService.takeIngredientFromWarehouse(ingredient, portion, amount);
            assertTrue(warehouseService.findIngredientInWarehouse(ingredient, portion) == null);
            assertTrue(warehouseService.findAllWarehouseIngredients().size() == warehouseSize);
        }
    }
}