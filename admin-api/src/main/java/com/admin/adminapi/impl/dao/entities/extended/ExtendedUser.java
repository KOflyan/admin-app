package com.admin.adminapi.impl.dao.entities.extended;

import com.admin.adminapi.base.dao.entities.Account;
import com.admin.adminapi.base.dao.entities.User;
import com.admin.adminapi.impl.dao.entities.Device;
import com.admin.adminapi.impl.dao.entities.SimpleAccount;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@NamedQueries({
        @NamedQuery(

                name = "User.fullInfo",
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
        ),
        @NamedQuery(
                name = "User.getDevices",
                query = "SELECT " +
                        "d " +
                        "FROM ExtendedUser u " +
                        "   LEFT JOIN FETCH u.devices d " +
                        "WHERE u.id = :userId " +
                        "ORDER BY d.id"
        )
})
@Entity
@Getter @Setter
@Table(name = "User")
public class ExtendedUser extends User {


    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private Set<Device> devices;

    @ManyToOne(cascade = CascadeType.ALL, targetEntity = SimpleAccount.class)
    @JoinColumn(name = "id", insertable = false, updatable = false)
    private Account account;


    public ExtendedUser() {

    }

}
