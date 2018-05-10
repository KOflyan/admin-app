package com.admin.adminapi.impl.dao;

import com.admin.adminapi.base.dao.Dao;
import com.admin.adminapi.base.dao.entities.AbstractUser;
import com.admin.adminapi.impl.dao.entities.User;
import com.admin.adminapi.impl.dao.entities.extended.ExtendedUser;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Repository
public class UserDao extends Dao<AbstractUser> {

    @Override
    public AbstractUser find(Long id) {
        return em.createNamedQuery("User.getById", ExtendedUser.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public List<AbstractUser> search(String searchText) {
        return em.createNamedQuery("User.search", AbstractUser.class)
                .setParameter("searchText", searchText)
                .getResultList();
    }


    @Override
    public List<AbstractUser> findAll() {
        return em.createNamedQuery("User.getAll", AbstractUser.class)
                .getResultList();
    }

    public Long countRecentUsers(String interval) {

        LocalDate now = LocalDate.now();

        return em.createNamedQuery("User.countRecent", Long.class)
                .setParameter("startDate", getStartDate(now, interval))
                .setParameter("endDate", Date.valueOf(now))
                .getSingleResult();
    }

    public List<AbstractUser> countUsersByLanguage() {
        return em.createNamedQuery("User.countByLanguage", AbstractUser.class)
                .getResultList();
    }

    private Date getStartDate(LocalDate now, String interval) {
        LocalDate startDate = null;

        switch (interval) {
            case "week":
                startDate = now.minusDays(7);
                break;

            case "month":
                startDate = now.minusWeeks(1);
                break;

            case "year":
                startDate = now.minusYears(1);
                break;
        }

        if (startDate == null) {
            throw new IllegalArgumentException("Wrong interval: " + interval);
        }

        return Date.valueOf(startDate);
    }
}
