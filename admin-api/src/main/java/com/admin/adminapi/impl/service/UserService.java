package com.admin.adminapi.impl.service;

import com.admin.adminapi.base.dao.entities.AbstractUser;
import com.admin.adminapi.base.service.GenericService;
import com.admin.adminapi.impl.dao.UserDao;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;

@Service
@Transactional
public class UserService extends GenericService<AbstractUser> {

    public UserService(UserDao dao) {
        super(dao);
    }

    public Long countRecentUsers(String interval) {
        return ((UserDao) dao).countRecentUsers(interval);
    }

    public Set<AbstractUser> countUsersByLanguage() {
        return ((UserDao) dao).countUsersByLanguage();
    }
}
