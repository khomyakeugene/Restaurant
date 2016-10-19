package restaurant.service;

import com.company.restaurant.model.Employee;
import com.company.restaurant.model.JobPosition;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;
import restaurant.service.common.RestaurantDataGenerator;
import restaurant.service.common.RestaurantService;
import restaurant.util.Util;

import java.util.List;

import static org.junit.Assert.assertTrue;



/**
 * Created by Yevhen on 19.10.2016.
 */
public class EmployeeServiceTest extends RestaurantService {
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
}