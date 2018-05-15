package com.admin.adminapi.impl.dao.entities;

import com.admin.adminapi.base.dao.entities.AbstractAccount;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@NamedQueries({

        @NamedQuery(
                name = "Account.countByType",
                query = "SELECT new Account(" +
                        "a.accountType, COUNT(a)) " +
                        "FROM Account a " +
                        "GROUP BY a.accountType"
        ),
        @NamedQuery(
                name = "Account.search",
                query = "SELECT a " +
                        "FROM Account a " +
                        "WHERE " +
                            "a.name LIKE :searchText OR " +
                            "a.accountType LIKE :searchText"
        )
})

@Entity
@Table(name = "Account")
@Getter @Setter
public class Account extends AbstractAccount {

    @Transient
    private Long count;


    public Account() {
    }

    public Account(Long id, String name, boolean isActive, String accountType) {
        super(id, name, isActive, accountType);
    }

    public Account(String accountType, Long count) {
        this.accountType = accountType;
        this.count = count;
    }


}
