package com.admin.adminapi.dto;

import com.admin.adminapi.dto.base.Dto;
import com.admin.adminapi.entity.Admin;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Component
public class AdminDto extends Dto<Admin> {

    @Getter @Setter private String name;
    @Getter @Setter private String surname;

    @NotNull
    @Size(min = 4, max = 50)
    @Getter @Setter private String username;

    @NotNull
    @Size(min = 5, max = 255)
    @Getter @Setter private String password;

    @NotNull
    @Getter @Setter private String role;

    public AdminDto() {
    }

    public AdminDto(String name, String surname, String username, String password, String role) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    @Override
    public Admin get() {
        return new Admin(name, surname, username, password, role);
    }

}
