package com.admin.adminapi.impl.dao.entities;

import com.admin.adminapi.base.dao.entities.AbstractEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@NamedQueries({

        @NamedQuery(
                name = "Device.countByFamily",
                query = "SELECT new Device(" +
                            "COUNT(d), d.family) " +
                        "FROM Device d " +
                        "GROUP BY d.family"

        ),
        @NamedQuery(
                name = "Device.search",
                query = "SELECT d " +
                        "FROM Device d " +
                        "WHERE " +
                            "d.deviceName LIKE :searchText OR " +
                            "d.family LIKE :searchText"
        )
})
@Entity
@Table(name = "Device")
@Getter @Setter @EqualsAndHashCode
public class Device implements AbstractEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "account_id")
    private int accountId;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "device_name")
    private String deviceName;

    @Column(name = "family")
    private String family;

    @Column(name = "os_version")
    private String osVersion;

    @Transient
    private Long count;


    public Device() {

    }

    public Device(Long id, int accountId, int userId, String deviceName, String family, String osVersion) {
        this.id = id;
        this.accountId = accountId;
        this.userId = userId;
        this.deviceName = deviceName;
        this.family = family;
        this.osVersion = osVersion;
    }

    public Device(Long count, String family) {
        this.count = count;
        this.family = family;
    }

}
