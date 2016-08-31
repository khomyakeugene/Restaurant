package com.company.restaurant.dao;

import com.company.restaurant.model.Table;

import java.util.List;

/**
 * Created by Yevhen on 22.05.2016.
 */
public interface TableDao {
    Table addTable(Table table);

    void delTable(Table table);

    Table findTableById(int tableId);

    Table findTableByNumber(int number);

    List<Table> findAllTables();
}
