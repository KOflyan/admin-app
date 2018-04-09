package com.admin.adminapi.base.dto;


import com.admin.adminapi.base.dao.entities.AbstractEntity;
import com.admin.adminapi.impl.dto.AccountDto;
import com.admin.adminapi.impl.dto.AdminDto;
import com.admin.adminapi.impl.dto.DeviceDto;
import com.admin.adminapi.impl.dto.UserDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo( use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = UserDto.class, name = "user"),
        @JsonSubTypes.Type(value = AccountDto.class, name = "account"),
        @JsonSubTypes.Type(value = DeviceDto.class, name = "device"),
        @JsonSubTypes.Type(value = AdminDto.class, name = "admin")
})
@Getter @Setter
public abstract class Dto<T extends AbstractEntity> implements Serializable {

    @JsonProperty
    private String type;

    public abstract T get();


}