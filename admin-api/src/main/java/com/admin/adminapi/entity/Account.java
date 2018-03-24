package com.admin.adminapi.entity;


import com.admin.adminapi.entity.base.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Account")
@Getter @Setter @EqualsAndHashCode

@NamedQueries({

        @NamedQuery(
                name = "Account.fullInfo",
                query = "SELECT a " +
//                            "SIZE(d), SIZE(u)" +
                       "FROM Account a " +
                            "LEFT JOIN FETCH a.devices d " +
                            "LEFT JOIN FETCH a.users u " +
                        "GROUP BY a.id, u.id, d.id"

        )
})
public class Account {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "account_name")
    private String name;

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "type")
    private String type;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Device.class)
    @JoinColumn(name = "account_id")
    private Set<Device> devices;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = SimpleUser.class)
    @JoinColumn(name = "account_id")
    private Set<User> users;

//    private int deviceCount;
//    private int userCount;

    public Account() {
    }

    public Account(String name, boolean isActive, String type) {
        this.name = name;
        this.isActive = isActive;
        this.type = type;
    }
}
