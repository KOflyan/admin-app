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

        // This is needed in case of @MappedSuperclass (AbstractAccount, AbstractUser) - you cannot query it directly
        if (className.contains("Account")) {
            className = "Account";
        } else if (className.contains("User")) {
            className = "User";
        }
        return className;
    }
}
