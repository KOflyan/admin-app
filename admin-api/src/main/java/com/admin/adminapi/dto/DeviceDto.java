package com.admin.adminapi.dto;

import com.admin.adminapi.dto.base.Dto;
import com.admin.adminapi.entity.Device;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Component
public class DeviceDto extends Dto<Device> {

    @NotNull
    @Min(1)
    @Getter @Setter private int accountId;

    @NotNull
    @Size(min = 3)
    @Getter @Setter private String deviceName;

    @NotNull
    @Size(min = 3)
    @Getter @Setter private String family;

    @NotNull
    @Size(min = 3)
    @Getter @Setter private String osVersion;

    public DeviceDto() {
    }

    public DeviceDto(int accountId, String deviceName, String family, String osVersion) {
        this.accountId = accountId;
        this.deviceName = deviceName;
        this.family = family;
        this.osVersion = osVersion;
    }

    @Override
    public Device get() {
        return new Device(accountId, deviceName, family, osVersion);
    }
}
