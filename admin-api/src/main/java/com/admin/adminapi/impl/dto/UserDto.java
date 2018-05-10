package com.admin.adminapi.impl.dto;

import com.admin.adminapi.base.dao.entities.AbstractUser;
import com.admin.adminapi.base.dto.Dto;
import com.admin.adminapi.impl.dao.entities.User;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;

@Component
@Getter @Setter
public class UserDto extends Dto<AbstractUser> {

    private Long id;

    private String name;

    private String surname;

    private boolean active;

    @NotNull
    private Integer accountId;

    @NotNull
    @Size(min = 3, max = 25)
    private String username;

    @NotNull
    @Size(min = 5, max = 255)
    private String password;

    @NotNull
    @Email
    private String email;

    @NotNull
    private String language;

    @NotNull
    private String country;

    public UserDto() {
    }

    public UserDto(Integer accountId, String name, String surname, String username, String password,
                   String email, String language, String country, Boolean active) {
        this.accountId = accountId;
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.email = email;
        this.language = language;
        this.country = country;
        this.active = active;
    }

    @Override
    public AbstractUser get() {
        return new User(id, accountId, name, surname, username, password, email, new Date(System.currentTimeMillis()), language, country, active);
    }
}
