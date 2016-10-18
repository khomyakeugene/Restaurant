package com.company.restaurant.service;

import com.company.restaurant.model.Table;

import java.util.List;

/**
 * Created by Yevhen on 17.06.2016.
 */
public interface TableService {
    Table findTableById(int tableId);

    List<Table> findAllTables();
}
