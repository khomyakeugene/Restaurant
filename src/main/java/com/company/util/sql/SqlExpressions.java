package com.company.util.sql;

public class SqlExpressions {
    private static final String SQL_SELECT_PATTERN = "SELECT %s";
    private static final String SQL_FROM_PATTERN = "FROM %s";
    private static final String SQL_WHERE_PATTERN = "WHERE (%s)";
    private static final String SQL_EQUALITY_PATTERN = "(%s = %s)";
    private static final String SQL_AND_PATTERN = "(%s AND %s)";
    private static final String SQL_MAX_STATEMENT = "MAX(%s)";
    private static final String SQL_DELETE_EXPRESSION_PATTERN = "DELETE %s";
    private static final String SQL_UPDATE_EXPRESSION_PATTERN = "UPDATE %s SET %s %s";
    private static final String SQL_UPDATE_SET_SECTION_PART_PATTERN = "%s = %s";

    public static final String SQL_ORDER_BY_ONE_FIELD_CONDITION_PATTERN_ASC = "ORDER BY %s ASC";
    public static final String SQL_ORDER_BY_TWO_FIELDS_CONDITION_PATTERN_ASC = "ORDER BY %s, %s ASC";
    public static final String SQL_ORDER_BY_ONE_FIELD_CONDITION_PATTERN_DESC = "ORDER BY %s DESC";

    public static String selectExpression(String selectFields) {
        return String.format(SQL_SELECT_PATTERN, selectFields);
    }

    public static String whereExpression(String condition) {
        return String.format(SQL_WHERE_PATTERN, condition);
    }

    public static String equalityCondition(Object leftPart, Object rightPart) {
        return String.format(SQL_EQUALITY_PATTERN, leftPart, rightPart);
    }

    public static String deleteExpression(String condition) {
        return String.format(SQL_DELETE_EXPRESSION_PATTERN, condition);
    }

    public static String updateExpression(String entityName,
                                          String updateSet,
                                          String updateCondition) {
        return String.format(SQL_UPDATE_EXPRESSION_PATTERN, entityName, updateSet, updateCondition);
    }

    public static String andCondition(String leftPart, String rightPart) {
        return String.format(SQL_AND_PATTERN, leftPart, rightPart);
    }

    public static String fieldCondition(String fieldName, Object value) {
        return whereExpression(equalityCondition(fieldName, value));
    }

    public static String twoFieldAndCondition(String fieldName1, Object value1, String fieldName2, Object value2) {
        return whereExpression(andCondition(equalityCondition(fieldName1, value1), equalityCondition(fieldName2, value2)));
    }


    public static String fromExpression(String entityName, String whereCondition, String orderByCondition) {
        return String.format(SQL_FROM_PATTERN, entityName) + " " +
                ((whereCondition != null) ? whereCondition + " " : "") +
                ((orderByCondition != null) ? orderByCondition : "");
    }

    public static String fromExpression(String entityName, String orderByCondition) {
        return fromExpression(entityName, null, orderByCondition);
    }

    public static String fromExpression(String entityName) {
        return fromExpression(entityName, null);
    }

    public static String fromExpressionWithFieldCondition(String entityName,
                                                          String fieldName,
                                                          Object value,
                                                          String orderByCondition) {
        return fromExpression(entityName, fieldCondition(fieldName, value), orderByCondition);
    }

    public static String fromExpressionWithFieldCondition(String entityName,
                                                          String fieldName,
                                                          Object value) {
        return fromExpressionWithFieldCondition(entityName, fieldName, value, null);
    }

    public static String fromExpressionWithTwoFieldCondition(String entityName,
                                                             String fieldName1,
                                                             Object value1,
                                                             String fieldName2,
                                                             Object value2,
                                                             String orderByCondition) {
        return fromExpression(entityName, twoFieldAndCondition(fieldName1, value1, fieldName2, value2), orderByCondition);
    }

    public static String fromExpressionWithTwoFieldCondition(String entityName,
                                                             String fieldName1,
                                                             Object value1,
                                                             String fieldName2,
                                                             Object value2) {
        return fromExpressionWithTwoFieldCondition(entityName, fieldName1, value1, fieldName2, value2, null);
    }

    public static String allEntityQueryCondition(String selectFields, String entityName, String orderByCondition) {
        return selectExpression(selectFields) + " " + fromExpression(entityName, orderByCondition);
    }

    public static String allEntityQueryCondition(String entityName, String selectFields) {
        return allEntityQueryCondition(selectFields, entityName, null);
    }

    public static String fieldEntityQueryCondition(String selectFields,
                                                   String entityName,
                                                   String fieldName,
                                                   Object value,
                                                   String orderByCondition) {
        return selectExpression(selectFields) + " " +
                fromExpressionWithFieldCondition(entityName, fieldName, value, orderByCondition);
    }

    public static String fieldEntityQueryCondition(String selectFields, String entityName, String fieldName,
                                                   Object value) {
        return SqlExpressions.fieldEntityQueryCondition(selectFields, entityName, fieldName, value, null);
    }

    public static String twoFieldsEntityQueryCondition(String selectFields,
                                                       String entityName,
                                                       String fieldName1,
                                                       Object value1,
                                                       String fieldName2,
                                                       Object value2,
                                                       String orderByCondition) {
        return selectExpression(selectFields) + " " +
                fromExpressionWithTwoFieldCondition(entityName, fieldName1, value1, fieldName2,
                        value2, orderByCondition);
    }

    public static String twoFieldsEntityQueryCondition(String selectFields,
                                                       String entityName,
                                                       String fieldName1,
                                                       Object value1,
                                                       String fieldName2,
                                                       Object value2) {
        return twoFieldsEntityQueryCondition(selectFields, entityName, fieldName1, value1, fieldName2, value2, null);
    }

    public static String maxFieldValueExpression(String fieldName) {
        return String.format(SQL_MAX_STATEMENT, fieldName);
    }

    public static String oneFieldByOneFieldConditionUpdateExpression(String entityName,
                                                                     String updateFieldName,
                                                                     Object updateFieldValue,
                                                                     String conditionFieldName,
                                                                     Object conditionFieldValue) {

        return updateExpression(entityName, String.format(SQL_UPDATE_SET_SECTION_PART_PATTERN,
                updateFieldName, updateFieldValue), whereExpression(String.format(
                SQL_EQUALITY_PATTERN, conditionFieldName, conditionFieldValue)));
    }
}