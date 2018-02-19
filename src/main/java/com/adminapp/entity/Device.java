package com.adminapp.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "`Device`")
public class Device {

    @Id
    @Getter @Setter private int id;

    @Column(name = "account_id")
    @Getter @Setter private int accountId;

    @Column(name = "device_name")
    @Getter @Setter private String deviceName;

    @Column(name = "family")
    @Getter @Setter private String family;

    @Column(name = "os_version")
    @Getter @Setter private String osVersion;

    public Device() {

    }

    public Device(String deviceName, String family, String osVersion) {
        this.deviceName = deviceName;
        this.family = family;
        this.osVersion = osVersion;
    }

    public Device(int accountId, String deviceName, String family, String osVersion) {
        this.accountId = accountId;
        this.deviceName = deviceName;
        this.family = family;
        this.osVersion = osVersion;
    }
}
