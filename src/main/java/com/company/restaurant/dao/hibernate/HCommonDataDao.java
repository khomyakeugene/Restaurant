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

    @Transactional
    @Override
    public CommonData addCommonData(String name, String value, byte[] image) {
        CommonData commonData = new CommonData();
        commonData.setName(name);
        commonData.setValue(value);
        commonData.setImage(image);

        return addCommonData(commonData);
    }

    @Transactional
    @Override
    public CommonData addCommonData(String name, String value) {
        return addCommonData(name, value, null);
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
    public void updCommonDataImage(int commonDataId, byte[] image) {
        updCommonDataImage(findCommonDataById(commonDataId), image);
    }

    @Transactional
    @Override
    public void updCommonDataImage(String name, byte[] image) {
        updCommonDataImage(findCommonDataByName(name), image);
    }

    @Transactional
    @Override
    public void delCommonData(CommonData commonData) {
        delete(commonData);
    }

    @Transactional
    @Override
    public void delCommonData(int commonDataId) {
        delete(commonDataId);
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
    public byte[] getCommonDataImage(int commonDataId) {
        return getCommonDataImage(findCommonDataById(commonDataId));
    }

    @Transactional
    @Override
    public byte[] getCommonDataImage(String name) {
        return getCommonDataImage(findCommonDataByName(name));
    }
}
