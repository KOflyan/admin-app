package com.admin.adminapi.service;

import com.admin.adminapi.entity.base.Account;
import com.admin.adminapi.service.base.GenericService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class AccountService extends GenericService<Account> {
}
