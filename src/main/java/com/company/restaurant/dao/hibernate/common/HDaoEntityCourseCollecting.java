package com.company.restaurant.dao.hibernate.common;

import com.company.restaurant.model.Course;
import com.company.restaurant.model.common.CourseCollecting;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

/**
 * Created by Yevhen on 28.06.2016.
 */
public abstract class HDaoEntityCourseCollecting<T extends CourseCollecting> extends HDaoEntity<T> {
    @Transactional
    protected Collection<Course> findCourses(T object) {
        getCurrentSession().refresh(object);

        return object.getCourses();
    }

    @Transactional
    protected Course findCourseByCourseId(T object, int courseId) {
        Optional<Course> courseOptional = findCourses(object).stream().filter(c ->
                (c.getCourseId() == courseId)).findFirst();

        return courseOptional.isPresent() ? courseOptional.get() : null;
    }
}
