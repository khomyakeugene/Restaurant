package com.company.restaurant.dao.hibernate;

import com.company.restaurant.dao.CookedCourseDao;
import com.company.restaurant.dao.hibernate.common.HDaoEntity;
import com.company.restaurant.model.CookedCourse;
import com.company.restaurant.model.Course;
import com.company.restaurant.model.Employee;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Created by Yevhen on 14.06.2016.
 */
public class HCookedCourseDao extends HDaoEntity<CookedCourse> implements CookedCourseDao{
    private static final String COOK_DATETIME_ATTRIBUTE_NAME = "cookDatetime";

    @Transactional
    @Override
    public CookedCourse addCookedCourse(Course course, Employee employee, Float weight) {
        CookedCourse cookedCourse = new CookedCourse();

        cookedCourse.setCookDatetime(new Timestamp(new Date().getTime()));
        cookedCourse.setWeight(weight);
        cookedCourse.setCourse(course);
        cookedCourse.setEmployee(employee);

        return save(cookedCourse);
    }

    @Transactional
    @Override
    public void delCookedCourse(CookedCourse cookedCourse) {
        delete(cookedCourse);
    }

    @Transactional
    @Override
    public List<CookedCourse> findAllCookedCourses() {
        return findAllObjects();
    }
}
