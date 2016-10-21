package com.company.restaurant.dao.hibernate;

import com.company.restaurant.dao.CourseIngredientDao;
import com.company.restaurant.dao.PortionDao;
import com.company.restaurant.dao.hibernate.common.HDaoEntity;
import com.company.restaurant.model.Course;
import com.company.restaurant.model.CourseIngredient;
import com.company.restaurant.model.Ingredient;
import com.company.restaurant.model.Portion;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Yevhen on 04.07.2016.
 */
public class HCourseIngredientDao extends HDaoEntity<CourseIngredient> implements CourseIngredientDao {
    private PortionDao portionDao;

    public void setPortionDao(PortionDao portionDao) {
        this.portionDao = portionDao;
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

        // Have not precisely investigated this issue, but without <Portion> there could be generated exception such as
        // "org.hibernate.TransientObjectException: object references an unsaved transient instance -
        // save the transient instance beforeQuery flushing: com.company.restaurant.model.Portion"
        // And again, cannot understand why, but
        //       try { delete(courseIngredient); } catch (TransientObjectException e) { ... }
        // does not work
        // So, try to init with "random" <Portion> and just then try to delete
        courseIngredient.setPortion(portionDao.findAllPortions().get(0));

        delete(courseIngredient);
    }

}
