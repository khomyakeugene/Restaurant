package com.company.restaurant.service.impl;

import com.company.restaurant.dao.CommonDataDao;
import com.company.restaurant.service.CommonDataService;
import com.company.restaurant.service.impl.common.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yevhen on 04.08.2016.
 */
public class CommonDataServiceImpl extends Service implements CommonDataService {
    private static final String NAME_NAME = "name";
    private static final String ADDRESS_NAME = "address";
    private static final String E_MAIL_NAME = "e-mail";
    private static final String PHONE_NUMBERS_MASK = "phone number";
    public static final String EMBLEM_NAME = "emblem";
    public static final String TRANSPORT_MAP_NAME = "transport map";
    public static final String RESTAURANT_SCHEMA_NAME = "restaurant schema";

    private CommonDataDao commonDataDao;

    public void setCommonDataDao(CommonDataDao commonDataDao) {
        this.commonDataDao = commonDataDao;
    }

    @Override
    public String getRestaurantName() {
        return commonDataDao.getCommonDataValue(NAME_NAME);
    }

    @Override
    public String getRestaurantAddress() {
        return commonDataDao.getCommonDataValue(ADDRESS_NAME);
    }

    @Override
    public String getRestaurantEMail() {
        return commonDataDao.getCommonDataValue(E_MAIL_NAME);
    }

    @Override
    public byte[] getEmblemImage() {
        return commonDataDao.getCommonDataImage(EMBLEM_NAME);
    }

    @Override
    public byte[] getRestaurantSchemeImage() {
        return commonDataDao.getCommonDataImage(RESTAURANT_SCHEMA_NAME);
    }

    @Override
    public byte[] getTransportMapImage() {
        return commonDataDao.getCommonDataImage(TRANSPORT_MAP_NAME);
    }

    @Override
    public List<String> getPhoneNumbers() {
        List<String> result = new ArrayList<>();

        commonDataDao.findAllCommonData().stream().
                filter(d -> (d.getValue() != null) && (d.getName().trim().indexOf(PHONE_NUMBERS_MASK) == 0)).
                forEach(d -> result.add(d.getValue().trim()));

        return result;
    }
}
