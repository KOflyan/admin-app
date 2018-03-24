package com.admin.adminapi.entity;

import com.admin.adminapi.entity.base.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter @Setter
@NamedQueries({
        @NamedQuery(

                name = "User.fullInfo",
                query = "SELECT new ExtendedUser (" +
                            "u.id, u.accountId," +
                            "u.name, u.surname," +
                            "u.username, u.email," +
                            "u.language, u.country," +
                            "a.name, u.isActive " +
                        ")" +

                        "FROM ExtendedUser u " +
                            "JOIN u.account a " +
                            "JOIN u.devices d " +
                        "WHERE u.id = :id " +
                        "GROUP BY u.id, d.id " +
                        "ORDER BY u.id"
        ),
        @NamedQuery(
                name = "User.getAll",
                query = "SELECT " +
                            "u.id, u.accountId," +
                            "u.name, u.surname," +
                            "u.username, u.email," +
                            "u.language, u.country, " +
                            "a.name, u.isActive " +
//                        ")" +
                        "FROM ExtendedUser u " +
                            "JOIN u.account a " +
                            "JOIN u.devices d " +
                        "GROUP BY u.id, d.id " +
                        "ORDER BY u.id"
        ),
        @NamedQuery(
                name = "User.getDevices",
                query = "SELECT " +
                        "d " +
                        "FROM ExtendedUser u " +
                        "   JOIN u.devices d " +
                        "WHERE u.id = :userId " +
                        "ORDER BY d.id"
        )
})
@Table(name = "User")
public class ExtendedUser extends User {

    @Column
    private String accountName;


    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Device> devices;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Account account;


    public ExtendedUser() {
    }


    public ExtendedUser(int id, int accountId, String name, String surname, String username,
                String email, String language, String country, String accountName,
                boolean isActive) {

        this.id = id;
        this.accountId = accountId;
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.email = email;
        this.language = language;
        this.country = country;
        this.accountName = accountName;
        this.isActive = isActive;
    }
}
