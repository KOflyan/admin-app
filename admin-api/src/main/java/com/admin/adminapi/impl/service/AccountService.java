package com.admin.adminapi.impl.service;

import com.admin.adminapi.base.dao.entities.AbstractAccount;
import com.admin.adminapi.base.service.GenericService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class AccountService extends GenericService<AbstractAccount> {
}
