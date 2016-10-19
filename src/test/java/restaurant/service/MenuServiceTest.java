package restaurant.service;

import com.company.restaurant.model.Course;
import com.company.restaurant.model.Menu;
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
public class MenuServiceTest extends RestaurantService {
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
}