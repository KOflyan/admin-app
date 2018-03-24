package com.admin.adminapi.entity.base;


import com.admin.adminapi.entity.base.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter @Setter @EqualsAndHashCode

@NamedQueries({

        @NamedQuery(
                name = "Account.fullInfo",
                query = "SELECT a " +
                       "FROM ExtendedAccount a " +
                            "LEFT JOIN FETCH a.devices d " +
                            "LEFT JOIN FETCH a.users u " +
                        "GROUP BY a.id, u.id, d.id"

        )
})
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
