package com.admin.adminapi.impl.dto;


import com.admin.adminapi.base.dto.Dto;
import com.admin.adminapi.impl.dao.entities.Admin;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Component
@Getter @Setter
public class AdminDto extends Dto<Admin> {

    private Long id;

    private String name;
    private String surname;

    @NotNull
    @Size(min = 3, max = 50)
    private String username;

    @NotNull
    @Size(min = 5, max = 255)
    private String password;

    @NotNull
    @Email
    private String email;

    @NotNull
    private String role;

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
        return new Admin(id, name, surname, username, email, password, role);
    }

}
