package restaurant.data;

import com.company.restaurant.model.Course;
import com.company.restaurant.model.Employee;
import com.company.restaurant.model.Table;
import restaurant.dao.common.RestaurantDao;

import java.util.List;
import java.util.Random;

/**
 * Created by Yevhen on 18.08.2016.
 */
public class RestaurantDataGenerator extends RestaurantDao {
    static private Random random = new Random();
    private static List<Employee> employeeList = employeeDao.findAllEmployees();
    private static List<Course> courseList = courseDao.findAllCourses();
    private static List<Table> tableList = tableDao.findAllTables();

    private static int getRandomInt(int bound) {
        return random.nextInt(bound);
    }

    public static Employee getRandomEmployee() {
        return employeeList.get(getRandomInt(employeeList.size()));
    }

    public static Course getRandomCourse() {
        return courseList.get(getRandomInt(courseList.size()));
    }

    public static Table getRandomTable() {
        return tableList.get(getRandomInt(tableList.size()));
    }
}
