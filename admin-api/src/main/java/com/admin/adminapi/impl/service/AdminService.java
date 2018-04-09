package com.admin.adminapi.impl.service;

import com.admin.adminapi.base.service.GenericService;
import com.admin.adminapi.impl.dao.AdminDao;
import com.admin.adminapi.impl.dao.entities.Admin;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class AdminService extends GenericService<Admin> {

    public AdminService(AdminDao dao) {
        super(dao);
    }
}
