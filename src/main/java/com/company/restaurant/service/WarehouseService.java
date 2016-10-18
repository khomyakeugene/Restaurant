package com.company.restaurant.service;

import com.company.restaurant.model.Ingredient;
import com.company.restaurant.model.Portion;
import com.company.restaurant.model.Warehouse;

import java.util.List;

/**
 * Created by Yevhen on 17.06.2016.
 */
public interface WarehouseService {
    void addIngredientToWarehouse(int ingredientId, int portionId, Float amount);

    void addIngredientToWarehouse(Ingredient ingredient, Portion portion, Float amount);

    void takeIngredientFromWarehouse(int ingredientId, int portionId, Float amount);

    void takeIngredientFromWarehouse(Ingredient ingredient, Portion portion, Float amount);

    Warehouse findIngredientInWarehouse(Ingredient ingredient, Portion portion);

    void setAmountInWarehouse(Warehouse warehouse, Float amount);

    List<Warehouse> findIngredientInWarehouseById(int ingredientId);

    List<Warehouse> findAllWarehouseIngredients();

    List<Ingredient> findAllIngredients();

    Ingredient findIngredientById(int ingredientId);

    List<Portion> findAllPortions();

    Portion findPortionById(int portionId);

    void clearWarehouse();
}
