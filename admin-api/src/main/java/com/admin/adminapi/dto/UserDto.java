package com.admin.adminapi.dto;

import com.admin.adminapi.dto.base.Dto;
import com.admin.adminapi.entity.User;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Component
@Getter @Setter
public class UserDto extends Dto<User> {

    private String name;

    private String surname;

    @NotNull
    @Min(1)
    private int accountId;

    @NotNull
    @Size(min = 4, max = 25)
    private String username;

    @NotNull
    @Size(min = 5, max = 255)
    private String password;

    @NotNull
    @Email
    @Size(min = 10, max = 50)
    private String email;

    @NotNull
    private String language;

    @NotNull
    private String country;

    public UserDto() {
    }

    public UserDto(int accountId, String name, String surname, String username, String password,
                   String email, String language, String country) {
        this.accountId = accountId;
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
        return new User(accountId, name, surname, username, password, email, language, country);
    }
}
