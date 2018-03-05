package com.admin.adminapi.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Account")
@Getter @Setter
public class Account {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "account_name")
    private String accountName;

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "type")
    private String type;

//
//    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinColumn(name = "account_id")
//    @Getter private Set<Device> devices;
//
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "account")
//    private Set<User> users;

    public Account() {
    }

    public Account(String accountName, boolean isActive, String type) {
        this.accountName = accountName;
        this.isActive = isActive;
        this.type = type;
    }
}
