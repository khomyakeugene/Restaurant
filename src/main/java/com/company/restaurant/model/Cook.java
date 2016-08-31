package com.company.restaurant.model;

import java.util.Set;

/**
 * Created by Yevhen on 02.07.2016.
 */
public class Cook extends Employee {
    private CookProperty cookProperty = new CookProperty();

    public CookProperty getCookProperty() {
        return cookProperty;
    }

    public Set<CookedCourse> getCookedCourses() {
        return cookProperty.getCookedCourses();
    }

    public void setCookedCourses(Set<CookedCourse> cookedCourses) {
        cookProperty.setCookedCourses(cookedCourses);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cook)) return false;
        if (!super.equals(o)) return false;

        Cook cook = (Cook) o;

        return cookProperty != null ? cookProperty.equals(cook.cookProperty) : cook.cookProperty == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (cookProperty != null ? cookProperty.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Cook{" +
                super.toString() + "\n" +
                "cookProperty=" + cookProperty +
                '}';
    }
}
