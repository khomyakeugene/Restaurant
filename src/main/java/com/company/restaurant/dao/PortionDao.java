package com.company.restaurant.dao;

import com.company.restaurant.model.Portion;

import java.util.List;

/**
 * Created by Yevhen on 24.05.2016.
 */
public interface PortionDao {
    Portion addPortion(Portion portion);

    void delPortion(Portion portion);

    List<Portion> findAllPortions();

    Portion findPortionById(int portionId);

    Portion findPortionByDescription(String description);
}
