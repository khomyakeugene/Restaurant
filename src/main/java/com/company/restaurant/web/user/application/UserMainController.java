package com.company.restaurant.web.user.application;

import com.company.restaurant.service.CourseService;
import com.company.restaurant.web.user.application.common.UserApplicationController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Yevhen on 28.07.2016.
 */
@Controller
public class UserMainController extends UserApplicationController {
    private static final String MAIN_PAGE_VIEW_NAME = "user-application/main-page";
    private static final String COURSE_VIEW_NAME = "user-application/course";
    private static final String USER_APPLICATION_MAIN_REQUEST_MAPPING_VALUE = "/user-application-main";
    private static final String USER_APPLICATION_COURSE_REQUEST_MAPPING_VALUE = "/course/{courseId}";
    private static final String SEARCH_REQUEST_MAPPING_VALUE = "/search";

    private static final String COURSES_VAR_NAME = "courses";
    private static final String COURSE_VAR_NAME = "course";
    private static final String COURSE_NAME_VAR_NAME = "courseName";

    private CourseService courseService;

    @Autowired
    public void setCourseService(CourseService courseService) {
        this.courseService = courseService;
    }

    @RequestMapping(value = USER_APPLICATION_MAIN_REQUEST_MAPPING_VALUE, method = RequestMethod.GET)
    public ModelAndView mainPage() {
        modelAndView.addObject(COURSES_VAR_NAME, courseService.findAllCourses());
        modelAndView.setViewName(MAIN_PAGE_VIEW_NAME);

        return modelAndView;
    }

    @RequestMapping(value = USER_APPLICATION_COURSE_REQUEST_MAPPING_VALUE, method = RequestMethod.GET)
    public ModelAndView course(@PathVariable int courseId) {
        modelAndView.addObject(COURSE_VAR_NAME, courseService.findCourseById(courseId));
        modelAndView.setViewName(COURSE_VIEW_NAME);

        return modelAndView;
    }

    @RequestMapping(value = SEARCH_REQUEST_MAPPING_VALUE, method = RequestMethod.GET)
    public ModelAndView search(@RequestParam(COURSE_NAME_VAR_NAME) String courseName) {
        modelAndView.addObject(COURSES_VAR_NAME, courseService.findCoursesByNameFragment(courseName));
        modelAndView.setViewName(MAIN_PAGE_VIEW_NAME);

        return modelAndView;
    }
}
