package com.admin.adminapi.base.dao.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Getter @Setter @EqualsAndHashCode
@MappedSuperclass
public abstract class AbstractUser implements AbstractEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    @Column(name = "account_id")
    protected Integer accountId;

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

    @Column(name = "registration_date")
    protected Date registrationDate;

    @Column(name = "language")
    protected String language;

    @Column(name = "country")
    protected String country;

    @Column(name = "is_active")
    protected boolean isActive;

    public AbstractUser() {

    }

    public AbstractUser(Long id, Integer accountId, String name, String surname, String username,
                        String password, String email, Date registrationDate, String language, String country, boolean isActive) {
        this.id = id;
        this.accountId = accountId;
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.email = email;
        this.registrationDate = registrationDate;
        this.language = language;
        this.country = country;
        this.isActive = isActive;
    }
}
