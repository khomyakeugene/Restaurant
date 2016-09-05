package com.company.restaurant.model;

import com.company.restaurant.model.common.SimpleDic;

import java.io.Serializable;

/**
 * Created by Yevhen on 24.05.2016.
 */
public class Ingredient extends SimpleDic implements Serializable {
    public int getIngredientId() {
        return getId();
    }

    public void setIngredientId(int ingredientId) {
        setId(ingredientId);
    }
}
