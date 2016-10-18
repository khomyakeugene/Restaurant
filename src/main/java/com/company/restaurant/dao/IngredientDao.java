package com.company.restaurant.dao;

import com.company.restaurant.model.Ingredient;

import java.util.List;

/**
 * Created by Yevhen on 24.05.2016.
 */
public interface IngredientDao {
    List<Ingredient> findAllIngredients();

    Ingredient findIngredientById(int ingredientId);

    Ingredient findIngredientByName(String name);
}
