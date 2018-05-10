package com.admin.adminapi.impl.dao.entities;


import com.admin.adminapi.base.dao.entities.AbstractEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@NamedQueries({
        @NamedQuery(
                name = "Admin.findByUsername",
                query = "SELECT a " +
                        "FROM Admin a " +
                        "WHERE a.username = :username"
        )
})

@Entity
@Table(name = "Admin")
@Getter @Setter @EqualsAndHashCode
public class Admin implements AbstractEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "email")
    private String email;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role;


    public Admin() {

    }

    public Admin(Long id, String name, String surname, String username, String email, String password, String role) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }
}
