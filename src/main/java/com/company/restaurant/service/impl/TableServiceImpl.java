package com.company.restaurant.service.impl;

import com.company.restaurant.dao.TableDao;
import com.company.restaurant.model.Table;
import com.company.restaurant.service.TableService;
import com.company.restaurant.service.impl.common.ObjectService;

import java.util.List;

public class TableServiceImpl extends ObjectService<Table> implements TableService {
    private TableDao tableDao;

    public void setTableDao(TableDao tableDao) {
        this.tableDao = tableDao;
    }

    @Override
    public Table addTable(Table table) {
        return tableDao.addTable(table);
    }

    @Override
    public void delTable(Table table) {
        tableDao.delTable(table);
    }

    @Override
    public Table findTableById(int tableId) {
        return tableDao.findTableById(tableId);
    }

    @Override
    public Table findTableByNumber(int number) {
        return tableDao.findTableByNumber(number);
    }

    @Override
    public List<Table> findAllTables() {
        return tableDao.findAllTables();
    }
}