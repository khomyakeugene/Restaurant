package com.company.restaurant.dao.hibernate;

import com.company.restaurant.dao.TableDao;
import com.company.restaurant.dao.hibernate.common.HDaoEntity;
import com.company.restaurant.model.Table;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Yevhen on 12.06.2016.
 */
public class HTableDao extends HDaoEntity<Table> implements TableDao {
    private static final String NUMBER_ATTRIBUTE_NAME = "number";

    @Transactional
    @Override
    public Table addTable(Table table) {
        return save(table);
    }

    @Transactional
    @Override
    public void delTable(Table table) {
        delete(table);
    }

    @Transactional
    @Override
    public Table findTableById(int tableId) {
        return findObjectById(tableId);
    }

    @Transactional
    @Override
    public Table findTableByNumber(int number) {
        return findObjectByAttributeValue(NUMBER_ATTRIBUTE_NAME, number);
    }

    @Transactional
    @Override
    public List<Table> findAllTables() {
        return findAllObjects();
    }
}
