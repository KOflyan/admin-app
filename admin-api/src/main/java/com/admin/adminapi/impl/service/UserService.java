package com.admin.adminapi.impl.service;

import com.admin.adminapi.base.dao.entities.AbstractUser;
import com.admin.adminapi.base.service.GenericService;
import com.admin.adminapi.impl.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserService extends GenericService<AbstractUser> {

    @Autowired
    public UserService(UserRepository repository) {
        super(repository);
    }
}
