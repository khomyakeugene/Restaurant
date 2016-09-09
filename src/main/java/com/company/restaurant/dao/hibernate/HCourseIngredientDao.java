package com.company.restaurant.dao.hibernate;

import com.company.restaurant.dao.CourseDao;
import com.company.restaurant.dao.CourseIngredientDao;
import com.company.restaurant.dao.IngredientDao;
import com.company.restaurant.dao.PortionDao;
import com.company.restaurant.dao.hibernate.common.HDaoEntity;
import com.company.restaurant.model.Course;
import com.company.restaurant.model.CourseIngredient;
import com.company.restaurant.model.Ingredient;
import com.company.restaurant.model.Portion;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

/**
 * Created by Yevhen on 04.07.2016.
 */
public class HCourseIngredientDao extends HDaoEntity<CourseIngredient> implements CourseIngredientDao {
    private static final String COURSE_ATTRIBUTE_NAME = "course";

    private CourseDao courseDao;
    private IngredientDao ingredientDao;
    private PortionDao portionDao;

    public void setCourseDao(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    public void setIngredientDao(IngredientDao ingredientDao) {
        this.ingredientDao = ingredientDao;
    }

    public void setPortionDao(PortionDao portionDao) {
        this.portionDao = portionDao;
    }

    @Transactional
    @Override
    public Set<CourseIngredient> findCourseIngredients(Course course) {
        return findObjectSetByAttributeValue(COURSE_ATTRIBUTE_NAME, course);
    }

    @Transactional
    @Override
    public CourseIngredient addCourseIngredient(Course course, Ingredient ingredient, Portion portion, Float amount) {
        CourseIngredient courseIngredient = new CourseIngredient();
        courseIngredient.setCourse(course);
        courseIngredient.setIngredient(ingredient);
        courseIngredient.setPortion(portion);
        courseIngredient.setAmount(amount);

        return save(courseIngredient);
    }

    @Transactional
    @Override
    public void delCourseIngredient(Course course, Ingredient ingredient) {
        CourseIngredient courseIngredient = new CourseIngredient();
        courseIngredient.setCourse(course);
        courseIngredient.setIngredient(ingredient);

        // Have not precisely investigated this issue, but witout <Portion> there could be generated exception such as
        // "org.hibernate.TransientObjectException: object references an unsaved transient instance -
        // save the transient instance beforeQuery flushing: com.company.restaurant.model.Portion"
        // And again, cannot understand why, but
        //       try { delete(courseIngredient); } catch (TransientObjectException e) { ... }
        // does not work
        // So, try to init with "random" <Portion> and just then try to delete
        courseIngredient.setPortion(portionDao.findAllPortions().get(0));

        delete(courseIngredient);
    }

    @Transactional
    @Override
    public CourseIngredient addCourseIngredient(int courseId, int ingredientId, int portionId, Float amount) {
        return addCourseIngredient(courseDao.findCourseById(courseId), ingredientDao.findIngredientById(ingredientId),
                portionDao.findPortionById(portionId), amount);
    }

    @Transactional
    @Override
    public void delCourseIngredient(int courseId, int ingredientId) {
        delCourseIngredient(courseDao.findCourseById(courseId), ingredientDao.findIngredientById(ingredientId));
    }
}
