package com.company.restaurant.model;

import java.io.Serializable;

/**
 * Created by Yevhen on 04.07.2016.
 */
public class CourseIngredient implements Serializable {
    private Course course = new Course();
    private Ingredient ingredient = new Ingredient();
    private Portion portion = new Portion();
    private Float amount;

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
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

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CourseIngredient)) return false;

        CourseIngredient that = (CourseIngredient) o;

        return course != null ? course.equals(that.course) :
                that.course == null && (ingredient != null ? ingredient.equals(that.ingredient) :
                        that.ingredient == null && (portion != null ? portion.equals(that.portion) :
                                that.portion == null && (amount != null ? amount.equals(that.amount) :
                                        that.amount == null)));

    }

    @Override
    public int hashCode() {
        int result = course != null ? course.hashCode() : 0;
        result = 31 * result + (ingredient != null ? ingredient.hashCode() : 0);
        result = 31 * result + (portion != null ? portion.hashCode() : 0);
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CourseIngredient{" +
                "course=" + course +
                ", ingredient=" + ingredient +
                ", portion=" + portion +
                ", amount=" + amount +
                '}';
    }
}
