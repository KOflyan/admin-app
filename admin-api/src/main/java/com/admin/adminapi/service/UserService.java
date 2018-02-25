package com.admin.adminapi.service;

import com.admin.adminapi.entity.User;
import com.admin.adminapi.service.base.AdminApiService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserService extends AdminApiService<User> {
}
