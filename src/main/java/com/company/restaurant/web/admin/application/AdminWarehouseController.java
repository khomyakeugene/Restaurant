package com.company.restaurant.web.admin.application;

import com.company.restaurant.model.Ingredient;
import com.company.restaurant.model.Portion;
import com.company.restaurant.model.Warehouse;
import com.company.restaurant.web.admin.application.common.AdminCRUDController;
import com.company.util.exception.DataIntegrityException;
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

    private static final String WAREHOUSE_CONTENT_VAR_NAME = "warehouseContent";
    private static final String WAREHOUSE_INGREDIENTS_VAR_NAME = "warehouseIngredients";

    private static final String INGREDIENT_ID_PAR_NAME = "ingredientId";
    private static final String NEW_INGREDIENT_ID_PAR_NAME = "newIngredientId";
    private static final String AMOUNT_PAR_NAME = "amount";

    private void clearNewWarehouseRecord() {
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
        return new ModelAndView(REDIRECT_PREFIX + ADMIN_WAREHOUSE_REQUEST_MAPPING_VALUE);
    }

    private Warehouse addWarehouseIngredient(int ingredientId, int portionId, Float amount) {
        // Important for the possible next attempt to add new warehouse record
        storeNewIngredientId(ingredientId);
        storeNewPortionId(portionId);
        storeNewAmount(amount);

        Ingredient ingredient = warehouseService.findIngredientById(ingredientId);
        if (ingredient == null) {
            throw new DataIntegrityException(PLEASE_SELECT_AN_INGREDIENT_MSG);
        }
        Portion portion = warehouseService.findPortionById(portionId);
        if (portion == null) {
            throw new DataIntegrityException(PLEASE_SELECT_A_MEASURE_MSG);
        }
        if (amount == null) {
            throw new DataIntegrityException(PLEASE_ENTER_AMOUNT_MSG);
        }
        warehouseService.addIngredientToWarehouse(ingredient, portion, amount);

        return warehouseService.findIngredientInWarehouse(ingredient, portion);
    }

    private void initWarehouseContentList() {
        Collator uaCollator = Collator.getInstance(Locale.US);

        modelAndView.addObject(WAREHOUSE_CONTENT_VAR_NAME,
                warehouseService.findAllWarehouseIngredients().stream().
                        sorted((w1, w2) -> uaCollator.compare(w1.getIngredient().getName(),
                                w2.getIngredient().getName())).collect(Collectors.toList()));
    }

    @RequestMapping(value = ADMIN_WAREHOUSE_REQUEST_MAPPING_VALUE, method = RequestMethod.GET)
    public ModelAndView warehouseContentPage() {
        clearErrorMessage();

        initWarehouseContentList();
        initWarehouseIngredientList();
        clearNewWarehouseRecord();

        modelAndView.setViewName(ADMIN_WAREHOUSE_PAGE_VIEW_NAME);

        return modelAndView;
    }

    @RequestMapping(value = ADMIN_WAREHOUSE_ACTION_REQUEST_MAPPING_VALUE, method = RequestMethod.POST)
    public ModelAndView searchIngredient(@RequestParam(INGREDIENT_ID_PAR_NAME) int ingredientId,
                                         @RequestParam(NEW_INGREDIENT_ID_PAR_NAME) int newIngredientId,
                                         @RequestParam(PORTION_ID_PAR_NAME) int portionId,
                                         @RequestParam(AMOUNT_PAR_NAME) Float amount,
                                         @RequestParam(SUBMIT_BUTTON_PAR_NAME) String submitButtonValue) {
        clearErrorMessage();

        if (isSubmitSearch(submitButtonValue)) {
            // Filter the data
            modelAndView.addObject(WAREHOUSE_CONTENT_VAR_NAME,
                    (ingredientId > 0) ? warehouseService.findIngredientInWarehouseById(ingredientId) :
                            warehouseService.findAllWarehouseIngredients());
        } else if (isSubmitAdd(submitButtonValue)) {
            addWarehouseIngredient(newIngredientId, portionId, amount);
        }

        return isSubmitSearch(submitButtonValue) ? modelAndView : returnToWarehouseContentPage();
    }

    @RequestMapping(value = ADMIN_DELETE_WAREHOUSE_INGREDIENT_REQUEST_MAPPING_VALUE, method = RequestMethod.GET)
    public ModelAndView deleteWarehouseIngredient(@PathVariable int ingredientId,
                                                  @PathVariable int portionId) {
        warehouseService.takeIngredientFromWarehouse(ingredientId, portionId, null);

        return returnToWarehouseContentPage();
    }

}
