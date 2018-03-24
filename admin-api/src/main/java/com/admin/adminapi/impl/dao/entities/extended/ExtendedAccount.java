package com.admin.adminapi.impl.dao.entities.extended;

import com.admin.adminapi.base.dao.entities.Account;
import com.admin.adminapi.base.dao.entities.User;
import com.admin.adminapi.impl.dao.entities.Device;
import com.admin.adminapi.impl.dao.entities.SimpleUser;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;


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
