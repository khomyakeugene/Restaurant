package com.company.util.common;

/**
 * Created by Yevhen on 06.09.2016.
 */
public class Util {

    public static String decapitalize(String data){
        StringBuilder sb = new StringBuilder(data);
        sb.setCharAt(0, Character.toLowerCase(sb.charAt(0)));

        return sb.toString();
    }
}
