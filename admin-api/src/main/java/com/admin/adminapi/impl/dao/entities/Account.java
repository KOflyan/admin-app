package com.admin.adminapi.impl.dao.entities;

import com.admin.adminapi.base.dao.entities.AbstractAccount;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Account")
@Getter @Setter
public class Account extends AbstractAccount {

    public Account() {

    }

    public Account(String name, boolean isActive, String type) {
        super(name, isActive, type);
    }
}
