package com.admin.adminapi.impl.dao;

import com.admin.adminapi.base.dao.Dao;
import com.admin.adminapi.base.dao.entities.AbstractUser;
import com.admin.adminapi.impl.dao.entities.extended.ExtendedUser;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Repository
public class UserDao extends Dao<AbstractUser> {

    @Override
    public AbstractUser find(Long id) {
        System.out.println(id);
        return em.createNamedQuery("User.getById", ExtendedUser.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public Set<AbstractUser> search(String searchText) {
        return new LinkedHashSet<>(em.createNamedQuery("User.search", AbstractUser.class)
                .setParameter("searchText", searchText)
                .getResultList());
    }


    @Override
    public Set<AbstractUser> findAll() {
        return new LinkedHashSet<>(em.createNamedQuery("User.getAll", AbstractUser.class)
                .getResultList());
    }

    public Long countRecentUsers(String interval) {

        LocalDate now = LocalDate.now();

        return em.createNamedQuery("User.countRecent", Long.class)
                .setParameter("startDate", getStartDate(now, interval))
                .setParameter("endDate", Date.valueOf(now))
                .getSingleResult();
    }

    public Set<AbstractUser> countUsersByLanguage() {
        return new LinkedHashSet<>(em.createNamedQuery("User.countByLanguage", AbstractUser.class)
                .getResultList());
    }

    private Date getStartDate(LocalDate now, String interval) {
        LocalDate startDate = null;

        switch (interval) {
            case "week":
                startDate = now.minusWeeks(1);
                break;

            case "month":
                startDate = now.minusMonths(1);
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
