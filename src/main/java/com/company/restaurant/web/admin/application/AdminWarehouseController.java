package com.company.restaurant.web.admin.application;

import com.company.restaurant.model.Ingredient;
import com.company.restaurant.model.Portion;
import com.company.restaurant.model.Warehouse;
import com.company.restaurant.web.admin.application.common.AdminCRUDController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.text.Collator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * Created by Yevhen on 04.09.2016.
 */
@Controller
public class AdminWarehouseController extends AdminCRUDController<Warehouse> {
    private static final String ADMIN_WAREHOUSE_PAGE_VIEW_NAME = "admin-application/warehouse/admin-warehouse-page";
    private static final String ADMIN_WAREHOUSE_REQUEST_MAPPING_VALUE = "/admin-warehouse";
    private static final String ADMIN_WAREHOUSE_ACTION_REQUEST_MAPPING_VALUE = "/warehouse-action";
    private static final String ADMIN_DELETE_WAREHOUSE_INGREDIENT_REQUEST_MAPPING_VALUE =
            "/warehouse/delete-warehouse-ingredient/{ingredientId}/{portionId}";
    private static final String ADMIN_EDIT_WAREHOUSE_INGREDIENT_REQUEST_MAPPING_VALUE =
            "/warehouse/edit-warehouse-ingredient/{ingredientId}/{portionId}";

    private static final String WAREHOUSE_CONTENT_VAR_NAME = "warehouseContent";
    private static final String WAREHOUSE_INGREDIENTS_VAR_NAME = "warehouseIngredients";
    private static final String FILTER_INGREDIENT_ID_VAR_NAME = "filterIngredientId";
    private static final String FILTER_INGREDIENT_NAME_VAR_NAME = "filterIngredientName";

    private static final String INGREDIENT_ID_PAR_NAME = "ingredientId";
    private static final String NEW_INGREDIENT_ID_PAR_NAME = "newIngredientId";
    private static final String NEW_AMOUNT_PAR_NAME = "newAmount";
    private static final String AMOUNT_PAR_NAME = "amount";

    private static final int NO_INGREDIENT_FILTER_VALUE = -1;

    private int filterIngredientId = NO_INGREDIENT_FILTER_VALUE;

    private void setFilterIngredientId(int filterIngredientId) {
        this.filterIngredientId = filterIngredientId;

        modelAndView.addObject(FILTER_INGREDIENT_ID_VAR_NAME, filterIngredientId);

        Ingredient ingredient = warehouseService.findIngredientById(filterIngredientId);
        modelAndView.addObject(FILTER_INGREDIENT_NAME_VAR_NAME, (ingredient == null) ? null : ingredient.getName());
    }

    private void clearFilterIngredientId() {
        setFilterIngredientId(NO_INGREDIENT_FILTER_VALUE);
    }

    protected void clearNewIngredientRecord() {
        clearNewIngredientId();
        clearNewPortionId();
        clearNewAmount();
    }

    private void initWarehouseIngredientList() {
        List<Warehouse> allWarehouseIngredients = warehouseService.findAllWarehouseIngredients();

        modelAndView.addObject(WAREHOUSE_INGREDIENTS_VAR_NAME, warehouseService.findAllIngredients().stream().
                filter(ingredient -> allWarehouseIngredients.stream().
                        filter(warehouse -> warehouse.getIngredient().equals(ingredient)).
                        findAny().isPresent()).collect(Collectors.toList()));
    }

    private ModelAndView returnToWarehouseContentPage() {
        // Point that there should be not ingredient amount editing process at all
        stopIngredientAmountEditing();

        return new ModelAndView(REDIRECT_PREFIX + ADMIN_WAREHOUSE_REQUEST_MAPPING_VALUE);
    }

    private Warehouse addWarehouseIngredient(int ingredientId, int portionId, Float amount) {
        // Important for the possible next attempt to add new warehouse record
        storeNewIngredientId(ingredientId);
        storeNewPortionId(portionId);
        storeNewAmount(amount);

        Ingredient ingredient = warehouseService.findIngredientById(ingredientId);
        Portion portion = warehouseService.findPortionById(portionId);
        warehouseService.addIngredientToWarehouse(ingredient, portion, amount);

        // To show full warehouse content list
        clearFilterIngredientId();

        return warehouseService.findIngredientInWarehouse(ingredient, portion);
    }

    private void saveWarehouseIngredientAmount(Float amount) {
        Warehouse warehouse = getCurrentObject();
        warehouse.setAmount(amount); // important for presentation value by JSP-view

        warehouseService.setAmountInWarehouse(warehouse, amount);
    }

    private List<Warehouse> sortWarehouseContent(List<Warehouse> warehouseContent) {
        Collator collator = Collator.getInstance(Locale.US);

        return warehouseContent.stream().sorted((w1, w2) -> {
            int result = collator.compare(w1.getIngredient().getName(), w2.getIngredient().getName());
            return (result == 0) ?
                    collator.compare(w1.getPortion().getDescription(), w2.getPortion().getDescription()) : result;
        }).collect(Collectors.toList());
    }

    private void setWarehouseContent(List<Warehouse> warehouseContent) {
        modelAndView.addObject(WAREHOUSE_CONTENT_VAR_NAME, sortWarehouseContent(warehouseContent));
    }

    private void initWarehouseContentList() {
        setWarehouseContent((filterIngredientId > 0) ?
                warehouseService.findIngredientInWarehouseById(filterIngredientId) :
                warehouseService.findAllWarehouseIngredients());
    }

    private boolean isIngredientAmountEditing() {
        Warehouse warehouse = getCurrentObject();

        return (warehouse != null) && (warehouse.getIngredient() != null);
    }

    private void stopIngredientAmountEditing() {
        clearCurrentObject();
    }

    @RequestMapping(value = ADMIN_WAREHOUSE_REQUEST_MAPPING_VALUE, method = RequestMethod.GET)
    public ModelAndView warehouseContentPage() {
        clearErrorMessage();

        initWarehouseContentList();
        initWarehouseIngredientList();
        clearNewIngredientRecord();

        modelAndView.setViewName(ADMIN_WAREHOUSE_PAGE_VIEW_NAME);

        return modelAndView;
    }

    @RequestMapping(value = ADMIN_WAREHOUSE_ACTION_REQUEST_MAPPING_VALUE, method = RequestMethod.POST)
    public ModelAndView warehouseAction(@RequestParam(INGREDIENT_ID_PAR_NAME) int ingredientId,
                                        @RequestParam(NEW_INGREDIENT_ID_PAR_NAME) int newIngredientId,
                                        @RequestParam(PORTION_ID_PAR_NAME) int portionId,
                                        @RequestParam(value = AMOUNT_PAR_NAME, required = false) Float amount,
                                        @RequestParam(NEW_AMOUNT_PAR_NAME) Float newAmount,
                                        @RequestParam(SUBMIT_BUTTON_PAR_NAME) String submitButtonValue) {
        if (isSubmitSave(submitButtonValue)) {
            saveWarehouseIngredientAmount(amount);
        } else {
            // If ingredient amount editing process is active and <amount> is entered - finish it confirming changes
            if (isIngredientAmountEditing() && amount != null) {
                saveWarehouseIngredientAmount(amount);
            }
            // Then try to perform another action
            if (isSubmitSearch(submitButtonValue)) {
                setFilterIngredientId(ingredientId);
            } else if (isSubmitAdd(submitButtonValue)) {
                addWarehouseIngredient(newIngredientId, portionId, newAmount);
            }
        }

        return returnToWarehouseContentPage();
    }

    @RequestMapping(value = ADMIN_DELETE_WAREHOUSE_INGREDIENT_REQUEST_MAPPING_VALUE, method = RequestMethod.GET)
    public ModelAndView deleteWarehouseIngredient(@PathVariable int ingredientId,
                                                  @PathVariable int portionId) {
        warehouseService.takeIngredientFromWarehouse(ingredientId, portionId, null);

        return returnToWarehouseContentPage();
    }

    @RequestMapping(value = ADMIN_EDIT_WAREHOUSE_INGREDIENT_REQUEST_MAPPING_VALUE, method = RequestMethod.GET)
    public ModelAndView editWarehouseIngredient(@PathVariable int ingredientId,
                                                @PathVariable int portionId) {
        setCurrentObject(warehouseService.findIngredientInWarehouse(warehouseService.findIngredientById(ingredientId),
                warehouseService.findPortionById(portionId)));

        return modelAndView;
    }
}
