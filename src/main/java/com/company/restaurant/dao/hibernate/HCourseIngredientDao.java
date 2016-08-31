package com.company.restaurant.dao.hibernate;

import com.company.restaurant.dao.CourseIngredientDao;
import com.company.restaurant.dao.hibernate.proto.HDaoEntity;
import com.company.restaurant.model.Course;
import com.company.restaurant.model.CourseIngredient;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

/**
 * Created by Yevhen on 04.07.2016.
 */
public class HCourseIngredientDao extends HDaoEntity<CourseIngredient> implements CourseIngredientDao {
    private static final String COURSE_ATTRIBUTE_NAME = "course";

    @Transactional
    @Override
    public Set<CourseIngredient> findCourseIngredients(Course course) {
        return findObjectSetByAttributeValue(COURSE_ATTRIBUTE_NAME, course);
    }
}
