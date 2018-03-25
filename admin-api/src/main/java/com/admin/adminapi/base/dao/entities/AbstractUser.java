package com.admin.adminapi.base.dao.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@Getter @Setter @EqualsAndHashCode
@MappedSuperclass
public abstract class AbstractUser {

    @Id
    @Column(name = "id")
    protected int id;

    @Column(name = "account_id", insertable = false, updatable = false)
    protected int accountId;

    @Column(name = "name")
    protected String name;

    @Column(name = "surname")
    protected String surname;

    @Column(name = "username")
    protected String username;

    @Column(name = "password")
    protected String password;

    @Column(name = "email")
    protected String email;

    @Column(name = "language")
    protected String language;

    @Column(name = "country")
    protected String country;

    @Column(name = "is_active")
    protected boolean isActive;

    public AbstractUser() {

    }

    public AbstractUser(int accountId, String name, String surname,
                        String username, String password, String email, String language, String country, boolean isActive) {
        this.accountId = accountId;
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.email = email;
        this.language = language;
        this.country = country;
        this.isActive = isActive;
    }
}