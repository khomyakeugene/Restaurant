package restaurant.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Yevhen on 08.06.2016.
 */
public class ObjectService {
    private static final String GET_PREFIX = "get";
    private static final String SET_PREFIX = "set";

    private static String[] getPublicMethodNameList(Object object, String methodPrefix) {
        Method[] methods = object.getClass().getMethods();
        ArrayList<String> nameArrayList = new ArrayList<>();

        boolean notPrefixCheck = (methodPrefix == null) || methodPrefix.isEmpty();
        for (Method method : methods) {
            String name = method.getName();
            if (notPrefixCheck || name.indexOf(methodPrefix) == 0) {
                nameArrayList.add(name);
            }
        }

        String[] methodNameList = new String[nameArrayList.size()];
        nameArrayList.toArray(methodNameList);

        return methodNameList;
    }

    private static String[] getGettersNameList(Object object) {
        return getPublicMethodNameList(object, GET_PREFIX);
    }

    private static String[] getSettersNameList(Object object) {
        return getPublicMethodNameList(object, SET_PREFIX);
    }

    public static Object copyObjectByAccessors(Object source, Object target) {
        if (source != null && target != null) {
            String[] sourceGetters = getGettersNameList(source);
            String[] targetSetters = getSettersNameList(target);

            Arrays.stream(sourceGetters).forEach(getter -> {
                String setterName = SET_PREFIX + getter.substring(GET_PREFIX.length());
                if (Arrays.stream(targetSetters).filter(s -> s.equals(setterName)).findFirst().isPresent()) {
                    try {
                        Method getterMethod = source.getClass().getMethod(getter);
                        Class returnType = getterMethod.getReturnType();
                        try {
                            Method setterMethod = target.getClass().getMethod(setterName, returnType);
                            if (setterMethod != null) {
                                try {
                                    setterMethod.invoke(target, getterMethod.invoke(source));
                                } catch (IllegalAccessException | InvocationTargetException e) {
                                    // Theoretically, such exception should not be raised and caught here
                                    throw new RuntimeException(e);
                                }
                            }
                        } catch (NoSuchMethodException e) {
                            // Not all "analogs" of source.getters should be presented as target.setters

                        }
                    } catch (NoSuchMethodException e) {
                        // Theoretically, such exception should not be raised and caught here
                        throw new RuntimeException(e);
                    }
                }
            });
        }

        return target;
    }
}
