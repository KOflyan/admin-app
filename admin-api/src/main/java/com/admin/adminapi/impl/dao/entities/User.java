package com.admin.adminapi.impl.dao.entities;

import com.admin.adminapi.base.dao.entities.AbstractUser;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter @Setter
@Table(name = "User")
public class User extends AbstractUser {

    public User() {

    }

    public User(int accountId, String name, String surname, String username,
                String password, String email, String language, String country, boolean isActive) {
        super(accountId, name, surname, username, password, email, language, country, isActive);
    }
}
