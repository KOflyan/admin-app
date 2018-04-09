package com.admin.adminapi.impl.dao;

import com.admin.adminapi.base.dao.Dao;
import com.admin.adminapi.base.dao.entities.AbstractAccount;
import com.admin.adminapi.impl.dao.entities.extended.ExtendedAccount;
import org.springframework.stereotype.Repository;

@Repository
public class AccountDao extends Dao<AbstractAccount> {

    @Override
    public AbstractAccount find(Long id) {
        return em.createNamedQuery("Account.getById", ExtendedAccount.class)
                .setParameter("id", id)
                .getSingleResult();
    }
}
