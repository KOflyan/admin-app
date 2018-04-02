package com.admin.adminapi.impl.service;

import com.admin.adminapi.base.dao.entities.AbstractAccount;
import com.admin.adminapi.base.service.GenericService;
import com.admin.adminapi.impl.dao.AccountDao;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;

@Service
@Transactional
public class AccountService extends GenericService<AbstractAccount> {


    public void setActivateState(Long id, boolean isActive) throws NoResultException {
        ((AccountDao) dao).setActiveState(id, isActive);
    }
}
