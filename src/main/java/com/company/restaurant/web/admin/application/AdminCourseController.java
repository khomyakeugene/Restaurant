package com.company.restaurant.web.admin.application;

import com.company.restaurant.model.Course;
import com.company.restaurant.web.admin.application.common.AdminCRUDPhotoHolderController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.stream.Collectors;

/**
 * Created by Yevhen on 04.09.2016.
 */
@Controller
public class AdminCourseController extends AdminCRUDPhotoHolderController<Course> {
    private static final String ADMIN_COURSE_LIST_PAGE_VIEW_NAME = "admin-application/course/admin-course-list-page";
    private static final String ADMIN_COURSE_LIST_REQUEST_MAPPING_VALUE = "/admin-course-list";
    private static final String ADMIN_COURSE_REQUEST_MAPPING_PATTERN = "/admin-course/%d";
    private static final String ADMIN_COURSE_REQUEST_MAPPING_VALUE = "/admin-course/{courseId}";
    private static final String ADMIN_DELETE_COURSE_INGREDIENT_REQUEST_MAPPING_VALUE =
            "/admin-course/delete-course-ingredient/{courseId}/{ingredientId}";
    private static final String ADMIN_SAVE_OR_DELETE_COURSE_PAGE_VIEW_NAME =
            "admin-application/course/admin-save-or-delete-course-page";
    private static final String ADMIN_SAVE_OR_DELETE_COURSE_REQUEST_MAPPING_VALUE = "/save-or-delete-course";
    private static final String ADMIN_CREATE_COURSE_PAGE_VIEW_NAME = "admin-application/course/admin-create-course-page";
    private static final String ADMIN_PREPARE_NEW_COURSE_REQUEST_MAPPING_VALUE = "/prepare-new-course";
    private static final String ADMIN_CREATE_COURSE_REQUEST_MAPPING_VALUE = "/create-course";

    private static final String NEW_INGREDIENTS_VAR_NAME = "newIngredients";

    private static final String COURSE_ID_PAR_NAME = "courseId";
    private static final String COURSE_NAME_PAR_NAME = "courseName";
    private static final String COURSE_WEIGHT_PAR_NAME = "courseWeight";
    private static final String COURSE_COST_PAR_NAME = "courseCost";
    private static final String COURSE_CATEGORY_ID_PAR_NAME = "courseCategoryId";
    private static final String COURSE_INGREDIENT_ID_PAR_NAME = "courseIngredientId";
    private static final String COURSE_INGREDIENT_AMOUNT_PAR_NAME = "courseIngredientAmount";

    private static final String DEFAULT_COURSE_CATEGORY_NAME_VALUE = "Salads";

    private Course newCourse() {
        Course result = new Course();
        result.setCourseCategory(courseService.findCourseCategoryByName(DEFAULT_COURSE_CATEGORY_NAME_VALUE));

        return result;
    }

    private void initCourseCategoryEnvironment(Course course) {
        setCurrentObject(course);
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
        // Clear new record parameters
        clearNewIngredientRecord();

        // Important to return to current object page (see method <toCurrentObjectPage>) and after possibly
        // called <ErrorHandler> that next redirect to "current course JSP-page" to show error message and to have
        // the possibility to correct editing parameters of "current object"
        setCurrentObject(course);
    }

    private void prepareCourseEnvironment() {
        prepareCourseEnvironment(0);
    }

    private void setCurrentObjectAttributes(String courseName,
                                            int courseCategoryId,
                                            Float courseWeight,
                                            Float courseCost) {
        Course course = getCurrentObject();

        course.setName(courseName);
        course.setCourseCategory(courseService.findCourseCategoryById(courseCategoryId));
        course.setWeight(courseWeight);
        course.setCost(courseCost);
    }

    private Course saveCourse(String courseName,
                              int courseCategoryId,
                              Float courseWeight,
                              Float courseCost) {
        setCurrentObjectAttributes(courseName, courseCategoryId, courseWeight, courseCost);

        return courseService.updCourse(getCurrentObject());
    }

    private Course createCourse(String courseName,
                                int courseCategoryId,
                                Float courseWeight,
                                Float courseCost) {
        return saveCourse(courseName, courseCategoryId, courseWeight, courseCost);
    }

    private void deleteCourse(int courseId) {
        courseService.delCourse(courseId);
    }

    private ModelAndView toCurrentObjectPage() {
        return new ModelAndView(REDIRECT_PREFIX + String.format(ADMIN_COURSE_REQUEST_MAPPING_PATTERN,
                getCurrentObject().getCourseId()));
    }

    private void addCourseIngredient(int courseIngredientId,
                                     int portionId,
                                     Float courseIngredientAmount) {
        // Important for the possible next attempt to add new warehouse record
        storeNewIngredientId(courseIngredientId);
        storeNewAmount(courseIngredientAmount);
        storeNewPortionId(portionId);

        courseService.addCourseIngredient(getCurrentObject(),
                warehouseService.findIngredientById(courseIngredientId),
                warehouseService.findPortionById(portionId),
                courseIngredientAmount);
    }

    private ModelAndView uploadCoursePhoto(@RequestParam(FILE_PAR_NAME) MultipartFile file,
                                           @RequestParam(COURSE_NAME_PAR_NAME) String courseName,
                                           @RequestParam(COURSE_CATEGORY_ID_PAR_NAME) int courseCategoryId,
                                           @RequestParam(COURSE_WEIGHT_PAR_NAME) Float courseWeight,
                                           @RequestParam(COURSE_COST_PAR_NAME) Float courseCost) {
        // Store actual parameters JSP-view from in "current object" to show then actual values in JSP-view
        setCurrentObjectAttributes(courseName, courseCategoryId, courseWeight, courseCost);

        return uploadPhoto(file);
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

        modelAndView.setViewName(ADMIN_SAVE_OR_DELETE_COURSE_PAGE_VIEW_NAME);

        return modelAndView;
    }

    @RequestMapping(value = ADMIN_SAVE_OR_DELETE_COURSE_REQUEST_MAPPING_VALUE,
            params = SUBMIT_BUTTON_PAR_NAME, method = RequestMethod.POST)
    public ModelAndView saveOrDeleteCourse(@RequestParam(COURSE_ID_PAR_NAME) int courseId,
                                           @RequestParam(COURSE_NAME_PAR_NAME) String courseName,
                                           @RequestParam(COURSE_CATEGORY_ID_PAR_NAME) int courseCategoryId,
                                           @RequestParam(COURSE_WEIGHT_PAR_NAME) Float courseWeight,
                                           @RequestParam(COURSE_COST_PAR_NAME) Float courseCost,
                                           @RequestParam(COURSE_INGREDIENT_ID_PAR_NAME) int courseIngredientId,
                                           @RequestParam(PORTION_ID_PAR_NAME) int portionId,
                                           @RequestParam(COURSE_INGREDIENT_AMOUNT_PAR_NAME) Float courseIngredientAmount,
                                           @RequestParam(SUBMIT_BUTTON_PAR_NAME) String submitButtonValue
    ) {
        if (isSubmitSave(submitButtonValue)) {
            saveCourse(courseName, courseCategoryId, courseWeight, courseCost);
        } else if (isSubmitDelete(submitButtonValue)) {
            deleteCourse(courseId);
        } else if (isSubmitAdd(submitButtonValue)) {
            addCourseIngredient(courseIngredientId, portionId, courseIngredientAmount);
        }

        return (((isSubmitSave(submitButtonValue) || isSubmitDelete(submitButtonValue))) && (courseId > 0)) ?
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

    @RequestMapping(value = ADMIN_CREATE_COURSE_REQUEST_MAPPING_VALUE,
            params = SUBMIT_BUTTON_PAR_NAME, method = RequestMethod.POST)
    public ModelAndView createNewCourse(@RequestParam(COURSE_NAME_PAR_NAME) String courseName,
                                        @RequestParam(COURSE_CATEGORY_ID_PAR_NAME) int courseCategoryId,
                                        @RequestParam(COURSE_WEIGHT_PAR_NAME) Float courseWeight,
                                        @RequestParam(COURSE_COST_PAR_NAME) Float courseCost,
                                        @RequestParam(SUBMIT_BUTTON_PAR_NAME) String submitButtonValue) {
        createCourse(courseName, courseCategoryId, courseWeight, courseCost);

        return toCurrentObjectPage();
    }

    @RequestMapping(value = ADMIN_DELETE_COURSE_INGREDIENT_REQUEST_MAPPING_VALUE, method = RequestMethod.GET)
    public ModelAndView deleteCourseIngredient(@PathVariable int courseId, @PathVariable int ingredientId) {
        courseService.delCourseIngredient(courseId, ingredientId);

        return toCurrentObjectPage();
    }

    @RequestMapping(value = ADMIN_CREATE_COURSE_REQUEST_MAPPING_VALUE, method = RequestMethod.POST)
    protected ModelAndView uploadNewCoursePhoto(@RequestParam(FILE_PAR_NAME) MultipartFile file,
                                                @RequestParam(COURSE_NAME_PAR_NAME) String courseName,
                                                @RequestParam(COURSE_CATEGORY_ID_PAR_NAME) int courseCategoryId,
                                                @RequestParam(COURSE_WEIGHT_PAR_NAME) Float courseWeight,
                                                @RequestParam(COURSE_COST_PAR_NAME) Float courseCost) {
        return uploadCoursePhoto(file, courseName, courseCategoryId, courseWeight, courseCost);
    }

    @RequestMapping(value = ADMIN_SAVE_OR_DELETE_COURSE_REQUEST_MAPPING_VALUE, method = RequestMethod.POST)
    protected ModelAndView uploadExistingCoursePhoto(@RequestParam(FILE_PAR_NAME) MultipartFile file,
                                                     @RequestParam(COURSE_NAME_PAR_NAME) String courseName,
                                                     @RequestParam(COURSE_CATEGORY_ID_PAR_NAME) int courseCategoryId,
                                                     @RequestParam(COURSE_WEIGHT_PAR_NAME) Float courseWeight,
                                                     @RequestParam(COURSE_COST_PAR_NAME) Float courseCost) {
        return uploadCoursePhoto(file, courseName, courseCategoryId, courseWeight, courseCost);
    }
}
