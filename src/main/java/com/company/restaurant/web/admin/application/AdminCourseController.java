package com.company.restaurant.web.admin.application;

import com.company.restaurant.model.Course;
import com.company.restaurant.web.admin.application.common.AdminCRUDController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.stream.Collectors;

/**
 * Created by Yevhen on 04.09.2016.
 */
@Controller
public class AdminCourseController extends AdminCRUDController<Course> {
    private static final String ADMIN_COURSE_LIST_PAGE_VIEW_NAME = "admin-application/course/admin-course-list-page";
    private static final String ADMIN_COURSE_LIST_REQUEST_MAPPING_VALUE = "/admin-course-list";
    private static final String ADMIN_COURSE_REQUEST_MAPPING_PATTERN = "/admin-course/%d";
    private static final String ADMIN_COURSE_REQUEST_MAPPING_VALUE = "/admin-course/{courseId}";
    private static final String ADMIN_DELETE_COURSE_INGREDIENT_REQUEST_MAPPING_VALUE =
            "/admin-course/delete_course_ingredient/{courseId}/{ingredientId}";
    private static final String ADMIN_SAVE_OR_DELETE_COURSE_PAGE_VIEW_NAME =
            "admin-application/course/admin-save-or-delete-course-page";
    private static final String ADMIN_SAVE_OR_DELETE_COURSE_REQUEST_MAPPING_VALUE = "/save-or-delete-course";
    private static final String ADMIN_CREATE_COURSE_PAGE_VIEW_NAME = "admin-application/course/admin-create-course-page";
    private static final String ADMIN_PREPARE_NEW_COURSE_REQUEST_MAPPING_VALUE = "/prepare-new-course";
    private static final String ADMIN_CREATE_EMPLOYEE_REQUEST_MAPPING_VALUE = "/create-course";

    private static final String COURSE_VAR_NAME = "course";
    private static final String COURSE_ID_VAR_NAME = "courseId";
    private static final String COURSE_NAME_VAR_NAME = "courseName";
    private static final String COURSE_WEIGHT_VAR_NAME = "courseWeight";
    private static final String COURSE_COST_VAR_NAME = "courseCost";
    private static final String COURSE_CATEGORY_ID_VAR_NAME = "courseCategoryId";
    private static final String NEW_INGREDIENTS_VAR_NAME = "newIngredients";
    private static final String COURSE_INGREDIENT_NAME_VAR_NAME = "courseIngredientName";
    private static final String COURSE_PORTION_DESCRIPTION_VAR_NAME = "coursePortionDescription";
    private static final String COURSE_INGREDIENT_AMOUNT_VAR_NAME = "courseIngredientAmount";

    private static final String DEFAULT_COURSE_CATEGORY_NAME_VALUE = "Salads";

    private Course newCourse() {
        Course result = new Course();
        result.setCourseCategory(courseService.findCourseCategoryByName(DEFAULT_COURSE_CATEGORY_NAME_VALUE));

        return result;
    }

    private void initCourseCategoryEnvironment(Course course) {
        // Current job position name - important to correct work of <form:select> in view
        String courseCategoryName = course.getCourseCategory().getName();
        modelAndView.addObject(COURSE_VAR_NAME, course);
    }

    private void initNewIngredientList(Course course) {
        // Ingredients which are new for this course
        modelAndView.addObject(NEW_INGREDIENTS_VAR_NAME, warehouseService.findAllIngredients().stream().
                filter(ingredient -> !course.getCourseIngredients().stream().
                        filter(courseIngredient -> courseIngredient.getIngredient().equals(ingredient)).
                        findAny().isPresent()).collect(Collectors.toList()));
    }

    private void prepareCourseEnvironment(int courseId) {
        Course course;
        if (courseId > 0) {
            course = courseService.findCourseById(courseId);
            if (course == null) {
                course = newCourse();
            }
        } else {
            course = newCourse();
        }

        // Course category combo box list
        initCourseCategoryEnvironment(course);
        // Ingredient combo box list: ingredients which are new for this course
        initNewIngredientList(course);
    }

    private void prepareCourseEnvironment() {
        prepareCourseEnvironment(0);
    }

    private Course saveCourse(int courseId,
                              String courseName,
                              int courseCategoryId,
                              Float courseWeight,
                              Float courseCost) {
        Course course = null;

        // Temporarily: get "old" image from database - because I do not know how to manipulate with image
        if (courseId > 0) {
            course = courseService.findCourseById(courseId);
        }
        if (course == null) {
            course = new Course();
        }

        // Important to return to current object page (see method <toCurrentObjectPage>) and after possibly
        // called <ErrorHandler> that next redirect to "current course JSP-page" to show error message and to have
        // the possibility to correct editing parameters of "current object"
        setCurrentObject(course);

        course.setCourseId(courseId);
        course.setName(courseName);
        course.setCourseCategory(courseService.findCourseCategoryById(courseCategoryId));
        course.setWeight(courseWeight);
        course.setCost(courseCost);

        return courseService.updCourse(course);
    }

    private Course createCourse(String courseName,
                                int courseCategoryId,
                                Float courseWeight,
                                Float courseCost) {
        return saveCourse(0, courseName, courseCategoryId, courseWeight, courseCost);
    }


    private void deleteCourse(int courseId) {
        courseService.delCourse(courseId);
    }

    private ModelAndView toCurrentObjectPage() {
        return new ModelAndView(REDIRECT_PREFIX + String.format(ADMIN_COURSE_REQUEST_MAPPING_PATTERN,
                getCurrentObject().getCourseId()));
    }

    private void addCourseIngredient(
            String courseIngredientName,
            String coursePortionDescription,
            Float courseIngredientAmount) {
        courseService.addCourseIngredient(getCurrentObject(),
                warehouseService.findIngredientByName(courseIngredientName),
                warehouseService.findPortionByDescription(coursePortionDescription),
                courseIngredientAmount);
    }

    @RequestMapping(value = ADMIN_COURSE_LIST_REQUEST_MAPPING_VALUE, method = RequestMethod.GET)
    public ModelAndView courseListPage() {
        clearErrorMessage();

        modelAndView.addObject(COURSES_VAR_NAME, courseService.findAllCourses());
        modelAndView.setViewName(ADMIN_COURSE_LIST_PAGE_VIEW_NAME);

        return modelAndView;
    }

    @RequestMapping(value = ADMIN_COURSE_REQUEST_MAPPING_VALUE, method = RequestMethod.GET)
    public ModelAndView course(@PathVariable int courseId) {
        clearErrorMessage();

        prepareCourseEnvironment(courseId);

        setCurrentObject(courseService.findCourseById(courseId));
        modelAndView.setViewName(ADMIN_SAVE_OR_DELETE_COURSE_PAGE_VIEW_NAME);

        return modelAndView;
    }

    @RequestMapping(value = ADMIN_SAVE_OR_DELETE_COURSE_REQUEST_MAPPING_VALUE, method = RequestMethod.POST)
    public ModelAndView saveOrDeleteCourse(@RequestParam(COURSE_ID_VAR_NAME) int courseId,
                                           @RequestParam(COURSE_NAME_VAR_NAME) String courseName,
                                           @RequestParam(COURSE_CATEGORY_ID_VAR_NAME) int courseCategoryId,
                                           @RequestParam(COURSE_WEIGHT_VAR_NAME) Float courseWeight,
                                           @RequestParam(COURSE_COST_VAR_NAME) Float courseCost,
                                           @RequestParam(COURSE_INGREDIENT_NAME_VAR_NAME) String courseIngredientName,
                                           @RequestParam(COURSE_PORTION_DESCRIPTION_VAR_NAME) String coursePortionDescription,
                                           @RequestParam(COURSE_INGREDIENT_AMOUNT_VAR_NAME) Float courseIngredientAmount,
                                           @RequestParam(SUBMIT_BUTTON_VAR_NAME) String submitButtonValue
    ) {
        if (isSubmitSave(submitButtonValue)) {
            saveCourse(courseId, courseName, courseCategoryId, courseWeight, courseCost);
        } else if (isSubmitDelete(submitButtonValue)) {
            deleteCourse(courseId);
        } else if (isSubmitAdd(submitButtonValue)) {
            addCourseIngredient(courseIngredientName, coursePortionDescription, courseIngredientAmount);
        }

        return (isSubmitSave(submitButtonValue) || isSubmitDelete(submitButtonValue)) ?
                new ModelAndView(REDIRECT_PREFIX + ADMIN_COURSE_LIST_REQUEST_MAPPING_VALUE) :
                toCurrentObjectPage();
    }

    @RequestMapping(value = ADMIN_PREPARE_NEW_COURSE_REQUEST_MAPPING_VALUE, method = RequestMethod.POST)
    public ModelAndView prepareNewCourse() {
        clearErrorMessage();

        prepareCourseEnvironment();

        modelAndView.setViewName(ADMIN_CREATE_COURSE_PAGE_VIEW_NAME);

        return modelAndView;
    }

    @RequestMapping(value = ADMIN_CREATE_EMPLOYEE_REQUEST_MAPPING_VALUE, method = RequestMethod.POST)
    public ModelAndView createEmployee(@RequestParam(COURSE_NAME_VAR_NAME) String courseName,
                                       @RequestParam(COURSE_CATEGORY_ID_VAR_NAME) int courseCategoryId,
                                       @RequestParam(COURSE_WEIGHT_VAR_NAME) Float courseWeight,
                                       @RequestParam(COURSE_COST_VAR_NAME) Float courseCost) {
        createCourse(courseName, courseCategoryId, courseWeight, courseCost);

        return toCurrentObjectPage();
    }

    @RequestMapping(value = ADMIN_DELETE_COURSE_INGREDIENT_REQUEST_MAPPING_VALUE, method = RequestMethod.GET)
    public ModelAndView deleteCourseIngredient(@PathVariable int courseId, @PathVariable int ingredientId) {
        courseService.delCourseIngredient(courseId, ingredientId);

        return toCurrentObjectPage();
    }
}
