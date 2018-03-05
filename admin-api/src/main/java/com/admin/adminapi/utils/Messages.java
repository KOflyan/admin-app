package com.admin.adminapi.utils;

import lombok.Getter;
import lombok.ToString;

@ToString
public enum Messages {

    DTO_ERROR(0, "DTO error occurred."),
    DATABASE_ERROR(1, "Error occurred while connecting to the database"),
    SUCCESS(2, "Success!");


    @Getter final int code;
    @Getter final String message;

    Messages(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
