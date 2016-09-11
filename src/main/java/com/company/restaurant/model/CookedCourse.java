package com.company.restaurant.model;

import com.company.restaurant.model.common.SimpleObject;

import java.sql.Timestamp;

/**
 * Created by Yevhen on 14.06.2016.
 */
public class CookedCourse extends SimpleObject {
    private Timestamp cookDatetime;
    private Float weight;
    private Course course = new Course();
    private Employee employee = new Employee();

    public int getCookedCourseId() {
        return getId();
    }

    public void setCookedCourseId(int cookedCourseId) {
        setId(cookedCourseId);
    }

    public Timestamp getCookDatetime() {
        return cookDatetime;
    }

    public void setCookDatetime(Timestamp cookDatetime) {
        this.cookDatetime = cookDatetime;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CookedCourse)) return false;
        if (!super.equals(o)) return false;

        CookedCourse that = (CookedCourse) o;

        return cookDatetime != null ? cookDatetime.equals(that.cookDatetime) :
                that.cookDatetime == null && (weight != null ? weight.equals(that.weight) :
                        that.weight == null && (course != null ? course.equals(that.course) :
                                that.course == null && (employee != null ? employee.equals(that.employee) :
                                        that.employee == null)));

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (cookDatetime != null ? cookDatetime.hashCode() : 0);
        result = 31 * result + (weight != null ? weight.hashCode() : 0);
        result = 31 * result + (course != null ? course.hashCode() : 0);
        result = 31 * result + (employee != null ? employee.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CookedCourse{" +
                "cookedCourseId=" + getCookedCourseId() +
                ", cookDatetime=" + cookDatetime +
                ", weight=" + weight +
                ", course=" + course +
                '}';
    }
}
