package com.admin.adminapi.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "`User`")
@ToString
public class User {

    @Id
    @Getter @Setter private int id;

    @Column(name = "name")
    @Getter @Setter private String name;

    @Column(name = "surname")
    @Getter @Setter private String surname;

    @Column(name = "username")
    @Getter @Setter private String username;

    @Column(name = "password")
    @Getter @Setter private String password;

    @Column(name = "email")
    @Getter @Setter private String email;

    @Column(name = "language")
    @Getter @Setter private String language;

    @Column(name = "country")
    @Getter @Setter private String country;

    public User() {
    }

    public User(String name, String surname, String username, String password,
                String email, String language, String country) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.email = email;
        this.language = language;
        this.country = country;
    }
}
