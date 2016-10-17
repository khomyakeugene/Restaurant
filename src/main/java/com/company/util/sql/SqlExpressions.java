package com.company.util.sql;

public class SqlExpressions {
    private static final String SQL_FROM_PATTERN = "FROM %s";
    private static final String SQL_WHERE_PATTERN = "WHERE (%s)";
    private static final String SQL_EQUALITY_PATTERN = "(%s = %s)";
    private static final String SQL_AND_PATTERN = "(%s AND %s)";
    private static final String SQL_DELETE_EXPRESSION_PATTERN = "DELETE %s";

    public static final String SQL_ORDER_BY_ONE_FIELD_CONDITION_PATTERN_ASC = "ORDER BY %s ASC";
    public static final String SQL_ORDER_BY_TWO_FIELDS_CONDITION_PATTERN_ASC = "ORDER BY %s, %s ASC";
    public static final String SQL_ORDER_BY_ONE_FIELD_CONDITION_PATTERN_DESC = "ORDER BY %s DESC";

    public static String whereExpression(String condition) {
        return String.format(SQL_WHERE_PATTERN, condition);
    }

    private static String equalityCondition(Object leftPart, Object rightPart) {
        return String.format(SQL_EQUALITY_PATTERN, leftPart, rightPart);
    }

    public static String deleteExpression(String condition) {
        return String.format(SQL_DELETE_EXPRESSION_PATTERN, condition);
    }

    private static String andCondition(String leftPart, String rightPart) {
        return String.format(SQL_AND_PATTERN, leftPart, rightPart);
    }

    private static String fieldCondition(String fieldName, Object value) {
        return whereExpression(equalityCondition(fieldName, value));
    }

    private static String twoFieldAndCondition(String fieldName1, Object value1, String fieldName2, Object value2) {
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

    private static String fromExpressionWithTwoFieldCondition(String entityName,
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
}