package com.admin.adminapi.dto;

import com.admin.adminapi.dto.base.Dto;
import com.admin.adminapi.entity.User;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Component
public class UserDto extends Dto<User> {

    @Getter @Setter private String name;

    @Getter @Setter private String surname;

    @NotNull
    @Size(min = 4, max = 25)
    @Getter @Setter private String username;

    @NotNull
    @Size(min = 5, max = 255)
    @Getter @Setter private String password;

    @NotNull
    @Email
    @Size(min = 10, max = 50)
    @Getter @Setter private String email;

    @NotNull
    @Getter @Setter private String language;

    @NotNull
    @Getter @Setter private String country;

    public UserDto() {
    }

    public UserDto(String name, String surname, String username, String password,
                   String email, String language, String country) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.email = email;
        this.language = language;
        this.country = country;
    }

    @Override
    public User get() {
        return new User(name, surname, username, password, email, language, country);
    }
}
