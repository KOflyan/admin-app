package com.admin.adminapi.impl.service;

import com.admin.adminapi.base.dao.entities.AbstractAccount;
import com.admin.adminapi.base.service.GenericService;
import com.admin.adminapi.impl.dao.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class AccountService extends GenericService<AbstractAccount> {


    @Autowired
    public AccountService(AccountRepository repository) {
        super(repository);
    }
}
