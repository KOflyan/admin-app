package com.admin.adminapi.service;

import com.admin.adminapi.entity.Admin;
import com.admin.adminapi.service.base.GenericService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class AdminService extends GenericService<Admin> {
}
