package com.admin.adminapi.impl.dao.entities;

import com.admin.adminapi.base.dao.entities.AbstractUser;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@NamedQueries(
        @NamedQuery(
                name = "User.countRecent",
                query = "SELECT " +
                            "COUNT(u) " +
                        "FROM User u " +
                        "WHERE u.registrationDate BETWEEN :startDate AND :endDate"
        )
)

@Entity
@Getter @Setter
@Table(name = "User")
public class User extends AbstractUser {

    public User() {

    }

    public User(Long id, int accountId, String name, String surname, String username,
                String password, String email, Date registrationDate, String language, String country, boolean isActive) {
        super(id, accountId, name, surname, username, password, email, registrationDate, language, country, isActive);
    }
}
