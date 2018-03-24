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
@Getter @Setter
public class DeviceDto extends Dto<Device> {

    @NotNull
    @Min(1)
    private int accountId;

    @NotNull
    @Size(min = 1)
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
        return new Device(accountId, userId, deviceName, family, osVersion);
    }
}
