package com.company.restaurant.model;

import com.company.restaurant.model.common.PhotoHolderObject;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Yevhen on 19.05.2016.
 */
public class Course extends PhotoHolderObject implements Serializable {
    private String name;
    private Float weight;
    private Float cost;
    private CourseCategory courseCategory = new CourseCategory();
    private Set<CourseIngredient> courseIngredients = new HashSet<>();

    public int getCourseId() {
        return getId();
    }

    public void setCourseId(int courseId) {
        setId(courseId);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public Float getCost() {
        return cost;
    }

    public void setCost(Float cost) {
        this.cost = cost;
    }

    public CourseCategory getCourseCategory() {
        return courseCategory;
    }

    public void setCourseCategory(CourseCategory courseCategory) {
        this.courseCategory = courseCategory;
    }

    public Set<CourseIngredient> getCourseIngredients() {
        return courseIngredients;
    }

    public void setCourseIngredients(Set<CourseIngredient> courseIngredients) {
        this.courseIngredients = courseIngredients;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Course)) return false;

        Course course = (Course) o;

        return getCourseId() == course.getCourseId() && (name != null ?
                name.equals(course.name) : course.name == null && (weight != null ?
                weight.equals(course.weight) : course.weight == null && (cost != null ?
                cost.equals(course.cost) : course.cost == null && (courseCategory != null ?
                courseCategory.equals(course.courseCategory) : course.courseCategory == null))));

    }

    @Override
    public int hashCode() {
        int result = getCourseId();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (weight != null ? weight.hashCode() : 0);
        result = 31 * result + (cost != null ? cost.hashCode() : 0);
        result = 31 * result + (courseCategory != null ? courseCategory.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseId=" + getCourseId() +
                ", name='" + name + '\'' +
                ", weight=" + weight +
                ", cost=" + cost +
                ", courseCategory=" + courseCategory +
                '}';
    }
}
