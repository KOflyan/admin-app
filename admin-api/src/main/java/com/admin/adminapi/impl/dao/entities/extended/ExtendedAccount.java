package com.admin.adminapi.impl.dao.entities.extended;

import com.admin.adminapi.base.dao.entities.AbstractAccount;
import com.admin.adminapi.impl.dao.entities.Device;
import com.admin.adminapi.impl.dao.entities.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.Set;


@NamedQueries({

        @NamedQuery(
                name = "Account.getById",
                query = "SELECT a " +
                        "FROM ExtendedAccount a " +
                            "INNER JOIN FETCH a.devices d " +
                            "INNER JOIN FETCH a.users u " +
                        "WHERE a.id = :id " +
                        "GROUP BY a.id, u.id, d.id"

        )
})
@Entity
@Table(name = "Account")
@Getter @Setter
@EqualsAndHashCode(callSuper = true)
public class ExtendedAccount extends AbstractAccount {

    @OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, targetEntity = Device.class)
    @JoinColumn(name = "account_id")
    @NotFound(action = NotFoundAction.IGNORE)
    private Set<Device> devices;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE, targetEntity = User.class)
    @JoinColumn(name = "account_id")
    @NotFound(action = NotFoundAction.IGNORE)
    private Set<User> users;

    public ExtendedAccount() {

    }
}
