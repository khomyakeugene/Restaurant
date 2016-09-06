package com.company.restaurant.web.admin.application;

import com.company.restaurant.service.CourseService;
import com.company.restaurant.web.admin.application.proto.AdminApplicationController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    private static final String COURSES_VAR_NAME = "courses";
    private static final String COURSE_VAR_NAME = "course";

    private CourseService courseService;

    @Autowired
    public void setCourseService(CourseService courseService) {
        this.courseService = courseService;
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


}
