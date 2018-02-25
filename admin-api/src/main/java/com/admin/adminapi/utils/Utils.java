package com.admin.adminapi.utils;

import com.admin.adminapi.entity.Account;
import com.admin.adminapi.entity.Admin;
import com.admin.adminapi.entity.Device;
import com.admin.adminapi.entity.User;
import lombok.Getter;
import org.springframework.core.GenericTypeResolver;

import java.util.Arrays;
import java.util.List;

public class Utils {

    @Getter private static final List<Class> classes = Arrays.asList(
            Account.class, Admin.class,
            Device.class, User.class
    );


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
