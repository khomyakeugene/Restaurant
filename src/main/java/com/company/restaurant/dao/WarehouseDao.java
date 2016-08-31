package com.company.restaurant.dao;

import com.company.restaurant.model.Ingredient;
import com.company.restaurant.model.Portion;
import com.company.restaurant.model.Warehouse;

import java.util.List;

/**
 * Created by Yevhen on 16.06.2016.
 */
public interface WarehouseDao {
    void addIngredientToWarehouse(Ingredient ingredient, Portion portion, float amount);

    void takeIngredientFromWarehouse(Ingredient ingredient, Portion portion, float amount);

    Warehouse findIngredientInWarehouse(Ingredient ingredient, Portion portion);

    List<Warehouse> findIngredientInWarehouseByName(String name);

    List<Warehouse> findIngredientInWarehouseById(int ingredientId);

    List<Warehouse> findAllWarehouseIngredients();

    List<Warehouse> findAllElapsingWarehouseIngredients(float limit);
}
