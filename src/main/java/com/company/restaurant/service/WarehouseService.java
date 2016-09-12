package com.company.restaurant.service;

import com.company.restaurant.model.Ingredient;
import com.company.restaurant.model.Portion;
import com.company.restaurant.model.Warehouse;

import java.util.List;

/**
 * Created by Yevhen on 17.06.2016.
 */
public interface WarehouseService {
    void addIngredientToWarehouse(Ingredient ingredient, Portion portion, Float amount);

    void takeIngredientFromWarehouse(Ingredient ingredient, Portion portion, Float amount);

    Warehouse findIngredientInWarehouse(Ingredient ingredient, Portion portion);

    List<Warehouse> findIngredientInWarehouseByName(String name);

    List<Warehouse> findIngredientInWarehouseById(int ingredientId);

    List<Warehouse> findAllWarehouseIngredients();

    List<Warehouse> findAllElapsingWarehouseIngredients(float limit);

    List<Ingredient> findAllIngredients();

    Ingredient findIngredientById(int ingredientId);

    Ingredient findIngredientByName(String name);

    List<Portion> findAllPortions();

    Portion findPortionById(int portionId);

    Portion findPortionByDescription(String description);

    void clearWarehouse();
}
