package com.admin.adminapi.entity;

import com.admin.adminapi.entity.base.Account;
import com.admin.adminapi.entity.base.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter @Setter
@NamedQueries({
        @NamedQuery(

                name = "User.fullInfo",
                query = "SELECT u " +

                        "FROM ExtendedUser u " +
//                            "LEFT JOIN FETCH u.account a " +
                            "LEFT JOIN FETCH u.devices d " +
                        "WHERE u.id = :id " +
                        "GROUP BY u.id, d.id " +
                        "ORDER BY u.id"
        ),
        @NamedQuery(
                name = "User.getAll",
                query = "SELECT u " +
                        "FROM ExtendedUser u " +
                            "LEFT JOIN FETCH u.account a " +
                            "LEFT JOIN FETCH u.devices d " +
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


    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private Set<Device> devices;

    @ManyToOne(cascade = CascadeType.ALL, targetEntity = SimpleAccount.class)
    @JoinColumn(name = "id", insertable = false, updatable = false)
    private Account account;


    public ExtendedUser() {

    }

}
