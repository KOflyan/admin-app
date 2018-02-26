package com.admin.adminapi.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "`Account`")
@ToString
public class Account {

    @Id
    @Getter @Setter private int id;

    @Column(name = "user_id")
    @Getter @Setter private int userId;

    @Column(name = "account_name")
    @Getter @Setter private String accountName;

    @Column(name = "is_active")
    @Getter @Setter private boolean isActive;

    @Column(name = "type")
    @Getter @Setter private String type;

    public Account() {
    }

    public Account(String accountName, boolean isActive, String type) {
        this.accountName = accountName;
        this.isActive = isActive;
        this.type = type;
    }

    public Account(int userId, String accountName, boolean isActive, String type) {
        this.userId = userId;
        this.accountName = accountName;
        this.isActive = isActive;
        this.type = type;
    }
}
