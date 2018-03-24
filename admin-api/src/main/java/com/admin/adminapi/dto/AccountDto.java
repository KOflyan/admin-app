package com.admin.adminapi.dto;

import com.admin.adminapi.dto.base.Dto;
import com.admin.adminapi.entity.SimpleAccount;
import com.admin.adminapi.entity.base.Account;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Component
@Getter @Setter
public class AccountDto extends Dto<Account> {

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

    public AccountDto(String accountName, boolean isActive, String type) {
        this.accountName = accountName;
        this.isActive = isActive;
        this.type = type;
    }

    @Override
    public Account get() {
        return new SimpleAccount(accountName, isActive, type);
    }

}
