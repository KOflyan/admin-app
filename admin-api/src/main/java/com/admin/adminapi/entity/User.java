package com.admin.adminapi.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "User")
@Getter @Setter
//@NamedQuery(
//        name = "User.getFullUserInfo",
//        query = "SELECT " +
//                "u.name," +
//                "u.surname," +
//                "u.username," +
//                "u.password," +
//                "u.email," +
//                "u.language," +
//                "u.country," +
//                "a.name," +
//                "a.type," +
//                "a.is_active" +
//                "FROM User u" +
//                "JOIN Account a" +
//                "WHERE a.id= :account_id")
public class User implements Serializable {

    @Id
    @Column(name = "id")
    private int id;

    @Id
    @Column(name = "account_id")
    private int accountId;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "language")
    private String language;

    @Column(name = "country")
    private String country;
//
//    @ManyToOne
//    private Account account;

    public User() {
    }

    public User(int accountId, String name, String surname, String username, String password,
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
}
