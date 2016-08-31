package com.company.restaurant.dao;

import com.company.restaurant.model.JobPosition;

import java.util.List;

/**
 * Created by Yevhen on 21.05.2016.
 */
public interface JobPositionDao {
    JobPosition addJobPosition(String name);

    void delJobPosition(String name);

    JobPosition findJobPositionByName(String name);

    JobPosition findJobPositionById(int jobPositionId);

    List<JobPosition> findAllJobPositions();
}
