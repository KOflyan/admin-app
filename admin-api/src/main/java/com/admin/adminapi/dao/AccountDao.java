package com.admin.adminapi.dao;

import com.admin.adminapi.dao.base.Dao;
import com.admin.adminapi.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AccountDao extends Dao<Account> {
}
