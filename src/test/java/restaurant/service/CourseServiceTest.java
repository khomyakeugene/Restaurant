package restaurant.service;

import com.company.restaurant.model.Course;
import com.company.restaurant.model.CourseCategory;
import com.company.restaurant.model.Ingredient;
import com.company.restaurant.model.Portion;
import org.junit.Test;
import restaurant.service.common.RestaurantService;
import restaurant.util.Util;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertTrue;

/**
 * Created by Yevhen on 18.10.2016.
 */
public class CourseServiceTest extends RestaurantService {
    private static final Float TEST_AMOUNT = 1.0f;
    private List<Ingredient> allIngredients;

    private Ingredient getFirstNewIngredient(Course course) {
        if (allIngredients == null) {
            allIngredients = warehouseService.findAllIngredients();
        }

        Optional<Ingredient> ingredientOptional = allIngredients.stream().filter(ingredient -> !course.getCourseIngredients().stream().
                        filter(courseIngredient -> courseIngredient.getIngredient().equals(ingredient)).
                        findAny().isPresent()).findFirst();
        return ingredientOptional.isPresent() ? ingredientOptional.get() : null;
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

    @Test//(timeout = 2000)
    //@Transactional
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
                assertTrue(course.getCourseIngredients().size() == (ingredientsCount + 1));
                courseService.delCourseIngredient(course.getCourseId(), newIngredient.getIngredientId());
                assertTrue(course.getCourseIngredients().size() == ingredientsCount);
            }

            // Add / upd / del course
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
            } while(true);
        }
    }
}