package com.company.restaurant.dao.hibernate;

import com.company.restaurant.dao.StateGraphDao;
import com.company.restaurant.dao.hibernate.common.HDaoEntity;
import com.company.restaurant.model.StateGraph;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Yevhen on 16.06.2016.
 */
public class HStateGraphDao extends HDaoEntity<StateGraph> implements StateGraphDao {
    private static final String ENTITY_NAME_ATTRIBUTE_NAME = "entityName";

    @Transactional
    @Override
    public List<StateGraph> findEntityStateGraph(String entityName) {
        return findObjectsByAttributeValue(ENTITY_NAME_ATTRIBUTE_NAME, entityName);
    }
}
