package com.adminapp.service;

import com.adminapp.dao.UserDao;
import com.adminapp.entity.User;
import lombok.Getter;
import lombok.Setter;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AdminAppService {

    private final Logger logger = Logger.getLogger(AdminAppService.class);


    @Getter @Setter private UserDao userDao;

    @Autowired
    public AdminAppService(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<User> getAllUsers() {
        return userDao.getAll();
    }
}
