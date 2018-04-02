package com.admin.adminapi.impl.dao;

import com.admin.adminapi.base.dao.BaseRepository;
import com.admin.adminapi.base.dao.entities.AbstractAccount;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends BaseRepository<AbstractAccount> {

    @Query(
            "SELECT a " +
            "FROM ExtendedAccount a " +
                "LEFT JOIN FETCH a.devices d " +
                "LEFT JOIN FETCH a.users u " +
            "WHERE a.id = :id " +
            "GROUP BY a.id, u.id, d.id"
    )
    AbstractAccount getByid(Long id);
}
