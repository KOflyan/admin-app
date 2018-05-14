package com.admin.adminapi.impl.dao;

import com.admin.adminapi.base.dao.Dao;
import com.admin.adminapi.base.dao.entities.AbstractAccount;
import com.admin.adminapi.impl.dao.entities.extended.ExtendedAccount;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashSet;
import java.util.Set;

@Repository
public class AccountDao extends Dao<AbstractAccount> {

    @Override
    public AbstractAccount find(Long id) {
        return em.createNamedQuery("Account.getById", ExtendedAccount.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public Set<AbstractAccount> search(String searchText) {
        return new LinkedHashSet<>(em.createNamedQuery("Account.search", AbstractAccount.class)
                .setParameter("searchText", searchText)
                .getResultList());
    }

    public Set<AbstractAccount> countAccountsByType() {
        return new LinkedHashSet<>(em.createNamedQuery("Account.countByType", AbstractAccount.class)
                .getResultList());
    }

}
