package com.admin.adminapi.entity;


import com.admin.adminapi.entity.base.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Account")
@Getter @Setter @EqualsAndHashCode

@NamedQueries({
        @NamedQuery(
                name = "Account.fullInfo",
                query = "SELECT new Account(" +
                        "a.id, a.name, " +
                        "a.isActive, a.type " +
//                        "d, u )"  +
//                            "SIZE(d), SIZE(u)" +
                       "FROM Account a " +
                            "JOIN a.devices d " +
                            "JOIN a.users u " +
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

    public Account(int id, String name, boolean isActive, String type, Set<Device> devices,
                   Set<User> users) {
        this.id = id;
        this.name = name;
        this.isActive = isActive;
        this.type = type;
        this.users = users;
        this.devices = devices;
    }

    public Account(int id, String name, boolean isActive, String type, Device device,
                   User user) {
        this.id = id;
        this.name = name;
        this.isActive = isActive;
        this.type = type;
        this.users.add(user);
        this.devices.add(device);
    }
}
