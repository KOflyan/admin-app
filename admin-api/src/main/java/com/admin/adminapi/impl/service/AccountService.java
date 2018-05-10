package com.admin.adminapi.impl.service;

import com.admin.adminapi.base.dao.entities.AbstractAccount;
import com.admin.adminapi.base.service.GenericService;
import com.admin.adminapi.impl.dao.AccountDao;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AccountService extends GenericService<AbstractAccount> {

    public AccountService(AccountDao dao) {
        super(dao);
    }

    public List<AbstractAccount> countAccountsByType() {
        return ((AccountDao) dao).countAccountsByType();
    }
}
