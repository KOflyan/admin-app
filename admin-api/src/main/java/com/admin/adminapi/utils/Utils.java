package com.admin.adminapi.utils;

import org.springframework.core.GenericTypeResolver;


public class Utils {

    @SuppressWarnings("unchecked")
    public static <T> Class<T> resolveClassOfT(Class<?> fromClass, Class forClass) {
        return (Class<T>) GenericTypeResolver.resolveTypeArgument(fromClass, forClass);
    }

    public static String getClassName(Class clazz) {
        String className = clazz.getName();
        if (className.contains(".")) {
            className = className.substring(className.lastIndexOf('.') + 1);
        }
        return className;
    }
}
