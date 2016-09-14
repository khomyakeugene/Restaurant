package com.company.restaurant.dao.hibernate;

import com.company.restaurant.dao.IngredientDao;
import com.company.restaurant.dao.WarehouseDao;
import com.company.restaurant.dao.hibernate.common.HDaoAmountLinkEntity;
import com.company.restaurant.model.Ingredient;
import com.company.restaurant.model.Portion;
import com.company.restaurant.model.Warehouse;
import com.company.util.sql.SqlExpressions;
import org.hibernate.query.Query;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by Yevhen on 16.06.2016.
 */
public class HWarehouseDao extends HDaoAmountLinkEntity<Warehouse> implements WarehouseDao {
    private static final String AMOUNT_ATTRIBUTE_NAME = "amount";
    private static final String INGREDIENT_ATTRIBUTE_NAME = "ingredient";
    private static final String PORTION_ATTRIBUTE_NAME = "portion";
    private static final String SQL_ELAPSING_WAREHOUSE_INGREDIENTS =
            String.format("%s < :%s", AMOUNT_ATTRIBUTE_NAME, AMOUNT_ATTRIBUTE_NAME);

    private IngredientDao ingredientDao;

    public void setIngredientDao(IngredientDao ingredientDao) {
        this.ingredientDao = ingredientDao;
    }

    @Override
    protected Object prepareFirstIdAttributeValue(int firstId) {
        Ingredient ingredient = new Ingredient();
        ingredient.setIngredientId(firstId);

        return ingredient;
    }

    @Override
    protected Object prepareSecondIdAttributeValue(int secondId) {
        Portion portion = new Portion();
        portion.setPortionId(secondId);

        return portion;
    }

    @Override
    protected Warehouse newObject(int firstId, int secondId, String linkData) {
        Warehouse result = new Warehouse();
        result.getIngredient().setIngredientId(firstId);
        result.getPortion().setPortionId(secondId);
        result.setLinkData(linkData);

        return result;
    }

    @Override
    protected void initMetadata() {
        orderByAttributeName = INGREDIENT_ATTRIBUTE_NAME;
        firstIdAttributeName = INGREDIENT_ATTRIBUTE_NAME;
        secondIdAttributeName = PORTION_ATTRIBUTE_NAME;
        linkDataAttributeName = AMOUNT_ATTRIBUTE_NAME;
    }

    @Transactional
    @Override
    public void addIngredientToWarehouse(Ingredient ingredient, Portion portion, Float amount) {
        addIngredientToWarehouse(ingredient.getIngredientId(), portion.getPortionId(), amount);
    }

    @Transactional
    @Override
    public void addIngredientToWarehouse(int ingredientId, int portionId, Float amount) {
        if (amount != null) {
            increaseAmount(ingredientId, portionId, amount);
        }
    }

    @Transactional
    @Override
    public void takeIngredientFromWarehouse(Ingredient ingredient, Portion portion, Float amount) {
        takeIngredientFromWarehouse(ingredient.getIngredientId(), portion.getPortionId(), amount);
    }

    @Transactional
    @Override
    public void takeIngredientFromWarehouse(int ingredientId, int portionId, Float amount) {
        decreaseAmount(ingredientId, portionId, amount);
    }

    @Transactional
    @Override
    public Warehouse findIngredientInWarehouse(Ingredient ingredient, Portion portion) {
        return findObjectByTwoAttributeValues(INGREDIENT_ATTRIBUTE_NAME, ingredient,
                PORTION_ATTRIBUTE_NAME, portion);
    }

    @Transactional
    @Override
    public void setIngredientInWarehouse(Ingredient ingredient, Portion portion, Float amount) {
        Warehouse warehouse = new Warehouse();
        warehouse.setIngredient(ingredient);
        warehouse.setPortion(portion);

        setAmountInWarehouse(warehouse, amount);
    }

    @Transactional
    @Override
    public void setAmountInWarehouse(Warehouse warehouse, Float amount) {
        warehouse.setAmount(amount);
        saveOrUpdate(warehouse);
    }

    @Transactional
    @Override
    public List<Warehouse> findIngredientInWarehouseByName(String name) {
        return findObjectsByAttributeValue(INGREDIENT_ATTRIBUTE_NAME, ingredientDao.findIngredientByName(name));
    }

    @Transactional
    @Override
    public List<Warehouse> findIngredientInWarehouseById(int ingredientId) {
        return findObjectsByAttributeValue(INGREDIENT_ATTRIBUTE_NAME, ingredientDao.findIngredientById(ingredientId));
    }

    @Transactional
    @Override
    public List<Warehouse> findAllWarehouseIngredients() {
        return findAllObjects();
    }

    private List<Warehouse> hqlFindAllElapsingWarehouseIngredients(float limit) {
        Query<Warehouse> query = getCurrentSession().createQuery(SqlExpressions.fromExpression(
                getEntityName(), SqlExpressions.whereExpression(SQL_ELAPSING_WAREHOUSE_INGREDIENTS),
                getDefaultOrderByCondition()), Warehouse.class);
        query.setParameter(AMOUNT_ATTRIBUTE_NAME, limit);

        return query.list();
    }

    private List<Warehouse> criteriaFindAllElapsingWarehouseIngredients(float limit) {
        CriteriaBuilder criteriaBuilder = getCriteriaBuilder();
        CriteriaQuery<Warehouse> criteriaQuery = createCriteriaQuery();
        Root<Warehouse> rootEntity = criteriaQuery.from(getEntityType());
        criteriaQuery.where(criteriaBuilder.lt(rootEntity.get(AMOUNT_ATTRIBUTE_NAME), limit));

        String orderByAttributeName = getOrderByAttributeName();
        if (orderByAttributeName != null && !orderByAttributeName.isEmpty()) {
            criteriaQuery.orderBy(criteriaBuilder.asc(rootEntity.get(orderByAttributeName)));
        }

        return getCriteriaQueryResultList(criteriaQuery);
    }

    @Transactional
    @Override
    public List<Warehouse> findAllElapsingWarehouseIngredients(float limit) {
        return isUseCriteriaQuery() ?
                criteriaFindAllElapsingWarehouseIngredients(limit) :
                hqlFindAllElapsingWarehouseIngredients(limit);
    }
}
