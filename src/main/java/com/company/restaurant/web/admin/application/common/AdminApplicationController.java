package com.company.restaurant.web.admin.application.common;

import com.company.restaurant.service.CourseService;
import com.company.restaurant.service.EmployeeService;
import com.company.restaurant.service.TableService;
import com.company.restaurant.service.WarehouseService;
import com.company.restaurant.web.common.CommonDataController;
import com.company.util.common.DatetimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * Created by Yevhen on 03.09.2016.
 */
public class AdminApplicationController extends CommonDataController {
    private static final String INGREDIENTS_VAR_NAME = "ingredients";
    private static final String TABLES_VAR_NAME = "tables";
    private static final String PORTIONS_VAR_NAME = "portions";
    private static final String JOB_POSITIONS_VAR_NAME = "jobPositions";
    private static final String COURSE_CATEGORIES_VAR_NAME = "courseCategories";
    protected static final String COURSES_VAR_NAME = "courses";

    protected static final String SUBMIT_BUTTON_PAR_NAME = "submitButtonValue";
    private static final String SUBMIT_BUTTON_SAVE_VALUE = "save";
    private static final String SUBMIT_BUTTON_DELETE_VALUE = "delete";
    private static final String SUBMIT_BUTTON_ADD_VALUE = "add";
    private static final String SUBMIT_BUTTON_SEARCH_VALUE = "search";

    protected EmployeeService employeeService;
    protected TableService tableService;
    protected WarehouseService warehouseService;
    protected CourseService courseService;

    static {
        ERROR_PAGE_VIEW_NAME = "admin-application/error";
    }

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Autowired
    public void setWarehouseService(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @Autowired
    public void setTableService(TableService tableService) {
        this.tableService = tableService;
    }

    @Autowired
    public void setCourseService(CourseService courseService) {
        this.courseService = courseService;
    }

    protected boolean isSubmitValue(String submitButtonValue, String value) {
        return submitButtonValue.toLowerCase().equals(value.toLowerCase());
    }

    protected boolean isSubmitSave(String submitButtonValue) {
        return isSubmitValue(submitButtonValue, SUBMIT_BUTTON_SAVE_VALUE);
    }

    protected boolean isSubmitDelete(String submitButtonValue) {
        return isSubmitValue(submitButtonValue, SUBMIT_BUTTON_DELETE_VALUE);
    }

    protected boolean isSubmitAdd(String submitButtonValue) {
        return isSubmitValue(submitButtonValue, SUBMIT_BUTTON_ADD_VALUE);
    }

    protected boolean isSubmitSearch(String submitButtonValue) {
        return submitButtonValue.toLowerCase().equals(SUBMIT_BUTTON_SEARCH_VALUE.toLowerCase());
    }

    private void initIngredientsList() {
        modelAndView.addObject(INGREDIENTS_VAR_NAME, warehouseService.findAllIngredients());
    }

    private void initTableList() {
        modelAndView.addObject(TABLES_VAR_NAME, tableService.findAllTables());
    }

    private void initPortionList() {
        modelAndView.addObject(PORTIONS_VAR_NAME, warehouseService.findAllPortions());
    }

    private void initJobPositionList() {
        modelAndView.addObject(JOB_POSITIONS_VAR_NAME, employeeService.findAllJobPositions());
    }

    private void initCourseCategoryList() {
        modelAndView.addObject(COURSE_CATEGORIES_VAR_NAME, courseService.findAllCourseCategories());
    }
    private void initDictionaryLists() {
        initCourseCategoryList();
        initIngredientsList();
        initJobPositionList();
        initPortionList();
        initTableList();
    }

    @Override
    protected void initModelAndViewData() {
        super.initModelAndViewData();

        initDictionaryLists();
    }

    protected String DateToStringPresentation(Date date) {
        return DatetimeFormatter.DateToStringPresentation(date);
    }

    protected Date parseDateFromStringPresentation(String datePresentation) {
        return DatetimeFormatter.parseDateFromStringPresentation(datePresentation);
    }
}
