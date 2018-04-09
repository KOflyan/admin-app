package com.admin.adminapi.base.dao.entities;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter @EqualsAndHashCode
@MappedSuperclass
 public abstract class AbstractAccount implements AbstractEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    @Column(name = "account_name")
    protected String name;

    @Column(name = "is_active")
    protected boolean isActive;

    @Column(name = "type")
    protected String accountType;

    public AbstractAccount() {
    }

    public AbstractAccount(Long id, String name, boolean isActive, String accountType) {
        this.id = id;
        this.name = name;
        this.isActive = isActive;
        this.accountType = accountType;
    }
}
