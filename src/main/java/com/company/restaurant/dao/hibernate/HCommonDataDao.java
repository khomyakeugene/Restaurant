package com.company.restaurant.dao.hibernate;

import com.company.restaurant.dao.CommonDataDao;
import com.company.restaurant.dao.hibernate.common.HDaoEntity;
import com.company.restaurant.model.CommonData;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Yevhen on 04.08.2016.
 */
public class HCommonDataDao extends HDaoEntity<CommonData> implements CommonDataDao {
    @Transactional
    @Override
    public CommonData addCommonData(CommonData commonData) {
        return save(commonData);
    }

    private void updCommonDataValue(CommonData commonData, String value) {
        if (commonData != null) {
            commonData.setValue(value);
            save(commonData);
        }
    }

    @Transactional
    @Override
    public void updCommonDataValue(int commonDataId, String value) {
        updCommonDataValue(findCommonDataById(commonDataId), value);
    }

    @Transactional
    @Override
    public void updCommonDataValue(String name, String value) {
        updCommonDataValue(findCommonDataByName(name), value);
    }

    private void updCommonDataImage(CommonData commonData, byte[] image) {
        if (commonData != null) {
            commonData.setImage(image);
            save(commonData);
        }
    }

    @Transactional
    @Override
    public void updCommonDataImage(String name, byte[] image) {
        updCommonDataImage(findCommonDataByName(name), image);
    }

    @Transactional
    @Override
    public void delCommonData(String name) {
        delete(name);
   }

    @Transactional
    @Override
    public List<CommonData> findAllCommonData() {
        return findAllObjects();
    }

    @Transactional
    @Override
    public CommonData findCommonDataById(int commonDataId) {
        return findObjectById(commonDataId);
    }

    @Transactional
    @Override
    public CommonData findCommonDataByName(String name) {
        return findObjectByName(name);
    }

    private String getCommonDataValue(CommonData commonData) {
        return (commonData == null) ? null : commonData.getValue();
    }

    @Transactional
    @Override
    public String getCommonDataValue(int commonDataId) {
        return getCommonDataValue(findCommonDataById(commonDataId));
    }

    @Transactional
    @Override
    public String getCommonDataValue(String name) {
        return getCommonDataValue(findCommonDataByName(name));
    }

    private byte[] getCommonDataImage(CommonData commonData) {
        return (commonData == null) ? null : commonData.getImage();
    }

    @Transactional
    @Override
    public byte[] getCommonDataImage(String name) {
        return getCommonDataImage(findCommonDataByName(name));
    }
}
