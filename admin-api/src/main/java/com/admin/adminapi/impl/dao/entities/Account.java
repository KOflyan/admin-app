package com.admin.adminapi.impl.dao.entities;

import com.admin.adminapi.base.dao.entities.AbstractAccount;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Account")
@Getter @Setter @ToString
public class Account extends AbstractAccount {

    public Account() {
    }

    public Account(Long id, String name, boolean isActive, String type) {
        super(id, name, isActive, type);
    }
}
