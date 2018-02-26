package com.admin.adminapi.dto;

import com.admin.adminapi.dto.base.Dto;
import com.admin.adminapi.entity.Account;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Component
public class AccountDto extends Dto<Account> {

    @NotNull
    @Min(1)
    @Getter @Setter private int userId;

    @NotNull
    @Size(min = 4, max = 25)
    @Getter @Setter private String accountName;

    @NotNull
    @Getter @Setter private boolean isActive;

    @NotNull
    @Size(min = 3, max = 50)
    @Getter @Setter private String type;

    public AccountDto() {
    }

    public AccountDto(int userId, String accountName, boolean isActive, String type) {
        this.userId = userId;
        this.accountName = accountName;
        this.isActive = isActive;
        this.type = type;
    }

    @Override
    public Account get() {
        return new Account(userId, accountName, isActive, type);
    }

}
