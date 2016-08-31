package restaurant.dao;

import com.company.restaurant.model.*;
import org.junit.Test;
import restaurant.ObjectService;
import restaurant.Util;

import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertTrue;

/**
 * Created by Yevhen on 08.06.2016.
 */
public class RestaurantModelDaoTest extends RestaurantDao {
    private final static String DUPLICATE_KEY_VALUE_VIOLATES_MESSAGE = "duplicate key value violates";

    private static Employee employee() {
        return employeeDao.findAllEmployees().get(0);
    }

    private static int jobPositionId() {
        return jobPositionDao.findAllJobPositions().get(0).getId();
    }

    private static int courseCategoryId() {
        return courseCategoryDao.findAllCourseCategories().get(0).getId();
    }

    private static int employeeId() {
        return employee().getEmployeeId();
    }

    private static int tableId() {
        return tableDao.findAllTables().get(0).getTableId();
    }

    private static int lastTableId() {
        List<Table> tableList = tableDao.findAllTables();

        return tableList.get(tableList.size() - 1).getTableId();
    }

    @Test(timeout = 2000)
    public void addFindDelJobPosition() throws Exception {
        String name = Util.getRandomString();
        JobPosition jobPosition = jobPositionDao.addJobPosition(name);

        assertTrue(jobPosition.equals(jobPositionDao.findJobPositionByName(jobPosition.getName())));
        assertTrue(jobPosition.equals(jobPositionDao.findJobPositionById(jobPosition.getId())));

        jobPositionDao.delJobPosition(name);
        assertTrue(jobPositionDao.findJobPositionByName(name) == null);
        // Test delete of non-existent data
        jobPositionDao.delJobPosition(name);

        jobPositionDao.findAllJobPositions().forEach(System.out::println);
    }

    @Test(timeout = 2000)
    public void addFindDelEmployeeTest() throws Exception {
        String firstName = Util.getRandomString();
        String secondName = Util.getRandomString();
        Employee employee = new Employee();
        employee.setFirstName(firstName);
        employee.setSecondName(secondName);
        employee.setPhoneNumber(Util.getRandomString());
        employee.setSalary(Util.getRandomFloat());
        employee.setJobPosition(jobPositionDao.findJobPositionById(jobPositionId()));

        employee = employeeDao.addEmployee(employee);
        int employeeId = employee.getEmployeeId();

        // Select test <employee> and check
        assertTrue(employee.equals(employeeDao.findEmployeeByFirstName(firstName).get(0)));
        assertTrue(employee.equals(employeeDao.findEmployeeBySecondName(secondName).get(0)));
        assertTrue(employee.equals(employeeDao.findEmployeeByFirstAndSecondName(firstName, secondName).get(0)));
        assertTrue(employee.equals(employeeDao.findEmployeeById(employeeId)));

        employeeDao.delEmployee(employee);
        assertTrue(employeeDao.findEmployeeById(employeeId) == null);
    }

    @Test(timeout = 2000)
    public void addFindDelCourseCategoryTest() throws Exception {
        String name = Util.getRandomString();
        CourseCategory courseCategory = courseCategoryDao.addCourseCategory(name);

        assertTrue(courseCategory.equals(courseCategoryDao.findCourseCategoryByName(courseCategory.getName())));
        assertTrue(courseCategory.equals(courseCategoryDao.findCourseCategoryById(courseCategory.getId())));

        courseCategoryDao.delCourseCategory(name);
        assertTrue(courseCategoryDao.findCourseCategoryByName(name) == null);
        // Test delete of non-existent data
        courseCategoryDao.delCourseCategory(name);
    }

    @Test(timeout = 2000)
    public void addFindDelCourseTest() throws Exception {
        CourseCategory courseCategory = new CourseCategory();
        courseCategory.setId(courseCategoryId());

        Course course = new Course();
        String name = Util.getRandomString();
        course.setName(name);
        course.setWeight(Util.getRandomFloat());
        course.setCost(Util.getRandomFloat());
        course.setCourseCategory(courseCategory);
        course = courseDao.addCourse(course);

        assertTrue(course.equals(courseDao.findCourseByName(course.getName())));
        assertTrue(course.equals(courseDao.findCourseById(course.getCourseId())));

        courseDao.delCourse(name);
        assertTrue(courseDao.findCourseByName(name) == null);
        // Test delete by "the whole object"
        course = courseDao.addCourse(course);
        assertTrue(course.equals(courseDao.findCourseByName(name)));
        courseDao.delCourse(course);
        assertTrue(courseDao.findCourseByName(name) == null);
        // Test delete of non-existent data
        courseDao.delCourse(name);

        // Whole course list
        courseDao.findAllCourses().forEach(c -> {
            System.out.println(c);
            c.getCourseIngredients().forEach(System.out::println);
        });
    }

    @Test(timeout = 2000)
    public void addFindDelMenuTest() throws Exception {
        String name = Util.getRandomString();
        Menu menu = menuDao.addMenu(name);

        assertTrue(menu.equals(menuDao.findMenuByName(name)));
        assertTrue(menu.equals(menuDao.findMenuById(menu.getId())));

        // Courses in menu ----------------------------
        Course course1 = new Course();
        course1.setName(Util.getRandomString());
        course1.setWeight(Util.getRandomFloat());
        course1.setCost(Util.getRandomFloat());
        course1.setCourseCategory(courseCategoryDao.findCourseCategoryById(courseCategoryId()));
        course1 = courseDao.addCourse(course1);

        Course course2 = new Course();
        course2.setName(Util.getRandomString());
        course2.setWeight(Util.getRandomFloat());
        course2.setCost(Util.getRandomFloat());
        course2.setCourseCategory(courseCategoryDao.findCourseCategoryById(courseCategoryId()));
        course2 = courseDao.addCourse(course2);

        menuDao.addCourseToMenu(menu, course1);
        menuDao.addCourseToMenu(menu, course2);

        assertTrue(course1.equals(menuDao.findMenuCourseByCourseId(menu, course1.getCourseId())));

        for (Course course : menuDao.findMenuCourses(menu)) {
            menuDao.findMenuCourseByCourseId(menu, course.getCourseId());
            System.out.println(course);
        }

        menuDao.delCourseFromMenu(menu, course1);
        menuDao.delCourseFromMenu(menu, course2);

        assertTrue(menuDao.findMenuCourseByCourseId(menu, course1.getCourseId()) == null);

        courseDao.delCourse(course1);
        courseDao.delCourse(course2);
        // ----------------------------

        menuDao.findAllMenus().forEach(System.out::println);

        menuDao.delMenu(name);
        assertTrue(menuDao.findMenuByName(name) == null);
    }

    @Test(timeout = 2000)
    public void addFindDelTableTest() throws Exception {
        Table table = new Table();
        table.setDescription(Util.getRandomString());
        boolean tableWasNotAdded = true;
        do {
            try {
                table.setNumber(tableDao.findTableById(lastTableId()).getNumber() + Util.getRandomInteger());
                table = tableDao.addTable(table);
                tableWasNotAdded = false;
            } catch (RuntimeException e) {
                //  Error "duplicate key value violates unique constraint "ak_u_table_number_table"" could be generated
                if (!e.getMessage().contains(DUPLICATE_KEY_VALUE_VIOLATES_MESSAGE)) {
                    throw new RuntimeException(e);

                }
            }
        } while (tableWasNotAdded);

        assertTrue(table.equals(tableDao.findTableByNumber(table.getNumber())));
        assertTrue(table.equals(tableDao.findTableById(table.getTableId())));

        // Whole table list
        tableDao.findAllTables().forEach(System.out::println);

        tableDao.delTable(table);
        assertTrue(tableDao.findTableByNumber(table.getNumber()) == null);
    }

    @Test(timeout = 2000)
    public void addFindDelOrderTest() throws Exception {
        Employee employee = employeeDao.findEmployeeById(employeeId());
        Waiter waiter = new Waiter();
        ObjectService.copyObjectByAccessors(employee, waiter);
        waiter.setEmployeeId(0);

        employee = employeeDao.addEmployee(waiter);
        boolean employeeIsWaiter = (employee instanceof Waiter);

        Order order = new Order();
        order.setOrderNumber(Util.getRandomString());
        order.setWaiter(employee);
        order.setTable(tableDao.findTableById(tableId()));
        order.setState(stateDao.findStateByType("A"));
        order = orderDao.addOrder(order);
        int orderId = order.getOrderId();

        assertTrue(order.equals(orderDao.findOrderById(order.getOrderId())));

        // Courses in order ----------------------------
        Course course1 = new Course();
        course1.setName(Util.getRandomString());
        course1.setWeight(Util.getRandomFloat());
        course1.setCost(Util.getRandomFloat());
        course1.setCourseCategory(courseCategoryDao.findCourseCategoryById(courseCategoryId()));
        course1 = courseDao.addCourse(course1);

        Course course2 = new Course();
        course2.setName(Util.getRandomString());
        course2.setWeight(Util.getRandomFloat());
        course2.setCost(Util.getRandomFloat());
        course2.setCourseCategory(courseCategoryDao.findCourseCategoryById(courseCategoryId()));
        course2 = courseDao.addCourse(course2);

        orderDao.addCourseToOrder(order, course1);
        orderDao.addCourseToOrder(order, course1);
        orderDao.addCourseToOrder(order, course2);

        for (Course course : orderDao.findOrderCourses(order)) {
            orderDao.findOrderCourseByCourseId(order, course.getCourseId());
            System.out.println(course);
        }

        assertTrue(course1.equals(orderDao.findOrderCourseByCourseId(order, course1.getCourseId())));

        orderDao.takeCourseFromOrder(order, course1);
        orderDao.takeCourseFromOrder(order, course1);
        orderDao.takeCourseFromOrder(order, course2);

        assertTrue(orderDao.findOrderCourseByCourseId(order, course1.getCourseId()) == null);

        courseDao.delCourse(course1);
        courseDao.delCourse(course2);
        // ----------------------------

        System.out.println("All orders:");
        orderDao.findAllOrders().forEach(System.out::println);

        System.out.println("Open orders:");
        orderDao.findAllOrders("A").forEach(System.out::println);

        System.out.println("Closed orders:");
        orderDao.findAllOrders("B").forEach(System.out::println);

        if (employeeIsWaiter) {
            waiter = (Waiter) employeeDao.findEmployeeById(employee.getEmployeeId());
            System.out.println(waiter);

            Set<Order> orders = waiter.getOrders();
            Order[] orderArray = orders.toArray(new Order[orders.size()]);
            assertTrue(orderArray[0].equals(order));
        }

        orderDao.delOrder(order);
        assertTrue(orderDao.findOrderById(orderId) == null);

        employeeDao.delEmployee(employee.getEmployeeId());

        stateGraphDao.findEntityStateGraph(orderDao.orderEntityName()).forEach(System.out::println);
    }

    @Test(timeout = 2000)
    public void addDelCookedCourse() throws Exception {
        Course testCourse = new Course();
        testCourse.setName(Util.getRandomString());
        testCourse.setWeight(Util.getRandomFloat());
        testCourse.setCost(Util.getRandomFloat());
        testCourse.setCourseCategory(courseCategoryDao.findCourseCategoryById(courseCategoryId()));
        testCourse = courseDao.addCourse(testCourse);

        Employee employee = employeeDao.findEmployeeById(employeeId());
        Cook cook = new Cook();
        ObjectService.copyObjectByAccessors(employee, cook);
        cook.setEmployeeId(0);
        employee = employeeDao.addEmployee(cook);
        boolean employeeIsCook = (employee instanceof Cook);

        CookedCourse cookedCourse = cookedCourseDao.addCookedCourse(testCourse, employee,
                Util.getRandomFloat());

        cookedCourseDao.findAllCookedCourses().forEach(System.out::println);

        if (employeeIsCook) {
            cook = (Cook) employeeDao.findEmployeeById(employee.getEmployeeId());
            System.out.println(cook);

            Set<CookedCourse> cookedCourses = cook.getCookedCourses();
            CookedCourse[] cookedCourseArray =
                    cookedCourses.toArray(new CookedCourse[cookedCourses.size()]);
            assertTrue(cookedCourseArray[0].equals(cookedCourse));
        }

        cookedCourseDao.delCookedCourse(cookedCourse);
        employeeDao.delEmployee(employee.getEmployeeId());

        // ----------------------
        CookAndWaiter cookAndWaiter = new CookAndWaiter();
        ObjectService.copyObjectByAccessors(employee, cookAndWaiter);
        cookAndWaiter.setEmployeeId(0);
        employee = employeeDao.addEmployee(cookAndWaiter);
        boolean employeeIsCookAndWaiter = (employee instanceof CookAndWaiter);

        cookedCourse = cookedCourseDao.addCookedCourse(testCourse, employee,
                Util.getRandomFloat());
        if (employeeIsCookAndWaiter) {
            cookAndWaiter = (CookAndWaiter) employeeDao.findEmployeeById(employee.getEmployeeId());
            System.out.println(cookAndWaiter);

            Set<CookedCourse> cookedCourses = cookAndWaiter.getCookedCourses();
            CookedCourse[] cookedCourseArray =
                    cookedCourses.toArray(new CookedCourse[cookedCourses.size()]);
            assertTrue(cookedCourseArray[0].equals(cookedCourse));
        }

        cookedCourseDao.delCookedCourse(cookedCourse);
        employeeDao.delEmployee(employee.getEmployeeId());
        courseDao.delCourse(testCourse);
    }

    @Test(timeout = 30000)
    public void addFindDelWarehouseTest() throws Exception {
        System.out.println("portionDao test ... ");
        for (Portion portion : portionDao.findAllPortions()) {
            assertTrue(portion.equals(portionDao.findPortionById(portion.getPortionId())));
        }
        System.out.println("ingredientDao test ... ");
        for (Ingredient ingredient : ingredientDao.findAllIngredients()) {
            assertTrue(ingredient.equals(ingredientDao.findIngredientById(ingredient.getIngredientId())));
        }

        System.out.println("warehouseDao test ... ");
        for (Ingredient ingredient : ingredientDao.findAllIngredients()) {
            // If there is some information about ingredient in the warehouse, this information is important
            // as a initial data in the system, and it is prohibited to modify this information
            List<Warehouse> warehouseList = warehouseDao.findIngredientInWarehouseById(ingredient.getIngredientId());
            if (warehouseList == null) {
                for (Portion portion : portionDao.findAllPortions()) {
                    float amountToAdd = Util.getRandomFloat();
                    warehouseDao.addIngredientToWarehouse(ingredient, portion, amountToAdd);
                    float amountToTake = Util.getRandomFloat();
                    warehouseDao.takeIngredientFromWarehouse(ingredient, portion, amountToTake);

                    Warehouse warehouse = warehouseDao.findIngredientInWarehouse(ingredient, portion);
                    if (warehouse != null) {
                        // "Clear" warehouse position
                        warehouseDao.takeIngredientFromWarehouse(ingredient, portion, amountToAdd - amountToTake);
                    }
                }
                // Test add / delete data only for ONE ingredient!
                break;
            } else {
                warehouseDao.findIngredientInWarehouseByName(ingredient.getName()).forEach(System.out::println);
            }
        }

        System.out.println("Warehouse all ingredients:");
        warehouseDao.findAllWarehouseIngredients().forEach(System.out::println);
        System.out.println("Warehouse elapsing ingredients:");
        warehouseDao.findAllElapsingWarehouseIngredients((float) 500.0).forEach(System.out::println);
    }

    @Test(timeout = 2000)
    public void addFindDelCommonData() throws Exception {
        CommonData commonData = new CommonData();
        String commonDataName = Util.getRandomString();
        String commonDataValue = Util.getRandomString();

        commonData.setName(commonDataName);
        commonData.setValue(commonDataValue);
        commonData = commonDataDao.addCommonData(commonData);
        int commonDataId = commonData.getCommonDataId();

        assertTrue(commonData.equals(commonDataDao.findCommonDataById(commonDataId)));
        assertTrue(commonData.equals(commonDataDao.findCommonDataByName(commonDataName)));
        assertTrue(commonDataValue.equals(commonDataDao.getCommonDataValue(commonDataId)));
        assertTrue(commonDataValue.equals(commonDataDao.getCommonDataValue(commonDataName)));

        String commonDataNewValue = Util.getRandomString();
        commonDataDao.updCommonDataValue(commonDataId, commonDataNewValue);
        assertTrue(commonDataNewValue.equals(commonDataDao.getCommonDataValue(commonDataId)));

        commonDataNewValue = Util.getRandomString();
        commonDataDao.updCommonDataValue(commonDataName, commonDataNewValue);
        assertTrue(commonDataNewValue.equals(commonDataDao.getCommonDataValue(commonDataName)));

        commonDataDao.delCommonData(commonDataName);
        assertTrue(commonDataDao.findCommonDataById(commonDataId) == null);
    }

}