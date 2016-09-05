package com.company.restaurant.dao.database.resource;

import java.util.HashMap;
import java.util.Optional;

/**
 * Created by Yevhen on 05.09.2016.
 */
public class Constraints {
    // Order
    private static final String CONSTRAINT_NAME_ORDER_EMPLOYEE_ID = "fk_order_order_emp_employee";
    private static final String CONSTRAINT_NAME_ORDER_TABLE_ID = "fk_order_order_tab_table";
    // Cooked course
    private static final String CONSTRAINT_NAME_COOKED_COURSE_EMPLOYEE_ID = "fk_cooked_c_ckd_crs_e_employee";
    private static final String CONSTRAINT_NAME_COOKED_COURSE_COURSE_ID = "fk_cooked_c_ckd_crs_c_course";

    private static final String EMPLOYEE_CANNOT_BE_DELETED_BECAUSE_OF_ORDER =
            "It is impossible to delete employee because there are orders served by him/her";
    private static final String TABLE_CANNOT_BE_DELETED_BECAUSE_OF_ORDER =
            "It is impossible to delete table because there are orders in which this table takes place";
    private static final String EMPLOYEE_CANNOT_BE_DELETED_BECAUSE_OF_COOKED_COURSE =
            "It is impossible to delete employee because there is at least one course cooked by him/her";
    private static final String COURSE_CANNOT_BE_DELETED_BECAUSE_OF_COOKED_COURSE =
            "It is impossible to delete course because is was cooked at least once";

    private static final HashMap<String, String> constraintMessageMap = new HashMap<String, String>() {
        {
            put (CONSTRAINT_NAME_ORDER_EMPLOYEE_ID, EMPLOYEE_CANNOT_BE_DELETED_BECAUSE_OF_ORDER);
            put (CONSTRAINT_NAME_ORDER_TABLE_ID, TABLE_CANNOT_BE_DELETED_BECAUSE_OF_ORDER);
            put (CONSTRAINT_NAME_COOKED_COURSE_EMPLOYEE_ID, EMPLOYEE_CANNOT_BE_DELETED_BECAUSE_OF_COOKED_COURSE);
            put (CONSTRAINT_NAME_COOKED_COURSE_COURSE_ID, COURSE_CANNOT_BE_DELETED_BECAUSE_OF_COOKED_COURSE);
        }
    };

    private static String findMessageByConstraintName(String constraintName) {
        return constraintMessageMap.get(constraintName.toLowerCase());
    }

    public static String transferExceptionToMessage(String exceptionMessage) {
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
        return transferExceptionToMessage(e.getMessage());
    }
}
