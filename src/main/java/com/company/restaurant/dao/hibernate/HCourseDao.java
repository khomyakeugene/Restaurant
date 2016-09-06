package com.company.restaurant.dao.hibernate;

import com.company.restaurant.dao.CourseDao;
import com.company.restaurant.dao.hibernate.common.HDaoEntity;
import com.company.restaurant.model.Course;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Yevhen on 11.06.2016.
 */
public class HCourseDao extends HDaoEntity<Course> implements CourseDao {
    @Transactional
    @Override
    public Course addCourse(Course course) {
        return save(course);
    }

    @Transactional
    @Override
    public Course updCourse(Course course) {
        return saveOrUpdate(course);
    }

    @Transactional
    @Override
    public void updCoursePhoto(int courseId, byte[] photo) {
        Course course = findCourseById(courseId);
        if (course != null) {
            course.setPhoto(photo);
            saveOrUpdate(course);
        }
    }

    @Transactional
    @Override
    public void delCourse(Course course) {
        delete(course);
    }

    @Transactional
    @Override
    public void delCourse(int courseId) {
        delete(courseId);
    }

    @Transactional
    @Override
    public void delCourse(String name) {
        delete(name);
    }

    @Transactional
    @Override
    public Course findCourseByName(String name) {
        return findObjectByName(name);
    }

    @Transactional
    @Override
    public Course findCourseById(int courseId) {
        return findObjectById(courseId);
    }

    @Transactional
    @Override
    public List<Course> findAllCourses() {
        return findAllObjects();
    }

    @Transactional
    @Override
    public List<Course> findCoursesByNameFragment(String nameFragment) {
        return findObjectsByNameFragment(nameFragment);
    }

    @Transactional
    @Override
    public byte[] getCoursePhoto(int courseId) {
        byte[] result = null;

        Course course = findCourseById(courseId);
        if (course != null) {
            result = course.getPhoto();
        }

        return result;
    }
}
