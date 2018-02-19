package com.adminapp.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "`User`")
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

    @Column(name = "registration_date")
    @Getter @Setter private Date registrationDate;

    @Column(name = "language")
    @Getter @Setter private String language;

    @Column(name = "country")
    @Getter @Setter private String country;

    public User() {
    }

    public User(String name, String surname, String username, String password,
                String email, Date registrationDate, String language, String country) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.email = email;
        this.registrationDate = registrationDate;
        this.language = language;
        this.country = country;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", registrationDate='" + registrationDate + '\'' +
                ", language='" + language + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
