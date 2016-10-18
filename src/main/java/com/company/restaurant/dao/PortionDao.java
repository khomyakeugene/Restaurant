package com.company.restaurant.dao;

import com.company.restaurant.model.Portion;

import java.util.List;

/**
 * Created by Yevhen on 24.05.2016.
 */
public interface PortionDao {
    List<Portion> findAllPortions();

    Portion findPortionById(int portionId);
}
