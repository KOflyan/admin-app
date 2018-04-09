package com.admin.adminapi.impl.dao.entities.extended;

import com.admin.adminapi.base.dao.entities.AbstractAccount;
import com.admin.adminapi.impl.dao.entities.Device;
import com.admin.adminapi.impl.dao.entities.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;


@NamedQueries({

        @NamedQuery(
                name = "Account.getById",
                query = "SELECT a " +
                        "FROM ExtendedAccount a " +
                            "LEFT JOIN FETCH a.devices d " +
                            "LEFT JOIN FETCH a.users u " +
                        "WHERE a.id = :id " +
                        "GROUP BY a.id, u.id, d.id"

        )
})
@Entity
@Table(name = "Account")
@Getter @Setter
public class ExtendedAccount extends AbstractAccount {

    @OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, targetEntity = Device.class)
    @JoinColumn(name = "account_id")
    private Set<Device> devices;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE, targetEntity = User.class)
    @JoinColumn(name = "account_id")
    private Set<User> users;

    public ExtendedAccount() {

    }
}
