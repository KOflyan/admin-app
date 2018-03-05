package com.admin.adminapi.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Device")
@Getter @Setter
public class Device implements Serializable{

    @Id
    @Column(name = "id")
    private int id;

    @Id
    @Column(name = "account_id")
    private int accountId;

    @Column(name = "device_name")
    private String deviceName;

    @Column(name = "family")
    private String family;

    @Column(name = "os_version")
    private String osVersion;

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
