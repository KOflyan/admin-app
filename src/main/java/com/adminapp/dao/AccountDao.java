package com.adminapp.dao;

import com.adminapp.dao.base.Dao;
import com.adminapp.entity.Account;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AccountDao extends Dao<Account> {

    @Override
    public List<Account> getAll() {
        return null;
    }

    @Override
    public Account getById(int id) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void update(Account account) {

    }

    @Override
    public void add(Account account) {

    }
}
