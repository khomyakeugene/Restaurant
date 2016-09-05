package com.company.restaurant.model;

import com.company.restaurant.model.common.FloatLinkObject;

/**
 * Created by Yevhen on 15.06.2016.
 */
public class Warehouse extends FloatLinkObject {
    private Ingredient ingredient = new Ingredient();
    private Portion portion = new Portion();

    public Float getAmount() {
        return getFloatLinkData();
    }

    public void setAmount(Float amount) {
        setFloatLinkData(amount);
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public Portion getPortion() {
        return portion;
    }

    public void setPortion(Portion portion) {
        this.portion = portion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Warehouse)) return false;
        if (!super.equals(o)) return false;

        Warehouse warehouse = (Warehouse) o;

        return ingredient != null ? ingredient.equals(warehouse.ingredient) :
                warehouse.ingredient == null && (portion != null ?
                        portion.equals(warehouse.portion) : warehouse.portion == null);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (ingredient != null ? ingredient.hashCode() : 0);
        result = 31 * result + (portion != null ? portion.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Warehouse{" +
                "ingredient=" + ingredient +
                ", portion=" + portion +
                '}';
    }
}
