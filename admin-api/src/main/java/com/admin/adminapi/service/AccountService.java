package com.admin.adminapi.service;

import com.admin.adminapi.entity.Account;
import com.admin.adminapi.service.base.AdminApiService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class AccountService extends AdminApiService<Account> {
}
