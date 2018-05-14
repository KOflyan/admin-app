package com.admin.adminapi.impl.dao.entities.extended;

import com.admin.adminapi.base.dao.entities.AbstractUser;
import com.admin.adminapi.impl.dao.entities.Account;
import com.admin.adminapi.impl.dao.entities.Device;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

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
@EqualsAndHashCode(callSuper = true)
public class ExtendedUser extends AbstractUser {


    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id")
    @NotFound(action = NotFoundAction.IGNORE)
    private Set<Device> devices;

    @ManyToOne(cascade = CascadeType.MERGE, targetEntity = Account.class)
    @JoinColumn(name = "id", insertable = false, updatable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    private Account account;


    public ExtendedUser() {

    }

}
