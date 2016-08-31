package com.company.restaurant.service.impl;

import com.company.restaurant.dao.IngredientDao;
import com.company.restaurant.dao.PortionDao;
import com.company.restaurant.dao.WarehouseDao;
import com.company.restaurant.model.Ingredient;
import com.company.restaurant.model.Portion;
import com.company.restaurant.model.Warehouse;
import com.company.restaurant.service.WarehouseService;
import com.company.restaurant.service.impl.proto.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class WarehouseServiceImpl extends Service implements WarehouseService {
    private WarehouseDao warehouseDao;
    private IngredientDao ingredientDao;
    private PortionDao portionDao;

    public void setWarehouseDao(WarehouseDao warehouseDao) {
        this.warehouseDao = warehouseDao;
    }

    public void setIngredientDao(IngredientDao ingredientDao) {
        this.ingredientDao = ingredientDao;
    }

    public void setPortionDao(PortionDao portionDao) {
        this.portionDao = portionDao;
    }

    @Override
    public void addIngredientToWarehouse(Ingredient ingredient, Portion portion, float amount) {
        if (amount > 0.0) {
            warehouseDao.addIngredientToWarehouse(ingredient, portion, amount);
        }
    }

    @Override
    public void takeIngredientFromWarehouse(Ingredient ingredient, Portion portion, float amount) {
        if (amount > 0.0) {
            warehouseDao.takeIngredientFromWarehouse(ingredient, portion, amount);
        }
    }

    @Override
    public Warehouse findIngredientInWarehouse(Ingredient ingredient, Portion portion) {
        return warehouseDao.findIngredientInWarehouse(ingredient, portion);
    }

    @Override
    public List<Warehouse> findIngredientInWarehouseByName(String name) {
        return warehouseDao.findIngredientInWarehouseByName(name);
    }

    @Override
    public List<Warehouse> findIngredientInWarehouseById(int ingredientId) {
        return warehouseDao.findIngredientInWarehouseById(ingredientId);
    }

    @Override
    public List<Warehouse> findAllWarehouseIngredients() {
        return warehouseDao.findAllWarehouseIngredients();
    }

    @Override
    public List<Warehouse> findAllElapsingWarehouseIngredients(float limit) {
        return warehouseDao.findAllElapsingWarehouseIngredients(limit);
    }

    @Override
    public List<Ingredient> findAllIngredients() {
        return ingredientDao.findAllIngredients();
    }

    @Override
    public Ingredient findIngredientById(int ingredientId) {
        return ingredientDao.findIngredientById(ingredientId);
    }

    @Override
    public List<Portion> findAllPortions() {
        return portionDao.findAllPortions();
    }

    @Override
    public Portion findPortionById(int portionId) {
        return portionDao.findPortionById(portionId);
    }

    @Override
    @Transactional
    public void clearWarehouse() {
        for (Warehouse warehouse : findAllWarehouseIngredients()) {
            takeIngredientFromWarehouse(warehouse.getIngredient(), warehouse.getPortion(),
                    warehouse.getAmount());

        }
    }
}