package com.company.restaurant.dao.hibernate.common;

import com.company.util.common.GenericHolder;
import com.company.util.common.ObjectService;
import com.company.util.sql.SqlExpressions;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.TransientObjectException;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.persister.entity.AbstractEntityPersister;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.SingularAttribute;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.*;

/**
 * Created by Yevhen on 09.06.2016.
 */
public abstract class HDaoEntity<T> extends GenericHolder<T> {
    private static final String NAME_ATTRIBUTE_NAME = "name";

    private SessionFactory sessionFactory;
    private boolean useCriteriaQuery;

    protected String nameAttributeName = NAME_ATTRIBUTE_NAME;
    protected String orderByAttributeName;
    protected String orderByExpression;

    public HDaoEntity() {
        initMetadata();
    }

    // Could be overridden in subclasses
    protected void initMetadata() {

    }

    protected String getOrderByAttributeName() {
        if (orderByAttributeName == null) {
            orderByAttributeName = getEntityIdAttributeName();
        }

        return orderByAttributeName;
    }

    protected boolean isUseCriteriaQuery() {
        return useCriteriaQuery;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void setUseCriteriaQuery(boolean useCriteriaQuery) {
        this.useCriteriaQuery = useCriteriaQuery;
    }

    private T getFirstFromList(List<T> objects) {
        return (objects != null && objects.size() > 0) ? objects.get(0) : null;
    }

    protected boolean isPersistent(T object) {
        Session session = getCurrentSession();
        String entityName = object.getClass().getName();

        try {
            // Method <getIdentifier> could throw <TransientObjectException> if the instance is transient or
            // associated with a different session
            Serializable id = session.getIdentifier(object);
            // Try to use session.get()
            return (session.get(entityName, id) != null);
        } catch (TransientObjectException e) {
            // In the case of <TransientObjectException> try to directly use value of <identifier>-attribute
            // (just for the "first level", without checking for superclasses of object.getClass())
            try {
                Field idField = object.getClass().getDeclaredField(getEntityIdAttributeName());
                idField.setAccessible(true);
                try {
                    // It is important to give <id> of particular class for method session.get(), otherwise
                    // <TypeMismatchException> with a message such "Provided id of the wrong type for
                    // class com.company.restaurant.model.Employee. Expected: class java.lang.Integer,
                    // got class java.lang.Long" should be generated
                    Type idFieldType = idField.getType();
                    // Check only for primitive types such "int" and "long"
                    if (((Class) idFieldType).getName().equals(int.class.getName())) {
                        return (session.get(entityName, idField.getInt(object)) != null);
                    } else if (((Class) idFieldType).getName().equals(long.class.getName())) {
                        return (session.get(entityName, idField.getLong(object)) != null);
                    }
                    // Intentionally do not check for other possible types
                    return false;
                } catch (IllegalAccessException | IllegalArgumentException e2) {
                    // In this case just dare to suggest that <object> is not persistent
                    return false;
                }
            } catch (NoSuchFieldException e1) {
                // In this case just dare to suggest that <object> is not persistent
                return false;
            }
        }
    }

    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    protected CriteriaBuilder getCriteriaBuilder() {
        return sessionFactory.getCriteriaBuilder();
    }

    protected CriteriaQuery<T> createCriteriaQuery() {
        return getCriteriaBuilder().createQuery(getEntityClass());
    }

    protected List<T> getCriteriaQueryResultList(CriteriaQuery<T> criteriaQuery) {
        return getCurrentSession().createQuery(criteriaQuery).getResultList();
    }

    protected String getEntityName() {
        return getEntityClass().getName();
    }

    private String getOrderByCondition(String attributeName) {
        return (attributeName == null || attributeName.isEmpty()) ? "" :
                String.format(SqlExpressions.SQL_ORDER_BY_ONE_FIELD_CONDITION_PATTERN_ASC, attributeName);
    }

    protected String getDefaultOrderByCondition() {
        return (orderByExpression == null) ? getOrderByCondition(getOrderByAttributeName()) :
                orderByExpression;
    }

    protected EntityType<T> getEntityType() {
        return sessionFactory.getMetamodel().entity(getEntityClass());
    }

    protected String getTableName() {
        String result = null;

        // sessionFactory.getMetamodel().managedType(getEntityClass()).
        // Do not know how to get ClassMetadata (or "something like" <AbstractEntityPersister>) using
        // "non deprecated" method :( ; at least, don't understand how can use, for example,
        // sessionFactory.getMetamodel() approaching the same aim ...
        ClassMetadata classMetadata = sessionFactory.getClassMetadata(getEntityClass());
        if (classMetadata instanceof AbstractEntityPersister) {  // And what I have to do if "not instnceof ..."?
            AbstractEntityPersister abstractEntityPersister = (AbstractEntityPersister) classMetadata;
            result = abstractEntityPersister.getTableName();
            // Without possibly presented schema-name
            int pointIndex = result.lastIndexOf('.');
            if (pointIndex != -1) {
                result = result.substring(pointIndex + 1);
            }
        }

        return result;
    }

    private String getEntityIdAttributeName() {
        String result = null;

        EntityType<T> entityType = getEntityType();
        if (entityType.hasSingleIdAttribute()) {
            Optional<SingularAttribute<T, ?>> idAttribute =
                    entityType.getDeclaredSingularAttributes().stream().
                            filter(a -> entityType.getSingularAttribute(a.getName()).isId()).findFirst();
            if (idAttribute.isPresent()) {
                result = idAttribute.get().getName();
            }
        }

        return result;
    }

    protected T save(T object) {
        getCurrentSession().save(object);

        return object;
    }

    protected T save(String name) {
        T result = null;

        Field nameField = ObjectService.getDeclaredField(getEntityClass(), nameAttributeName);
        if (nameField != null) {
            result = newObject();
            nameField.setAccessible(true);
            try {
                nameField.set(result, name);
                result = save(result);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return result;
    }


    protected T saveOrUpdate(T object) {
        Session session = getCurrentSession();

        // It is important to call session.clear(): otherwise, the exception raises:
        // org.hibernate.NonUniqueObjectException: A different object with the same identifier value was already
        // associated with the session : [............
        session.clear();
        session.saveOrUpdate(object);

        return object;
    }

    protected T update(T object) {
        Session session = getCurrentSession();

        // It is important to call session.clear(): otherwise, the exception raises:
        // org.hibernate.NonUniqueObjectException: A different object with the same identifier value was already
        // associated with the session : [............
        session.clear();
        session.update(object);

        return object;
    }

    protected void delete(T object) {
        if (object != null) {
            Session session = getCurrentSession();
            // It is important to call session .clear(): otherwise, sometimes (not all the time!) (cannot understand
            // the reason of it, but it is!) the exception could raise:
            // Could not commit Hibernate transaction; nested exception is org.hibernate.TransactionException: Transaction
            // was marked for rollback only; cannot commit
            session.clear();
            session.delete(object);
        }
    }

    protected void delete(int id) {
        delete(findObjectById(id));
    }

    protected void delete(String name) {
        delete(findObjectByName(name));
    }

    protected int deleteAllObjects() {
        Query query = getCurrentSession().createQuery(SqlExpressions.deleteExpression(SqlExpressions.fromExpression(getEntityName())));
        return query.executeUpdate();
    }

    private List<T> hqlFindAllObjects() {
        return getCurrentSession().createQuery(SqlExpressions.fromExpression(
                getEntityName(), getDefaultOrderByCondition()), getEntityClass()).list();
    }

    private List<T> criteriaFindAllObjects() {
        CriteriaBuilder criteriaBuilder = getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = createCriteriaQuery();
        Root<T> rootEntity = criteriaQuery.from(getEntityType());

        String orderByAttributeName = getOrderByAttributeName();
        if (orderByAttributeName != null && !orderByAttributeName.isEmpty()) {
            criteriaQuery.orderBy(criteriaBuilder.asc(rootEntity.get(orderByAttributeName)));
        }

        return getCriteriaQueryResultList(criteriaQuery);
    }

    protected List<T> findAllObjects() {
        return useCriteriaQuery ? criteriaFindAllObjects() : hqlFindAllObjects();
    }

    private List<T> hqlFindObjectsByAttributeValue(String attributeName, Object value) {
        String orderByAttributeName = getOrderByAttributeName();
        String orderByCondition = (orderByAttributeName != null && !orderByAttributeName.isEmpty() &&
                !orderByAttributeName.equals(attributeName)) ? getOrderByCondition(getOrderByAttributeName()) : null;

        Query<T> query = getCurrentSession().createQuery(SqlExpressions.fromExpressionWithFieldCondition(
                getEntityName(), attributeName, String.format(":%s", attributeName), orderByCondition),
                getEntityClass());
        query.setParameter(attributeName, value);

        return query.list();
    }

    private List<T> criteriaFindObjectsByAttributeValue(String attributeName, Object value) {
        CriteriaBuilder criteriaBuilder = getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = createCriteriaQuery();
        Root<T> rootEntity = criteriaQuery.from(getEntityType());
        criteriaQuery.where(criteriaBuilder.equal(rootEntity.get(attributeName), value));

        String orderByAttributeName = getOrderByAttributeName();
        if (orderByAttributeName != null && !orderByAttributeName.isEmpty() && !attributeName.equals(orderByAttributeName)) {
            criteriaQuery.orderBy(criteriaBuilder.asc(rootEntity.get(orderByAttributeName)));
        }

        return getCriteriaQueryResultList(criteriaQuery);
    }

    protected List<T> findObjectsByAttributeValue(String attributeName, Object value) {
        return useCriteriaQuery ? criteriaFindObjectsByAttributeValue(attributeName, value) :
                hqlFindObjectsByAttributeValue(attributeName, value);
    }


    protected Set<T> findObjectSetByAttributeValue(String attributeName, com.company.restaurant.model.Course value) {
        HashSet<T> result = new HashSet<>();
        result.addAll(findObjectsByAttributeValue(attributeName, value));

        return result;
    }


    protected T findObjectByAttributeValue(String attributeName, Serializable value) {
        return getFirstFromList(findObjectsByAttributeValue(attributeName, value));
    }

    protected T findObjectById(int id) {
        return findObjectByAttributeValue(getEntityIdAttributeName(), id);
    }

    protected T findObjectByName(String name) {
        return findObjectByAttributeValue(nameAttributeName, name);
    }

    private List<T> findObjectsByAttributeFragment(String attributeName, String nameFragment) {
        List<T> result = null;

        Field nameField = ObjectService.getDeclaredField(getEntityClass(), attributeName);
        if (nameField != null) {
            nameField.setAccessible(true);
            String lowerCaseNameFragment = nameFragment.trim().toLowerCase();
            result = new ArrayList<>();
            findAllObjects().stream().filter(c -> {
                try {
                    return (((String) nameField.get(c)).trim().toLowerCase().contains(lowerCaseNameFragment));
                } catch (IllegalAccessException e) {
                    return false;
                }
            }).forEach(result::add);
        }

        return result;
    }

    protected List<T> findObjectsByNameFragment(String nameFragment) {
        return findObjectsByAttributeFragment(nameAttributeName, nameFragment);
    }

    private List<T> hqlFindObjectsByTwoAttributeValues(String attributeName1,
                                                       Object value1,
                                                       String attributeName2,
                                                       Object value2) {
        Query<T> query = getCurrentSession().createQuery(SqlExpressions.fromExpressionWithTwoFieldCondition(
                getEntityName(), attributeName1, String.format(":%s", attributeName1), attributeName2,
                String.format(":%s", attributeName2)), getEntityClass());
        query.setParameter(attributeName1, value1);
        query.setParameter(attributeName2, value2);

        return query.list();
    }

    private List<T> criteriaFindObjectsByTwoAttributeValues(String attributeName1,
                                                            Object value1,
                                                            String attributeName2,
                                                            Object value2) {
        CriteriaBuilder criteriaBuilder = getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = createCriteriaQuery();
        Root<T> rootEntity = criteriaQuery.from(getEntityType());
        criteriaQuery.where(criteriaBuilder.and(criteriaBuilder.equal(rootEntity.get(attributeName1), value1),
                criteriaBuilder.equal(rootEntity.get(attributeName2), value2)));

        return getCriteriaQueryResultList(criteriaQuery);
    }

    protected List<T> findObjectsByTwoAttributeValues(String attributeName1,
                                                      Object value1,
                                                      String attributeName2,
                                                      Object value2) {
        return useCriteriaQuery ?
                criteriaFindObjectsByTwoAttributeValues(attributeName1, value1, attributeName2, value2) :
                hqlFindObjectsByTwoAttributeValues(attributeName1, value1, attributeName2, value2);
    }

    protected T findObjectByTwoAttributeValues(String attributeName1,
                                               Object value1,
                                               String attributeName2,
                                               Object value2) {
        return getFirstFromList(findObjectsByTwoAttributeValues(attributeName1, value1, attributeName2, value2));
    }
}
