package com.admin.adminapi.impl.dao;

import com.admin.adminapi.base.dao.Dao;
import com.admin.adminapi.base.dao.entities.AbstractAccount;
import org.springframework.stereotype.Repository;

@Repository
public class AccountDao extends Dao<AbstractAccount> {

    @Override
    public AbstractAccount getById(int id) {
        return em.createNamedQuery("Account.getById", AbstractAccount.class)
                .setParameter("id", id)
                .getSingleResult();
    }

//
//    @Override
//    public List<AbstractAccount> getAll() {
//        return em.createNamedQuery("AbstractAccount.getById", AbstractAccount.class)
//                .getResultList();
//    }
}
