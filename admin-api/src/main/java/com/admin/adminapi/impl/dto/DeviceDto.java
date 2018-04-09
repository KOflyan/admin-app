package com.admin.adminapi.impl.dto;

import com.admin.adminapi.base.dto.Dto;
import com.admin.adminapi.impl.dao.entities.Device;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Component
@Getter @Setter
public class DeviceDto extends Dto<Device> {

    private Long id;

    @NotNull
    private int accountId;

    @NotNull
    private int userId;

    @NotNull
    @Size(min = 3)
    private String deviceName;

    @NotNull
    @Size(min = 3)
    private String family;

    @NotNull
    @Size(min = 3)
    private String osVersion;

    public DeviceDto() {
    }

    public DeviceDto(int accountId, int userId, String deviceName, String family, String osVersion) {
        this.accountId = accountId;
        this.userId = userId;
        this.deviceName = deviceName;
        this.family = family;
        this.osVersion = osVersion;
    }

    @Override
    public Device get() {
        return new Device(id, accountId, userId, deviceName, family, osVersion);
    }
}
