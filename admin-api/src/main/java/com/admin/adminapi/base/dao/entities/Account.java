package com.admin.adminapi.base.dao.entities;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter @EqualsAndHashCode
@MappedSuperclass
public abstract class Account {

    @Id
    @Column(name = "id")
    protected int id;

    @Column(name = "account_name")
    protected String name;

    @Column(name = "is_active")
    protected boolean isActive;

    @Column(name = "type")
    protected String type;

    public Account() {
    }

    public Account(String name, boolean isActive, String type) {
        this.name = name;
        this.isActive = isActive;
        this.type = type;
    }
}
