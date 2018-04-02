package com.admin.adminapi.utils;

import lombok.Getter;

@Getter
public enum Messages {

    DTO_ERROR(0, "DTO error occurred (Invalid DTO)."),
    DATABASE_ERROR(1, "Error occurred while connecting to the database."),
    SUCCESS(2, "Operation completed successfully."),
    ENTITY_NO_FOUND_ERROR(3, "Entity with the requested ID does not exist.");


    final int code;
    final String message;

    Messages(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
