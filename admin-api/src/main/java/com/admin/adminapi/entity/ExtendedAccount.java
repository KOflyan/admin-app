package com.admin.adminapi.entity;

import com.admin.adminapi.entity.base.Account;
import com.admin.adminapi.entity.base.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Account")
@Getter @Setter
public class ExtendedAccount extends Account {


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Device.class)
    @JoinColumn(name = "account_id")
    private Set<Device> devices;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = SimpleUser.class)
    @JoinColumn(name = "account_id")
    private Set<User> users;

    public ExtendedAccount() {

    }
}
