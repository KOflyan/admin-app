package com.admin.adminapi.entity;

import com.admin.adminapi.entity.base.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter @Setter
@Table(name = "User")
public class SimpleUser extends User {

    public SimpleUser() {

    }

    public SimpleUser(int accountId, String name, String surname, String username,
                      String password, String email, String language, String country, boolean isActive) {
        super(accountId, name, surname, username, password, email, language, country, isActive);
    }
}
