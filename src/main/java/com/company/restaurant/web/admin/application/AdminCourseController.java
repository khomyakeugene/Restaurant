package com.company.restaurant.web.admin.application;

import com.company.restaurant.model.Course;
import com.company.restaurant.service.CourseService;
import com.company.restaurant.web.admin.application.common.AdminApplicationController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Yevhen on 04.09.2016.
 */
@Controller
public class AdminCourseController extends AdminApplicationController {
    private static final String ADMIN_COURSE_LIST_PAGE_VIEW_NAME = "admin-application/course/admin-course-list-page";
    private static final String ADMIN_COURSE_LIST_REQUEST_MAPPING_VALUE = "/admin-course-list";
    private static final String ADMIN_APPLICATION_COURSE_REQUEST_MAPPING_VALUE = "/admin-course/{courseId}";
    private static final String ADMIN_SAVE_OR_DELETE_COURSE_PAGE_VIEW_NAME =
            "admin-application/course/admin-save-or-delete-course-page";
    private static final String ADMIN_SAVE_OR_DELETE_COURSE_REQUEST_MAPPING_VALUE = "/save-or-delete-course";

    private static final String COURSES_VAR_NAME = "courses";
    private static final String COURSE_VAR_NAME = "course";
    private static final String COURSE_ID_VAR_NAME = "courseId";
    private static final String COURSE_NAME_VAR_NAME = "courseName";
    private static final String COURSE_WEIGHT_VAR_NAME = "courseWeight";
    private static final String COURSE_COST_VAR_NAME = "courseCost";

    private CourseService courseService;

    @Autowired
    public void setCourseService(CourseService courseService) {
        this.courseService = courseService;
    }

    private Course saveCourse(int courseId,
                              String courseName,
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

        course.setCourseId(courseId);
        course.setName(courseName);
        course.setWeight(courseWeight);
        course.setCost(courseCost);

        return courseService.updCourse(course);
    }

    private void deleteCourse(int courseId) {
        courseService.delCourse(courseId);
    }

    @RequestMapping(value = ADMIN_COURSE_LIST_REQUEST_MAPPING_VALUE, method = RequestMethod.GET)
    public ModelAndView courseListPage() {
        modelAndView.clear();

        modelAndView.addObject(COURSES_VAR_NAME, courseService.findAllCourses());
        modelAndView.setViewName(ADMIN_COURSE_LIST_PAGE_VIEW_NAME);

        // To possible further return from <ExceptionHandler>
        storeLastNavigationViewName();

        return modelAndView;
    }

    @RequestMapping(value = ADMIN_APPLICATION_COURSE_REQUEST_MAPPING_VALUE, method = RequestMethod.GET)
    public ModelAndView course(@PathVariable int courseId) {
        modelAndView.clear();

        modelAndView.addObject(COURSE_VAR_NAME, courseService.findCourseById(courseId));
        modelAndView.setViewName(ADMIN_SAVE_OR_DELETE_COURSE_PAGE_VIEW_NAME);

        // To possible further return from <ExceptionHandler>
        storeLastNavigationViewName();

        return modelAndView;
    }

    @RequestMapping(value = ADMIN_SAVE_OR_DELETE_COURSE_REQUEST_MAPPING_VALUE, method = RequestMethod.POST)
    public ModelAndView saveOrDeleteCourse(@RequestParam(COURSE_ID_VAR_NAME) int courseId,
                                           @RequestParam(COURSE_NAME_VAR_NAME) String courseName,
                                           @RequestParam(COURSE_WEIGHT_VAR_NAME) Float courseWeight,
                                           @RequestParam(COURSE_COST_VAR_NAME) Float courseCost,
                                           @RequestParam(SUBMIT_BUTTON_VAR_NAME) String submitButtonValue
    ) {
        if (isSubmitSave(submitButtonValue)) {
            saveCourse(courseId, courseName, courseWeight, courseCost);
        } else if (isSubmitDelete(submitButtonValue)) {
            deleteCourse(courseId);
        }

        modelAndView.clear();
        modelAndView.setViewName(REDIRECT_PREFIX + ADMIN_COURSE_LIST_REQUEST_MAPPING_VALUE);

        return modelAndView;
    }
}
