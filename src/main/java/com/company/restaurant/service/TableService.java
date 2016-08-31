package com.company.restaurant.service;

import com.company.restaurant.model.Table;

import java.util.List;

/**
 * Created by Yevhen on 17.06.2016.
 */
public interface TableService {
    Table addTable(Table table);

    void delTable(Table table);

    Table findTableById(int tableId);

    Table findTableByNumber(int number);

    List<Table> findAllTables();
}
