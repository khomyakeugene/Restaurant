package com.company.restaurant.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Yevhen on 02.07.2016.
 */
public class CookProperty {
    private Set<CookedCourse> cookedCourses = new HashSet<>();

    public Set<CookedCourse> getCookedCourses() {
        return cookedCourses;
    }

    public void setCookedCourses(Set<CookedCourse> cookedCourses) {
        this.cookedCourses = cookedCourses;
    }

    @Override
    public String toString() {
        return "CookProperty{" +
                "cookedCourses=" + cookedCourses +
                '}';
    }
}
