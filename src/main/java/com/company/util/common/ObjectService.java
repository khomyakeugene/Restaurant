package com.company.util.common;

import java.lang.reflect.Field;

/**
 * Created by Yevhen on 18.08.2016.
 */
public class ObjectService {
    public static Field getDeclaredField(Class objectClass, String fieldName) {
        Field result = null;
        while((result == null) && (objectClass != null)) {
            try {
                result = objectClass.getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
                objectClass = objectClass.getSuperclass();
            }
        }

        return result;
    }
}
