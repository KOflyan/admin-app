package com.adminapp.service;

import com.adminapp.dao.AccountDao;
import com.adminapp.dao.AdminDao;
import com.adminapp.dao.DeviceDao;
import com.adminapp.dao.UserDao;
import com.adminapp.entity.Account;
import com.adminapp.entity.Admin;
import com.adminapp.entity.Device;
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
    @Getter @Setter private AccountDao accountDao;
    @Getter @Setter private DeviceDao deviceDao;
    @Getter @Setter private AdminDao adminDao;


    @Autowired
    public AdminAppService(UserDao userDao, AccountDao accountDao, DeviceDao deviceDao, AdminDao adminDao) {
        this.userDao = userDao;
        this.accountDao = accountDao;
        this.deviceDao = deviceDao;
        this.adminDao = adminDao;
    }


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
