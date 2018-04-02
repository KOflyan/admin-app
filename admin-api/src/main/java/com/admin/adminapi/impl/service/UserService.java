package com.admin.adminapi.impl.service;

import com.admin.adminapi.base.dao.entities.AbstractUser;
import com.admin.adminapi.base.service.GenericService;
import com.admin.adminapi.impl.dao.UserDao;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;

@Service
@Transactional
public class UserService extends GenericService<AbstractUser> {

    public void setActivateState(Long id, boolean isActive) throws NoResultException {
        ((UserDao) dao).setActiveState(id, isActive);
    }
}
