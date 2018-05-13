package com.admin.adminapi.impl.dao.entities;

import com.admin.adminapi.base.dao.entities.AbstractUser;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@NamedQueries({

        @NamedQuery(
                name = "User.countRecent",
                query = "SELECT " +
                        "   COUNT(u) " +
                        "FROM User u " +
                        "WHERE u.registrationDate BETWEEN :startDate AND :endDate"
        ),
        @NamedQuery(
                name = "User.countByLanguage",
                query = "SELECT new User(" +
                            "u.language, COUNT(u)) " +
                        "FROM User u " +
                        "GROUP BY u.language"
        ),
        @NamedQuery(
                name = "User.search",
                query = "SELECT u " +
                        "FROM User u " +
                        "WHERE " +
                            "u.username LIKE :searchText OR " +
                            "u.name LIKE :searchText OR " +
                            "u.email LIKE :searchText OR " +
                            "u.surname LIKE :searchText"
        )
})
@Entity
@Getter @Setter
@Table(name = "User")
@EqualsAndHashCode(callSuper = true)
public class User extends AbstractUser {

    @Transient
    private Long count;

    public User() {

    }

    public User(Long id, Integer accountId, String name, String surname, String username,
                String password, String email, Date registrationDate, String language, String country, boolean isActive) {
        super(id, accountId, name, surname, username, password, email, registrationDate, language, country, isActive);
    }

    public User(String language, Long count) {
        this.language = language;
        this.count = count;
    }
}
