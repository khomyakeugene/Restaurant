package com.company.restaurant.dao.common.resource;

import org.hibernate.exception.ConstraintViolationException;

import java.util.HashMap;
import java.util.Optional;

/**
 * Created by Yevhen on 05.09.2016.
 */
public class Constraints {
    // Order
    private static final String CONSTRAINT_NAME_ORDER_EMPLOYEE_ID = "fk_order_order_emp_employee";
    private static final String CONSTRAINT_NAME_ORDER_TABLE_ID = "fk_order_order_tab_table";
    // Course
    private static final String CONSTRAINT_NAME_COURSE_NAME_UNIQUE = "ak_u_course_course";
    private static final String FIELD_NAME_COURSE_ID = "course_id";
    // Cooked course
    private static final String CONSTRAINT_NAME_COOKED_COURSE_EMPLOYEE_ID = "fk_cooked_c_ckd_crs_e_employee";
    private static final String CONSTRAINT_NAME_COOKED_COURSE_COURSE_ID = "fk_cooked_c_ckd_crs_c_course";
    // order_course
    private static final String CONSTRAINT_NAME_ORDER_COURSE_COURSE_ID = "fk_order_co_ord_crs_c_course";
    // menu_course
    private static final String CONSTRAINT_NAME_MENU_COURSE_COURSE_ID = "fk_menu_cou_mmu_crs_c_course";
    // Ingredient
    private static final String FIELD_NAME_INGREDIENT_ID = "ingredient_id";
    // course_ingredient
    private static final String CONSTRAINT_NAME_PK_COURSE_INGREDIENT = "pk_course_ingredient";

    private static final String EMPLOYEE_CANNOT_BE_DELETED_BECAUSE_OF_ORDER =
            "It is impossible to delete this employee because there are orders served by him/her";
    private static final String TABLE_CANNOT_BE_DELETED_BECAUSE_OF_ORDER =
            "It is impossible to delete this table because there are orders in which this table takes place";
    private static final String EMPLOYEE_CANNOT_BE_DELETED_BECAUSE_OF_COOKED_COURSE =
            "It is impossible to delete this employee because there is at least one course cooked by him/her";
    private static final String COURSE_CANNOT_BE_DELETED_BECAUSE_OF_COOKED_COURSE =
            "It is impossible to delete this course because it was cooked at least once";
    private static final String COURSE_CANNOT_BE_DELETED_BECAUSE_OF_ORDER =
            "It is impossible to delete this course because it is included in order at least once";
    private static final String COURSE_CANNOT_BE_DELETED_BECAUSE_OF_MENU =
            "It is impossible to delete this course because it is included at least in one menu";
    private static final String COURSE_CANNOT_BE_ADDED_BECAUSE_OF_NON_UNIQUE_NAME =
            "It is impossible to save this course because its name is non-unique";
    private static final String PLEASE_SELECT_AN_INGREDIENT =
            "Please, select an ingredient";
    private static final String PLEASE_SELECT_A_COURSE =
            "Please, select a course";
    private static final String THIS_INGREDIENT_IS_ALREADY_PRESENTED_IN_THIS_COURSE =
            "This ingredient is already presented in this course";

    private static final HashMap<String, String> constraintMessageMap = new HashMap<String, String>() {
        {
            put (CONSTRAINT_NAME_ORDER_EMPLOYEE_ID, EMPLOYEE_CANNOT_BE_DELETED_BECAUSE_OF_ORDER);
            put (CONSTRAINT_NAME_ORDER_TABLE_ID, TABLE_CANNOT_BE_DELETED_BECAUSE_OF_ORDER);
            put (CONSTRAINT_NAME_COOKED_COURSE_EMPLOYEE_ID, EMPLOYEE_CANNOT_BE_DELETED_BECAUSE_OF_COOKED_COURSE);
            put (CONSTRAINT_NAME_COOKED_COURSE_COURSE_ID, COURSE_CANNOT_BE_DELETED_BECAUSE_OF_COOKED_COURSE);
            put (CONSTRAINT_NAME_ORDER_COURSE_COURSE_ID, COURSE_CANNOT_BE_DELETED_BECAUSE_OF_ORDER);
            put (CONSTRAINT_NAME_MENU_COURSE_COURSE_ID, COURSE_CANNOT_BE_DELETED_BECAUSE_OF_MENU);
            put (CONSTRAINT_NAME_COURSE_NAME_UNIQUE, COURSE_CANNOT_BE_ADDED_BECAUSE_OF_NON_UNIQUE_NAME);
            put (FIELD_NAME_INGREDIENT_ID, PLEASE_SELECT_AN_INGREDIENT);
            put (FIELD_NAME_COURSE_ID, PLEASE_SELECT_A_COURSE);
            put (CONSTRAINT_NAME_PK_COURSE_INGREDIENT, THIS_INGREDIENT_IS_ALREADY_PRESENTED_IN_THIS_COURSE);

        }
    };

    private static String findMessageByConstraintName(String constraintName) {
        return constraintMessageMap.get(constraintName.toLowerCase());
    }

    private static String transferExceptionToMessage(String exceptionMessage) {
        String result = null;

        // Search known constraint name in <exceptionMessage>
        String exceptionMessageLowerCase = exceptionMessage.toLowerCase();
        Optional<String > constraintName =
                constraintMessageMap.keySet().stream().
                        filter(c -> (exceptionMessageLowerCase.contains(c))).findFirst();
        if (constraintName.isPresent()) {
            result = findMessageByConstraintName(constraintName.get());
        }

        if (result == null) {
            result = exceptionMessage;
        }
        return result;
    }

    public static String transferExceptionToMessage(Exception e) {
        String result;

        if (e instanceof ConstraintViolationException) {
            String constraintName = ((ConstraintViolationException)e).getConstraintName();
            result = findMessageByConstraintName(constraintName);
            if (result== null) {
                result = constraintName;
            }
        } else {
            result = transferExceptionToMessage(e.getMessage());
        }

        return result;
    }
}
