package com.company.restaurant.web.admin.application.common;

import com.company.restaurant.service.CourseService;
import com.company.restaurant.service.EmployeeService;
import com.company.restaurant.service.TableService;
import com.company.restaurant.service.WarehouseService;
import com.company.restaurant.web.common.CommonDataController;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Yevhen on 03.09.2016.
 */
public class AdminApplicationController extends CommonDataController {
    private static final String DEFAULT_DATE_FORMAT = "dd-MM-yyyy";

    private static final String INGREDIENTS_VAR_NAME = "ingredients";
    private static final String TABLES_VAR_NAME = "tables";
    private static final String PORTIONS_VAR_NAME = "portions";
    private static final String JOB_POSITIONS_VAR_NAME = "jobPositions";
    private static final String COURSE_CATEGORIES_VAR_NAME = "courseCategories";
    protected static final String COURSES_VAR_NAME = "courses";

    protected static final String SUBMIT_BUTTON_VAR_NAME = "submitButtonValue";
    private static final String SUBMIT_BUTTON_SAVE_VALUE = "save";
    private static final String SUBMIT_BUTTON_DELETE_VALUE = "delete";
    private static final String SUBMIT_BUTTON_ADD_VALUE = "add";
    private static final String SUBMIT_BUTTON_SEARCH_VALUE = "search";

    private SimpleDateFormat defaultDateFormat = new SimpleDateFormat(DEFAULT_DATE_FORMAT);

    protected EmployeeService employeeService;
    private TableService tableService;
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

    protected boolean isSubmitSave(String submitButtonValue) {
        return submitButtonValue.toLowerCase().equals(SUBMIT_BUTTON_SAVE_VALUE.toLowerCase());
    }

    protected boolean isSubmitDelete(String submitButtonValue) {
        return submitButtonValue.toLowerCase().equals(SUBMIT_BUTTON_DELETE_VALUE.toLowerCase());
    }

    protected boolean isSubmitAdd(String submitButtonValue) {
        return submitButtonValue.toLowerCase().equals(SUBMIT_BUTTON_ADD_VALUE.toLowerCase());
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

    protected String DateToDefaultStringPresentation(Date date) {
        return defaultDateFormat.format(date);
    }

    protected Date parseDateFromDefaultStringPresentation(String datePresentation) {
        try {
            return (datePresentation == null || datePresentation.trim().isEmpty()) ? null :
                    defaultDateFormat.parse(datePresentation);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
