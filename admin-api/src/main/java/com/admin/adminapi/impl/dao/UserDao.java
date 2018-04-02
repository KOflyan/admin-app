package com.admin.adminapi.impl.dao;

import com.admin.adminapi.base.dao.Dao;
import com.admin.adminapi.base.dao.entities.AbstractUser;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import java.util.List;

@Repository
public class UserDao extends Dao<AbstractUser> {

    @Override
    public AbstractUser find(Long id) {
        return em.createNamedQuery("User.getById", AbstractUser.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public List<AbstractUser> findAll() {
        return em.createNamedQuery("User.getAll", AbstractUser.class)
                .getResultList();
    }

    public void setActiveState(Long id, boolean isActive) throws NoResultException {

        find(id);

        em.createQuery("UPDATE User u SET u.isActive = :isActive WHERE u.id = :id")
                .setParameter("isActive", isActive)
                .setParameter("id", id)
                .executeUpdate();
    }
}
