package com.admin.adminapi.impl.dao.entities.extended;

import com.admin.adminapi.base.dao.entities.AbstractAccount;
import com.admin.adminapi.base.dao.entities.AbstractUser;
import com.admin.adminapi.impl.dao.entities.Device;
import com.admin.adminapi.impl.dao.entities.Account;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@NamedQueries({
        @NamedQuery(

                name = "User.getById",
                query = "SELECT u " +

                        "FROM ExtendedUser u " +
                            "LEFT JOIN FETCH u.account a " +
                            "LEFT JOIN FETCH u.devices d " +
                        "WHERE u.id = :id " +
                        "GROUP BY u.id, d.id " +
                        "ORDER BY u.id"
        ),
        @NamedQuery(
                name = "User.getAll",
                query = "SELECT u " +
                        "FROM ExtendedUser u " +
                            "LEFT JOIN FETCH u.account a " +
                            "LEFT JOIN FETCH u.devices d " +
                        "GROUP BY u.id, d.id " +
                        "ORDER BY u.id"
        )
})
@Entity
@Getter @Setter
@Table(name = "User")
public class ExtendedUser extends AbstractUser {


    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private Set<Device> devices;

    @ManyToOne(cascade = CascadeType.ALL, targetEntity = Account.class)
    @JoinColumn(name = "id", insertable = false, updatable = false)
    private AbstractAccount account;


    public ExtendedUser() {

    }

}
