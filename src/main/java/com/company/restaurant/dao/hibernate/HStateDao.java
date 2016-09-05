package com.company.restaurant.dao.hibernate;

import com.company.restaurant.dao.StateDao;
import com.company.restaurant.dao.hibernate.common.HDaoEntity;
import com.company.restaurant.model.State;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Yevhen on 26.06.2016.
 */
public class HStateDao extends HDaoEntity<State> implements StateDao {
    private static final String STATE_TYPE_ATTRIBUTE_NAME = "stateType";

    @Transactional
    @Override
    public State findStateByType(String stateType) {
        return findObjectByAttributeValue(STATE_TYPE_ATTRIBUTE_NAME, stateType);
    }
}
