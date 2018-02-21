package com.admin.adminapi.service;

import com.admin.adminapi.dao.base.Dao;
import com.admin.adminapi.entity.Account;
import com.admin.adminapi.entity.Admin;
import com.admin.adminapi.entity.Device;
import com.admin.adminapi.entity.User;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AdminApiService {

    private final Logger logger = Logger.getLogger(AdminApiService.class);

    @Autowired
    private Dao<User> userDao;
    @Autowired
    private Dao<Account> accountDao;
    @Autowired
    private Dao<Device> deviceDao;
    @Autowired
    private Dao<Admin> adminDao;


    public List<User> getAllUsers() {
        return userDao.getAll();
    }

    public User getUserById(int id) {
        return userDao.getById(id);
    }

    public void deleteUserById(int id) {
        userDao.delete(id);
    }

    public List<Account> getAllAccounts() {
        return accountDao.getAll();
    }

    public Account getAccountById(int id) {
        return accountDao.getById(id);
    }

    public void deleteAccountById(int id) {
        accountDao.delete(id);
    }

    public List<Device> getAllDevices() {
        return deviceDao.getAll();
    }

    public Device getDeviceById(int id) {
        return deviceDao.getById(id);
    }

    public void deleteDeviceById(int id) {
        deviceDao.delete(id);
    }

    public List<Admin> getAllAdmins() {
        return adminDao.getAll();
    }

    public Admin getAdminById(int id) {
        return adminDao.getById(id);
    }

    public void deleteAdminById(int id) {
        adminDao.delete(id);
    }

}
