package com.admin.adminapi.impl.dto;

import com.admin.adminapi.base.dao.entities.AbstractAccount;
import com.admin.adminapi.base.dto.Dto;
import com.admin.adminapi.impl.dao.entities.Account;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Component
@Getter @Setter
public class AccountDto extends Dto<AbstractAccount> {

    private Long id;

    @NotNull
    @Size(min = 3, max = 25)
    private String name;

    private boolean active;

    @NotNull
    @Size(min = 3, max = 50)
    private String accountType;

    public AccountDto() {
    }

    public AccountDto(String name, String accountType) {
        this.name = name;
        this.accountType = accountType;
    }

    @Override
    public AbstractAccount get() {
        return new Account(id, name, active, accountType);
    }

}
