package com.admin.adminapi.entity;

import com.admin.adminapi.entity.base.Account;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Account")
@Getter @Setter
public class SimpleAccount extends Account {

    public SimpleAccount() {

    }

    public SimpleAccount(String name, boolean isActive, String type) {
        super(name, isActive, type);
    }
}
