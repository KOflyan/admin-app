package com.admin.adminapi.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Device")
@Getter @Setter @EqualsAndHashCode
public class Device {

    @Id
    @Column(name = "id")
    private int id;

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


    public Device() {

    }

    public Device(int accountId, int userId, String deviceName, String family, String osVersion) {
        this.accountId = accountId;
        this.userId = userId;
        this.deviceName = deviceName;
        this.family = family;
        this.osVersion = osVersion;
    }
}
