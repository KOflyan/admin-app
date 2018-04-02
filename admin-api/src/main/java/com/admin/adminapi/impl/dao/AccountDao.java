package com.admin.adminapi.impl.dao;

import com.admin.adminapi.base.dao.Dao;
import com.admin.adminapi.base.dao.entities.AbstractAccount;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;

@Repository
public class AccountDao extends Dao<AbstractAccount> {

    @Override
    public AbstractAccount find(Long id) {
        return em.createNamedQuery("Account.getById", AbstractAccount.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    public void setActiveState(Long id, boolean isActive) throws NoResultException {

        find(id);

        em.createQuery("UPDATE Account a SET a.isActive = :isActive WHERE a.id = :id")
                .setParameter("isActive", isActive)
                .setParameter("id", id)
                .executeUpdate();
    }
}
