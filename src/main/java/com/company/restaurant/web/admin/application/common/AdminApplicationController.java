package com.company.restaurant.web.admin.application.common;

import com.company.restaurant.model.Ingredient;
import com.company.restaurant.model.Portion;
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

    private static final String NEW_INGREDIENT_ID_VAR_NAME = "newIngredientId";
    private static final String NEW_INGREDIENT_NAME_VAR_NAME = "newIngredientName";
    private static final String NEW_PORTION_ID_VAR_NAME = "newPortionId";
    private static final String NEW_PORTION_NAME_VAR_NAME = "newPortionName";
    private static final String NEW_AMOUNT_VAR_NAME = "newAmount";

    private static final Integer NEW_INGREDIENT_ID_EMPTY_VALUE = -1;
    private static final String NEW_INGREDIENT_NAME_EMPTY_VALUE = "--- Select ingredient ---";
    private static final Integer NEW_PORTION_ID_EMPTY_VALUE = -1;
    private static final String NEW_PORTION_NAME_EMPTY_VALUE = "- portion -";
    private static final Float NEW_AMOUNT_EMPTY_VALUE = null;

    protected static final String SUBMIT_BUTTON_PAR_NAME = "submitButtonValue";
    protected static final String PORTION_ID_PAR_NAME = "portionId";

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

    protected void storeNewIngredientId(Integer ingredientId) {
        modelAndView.addObject(NEW_INGREDIENT_ID_VAR_NAME, ingredientId);

        Ingredient ingredient = warehouseService.findIngredientById(ingredientId);
        modelAndView.addObject(NEW_INGREDIENT_NAME_VAR_NAME,
                (ingredient == null) ? NEW_INGREDIENT_NAME_EMPTY_VALUE : ingredient.getName());
    }

    protected void clearNewIngredientId() {
        storeNewIngredientId(NEW_INGREDIENT_ID_EMPTY_VALUE);
    }

    protected void storeNewPortionId(Integer portionId) {
        modelAndView.addObject(NEW_PORTION_ID_VAR_NAME, portionId);

        Portion portion = warehouseService.findPortionById(portionId);
        modelAndView.addObject(NEW_PORTION_NAME_VAR_NAME,
                (portion == null) ? NEW_PORTION_NAME_EMPTY_VALUE : portion.getDescription());
    }

    protected void clearNewPortionId() {
        storeNewPortionId(NEW_PORTION_ID_EMPTY_VALUE);
    }

    protected void storeNewAmount(Float amount) {
        modelAndView.addObject(NEW_AMOUNT_VAR_NAME, amount);
    }

    protected void clearNewAmount() {
        storeNewAmount(NEW_AMOUNT_EMPTY_VALUE);
    }

    protected void clearNewIngredientRecord() {
        clearNewIngredientId();
        clearNewPortionId();
        clearNewAmount();
    }

    private void initIngredientsList() {
        modelAndView.addObject(INGREDIENTS_VAR_NAME, warehouseService.findAllIngredients());

        clearNewIngredientId();
    }

    private void initTableList() {
        modelAndView.addObject(TABLES_VAR_NAME, tableService.findAllTables());
    }

    private void initPortionList() {
        modelAndView.addObject(PORTIONS_VAR_NAME, warehouseService.findAllPortions());

        clearNewPortionId();
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
        clearNewAmount();
    }

    protected String DateToStringPresentation(Date date) {
        return DatetimeFormatter.DateToStringPresentation(date);
    }

    protected Date parseDateFromStringPresentation(String datePresentation) {
        return DatetimeFormatter.parseDateFromStringPresentation(datePresentation);
    }
}
