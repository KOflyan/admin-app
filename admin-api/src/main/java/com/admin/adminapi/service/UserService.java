package com.admin.adminapi.service;

import com.admin.adminapi.entity.base.User;
import com.admin.adminapi.service.base.GenericService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserService extends GenericService<User> {
}
