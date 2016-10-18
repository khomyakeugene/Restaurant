package com.company.restaurant.dao;

import com.company.restaurant.model.CommonData;

import java.util.List;

/**
 * Created by Yevhen on 04.08.2016.
 */
public interface CommonDataDao {
    CommonData addCommonData(CommonData commonData);

    void updCommonDataValue(int commonDataId, String value);

    void updCommonDataValue(String name, String value);

    void updCommonDataImage(String name, byte[] image);

    void delCommonData(String name);

    List<CommonData> findAllCommonData();

    CommonData findCommonDataById(int commonDataId);

    CommonData findCommonDataByName(String name);

    String getCommonDataValue(int commonDataId);

    String  getCommonDataValue(String name);

    byte[] getCommonDataImage(String name);
}
