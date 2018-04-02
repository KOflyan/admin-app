package com.admin.adminapi.impl.dao;

import com.admin.adminapi.base.dao.BaseRepository;
import com.admin.adminapi.base.dao.entities.AbstractUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends BaseRepository<AbstractUser> {

    @Query(
            "SELECT u " +
                    "FROM ExtendedUser u " +
                    "LEFT JOIN FETCH u.account a " +
                    "LEFT JOIN FETCH u.devices d " +
                    "WHERE u.id = :id " +
                    "GROUP BY u.id, d.id " +
                    "ORDER BY u.id"
    )
    AbstractUser findOne(Long id);


    @Query(
            "SELECT u " +
                    "FROM ExtendedUser u " +
                    "LEFT JOIN FETCH u.account a " +
                    "LEFT JOIN FETCH u.devices d " +
                    "GROUP BY u.id, d.id " +
                    "ORDER BY u.id"
    )
    List<AbstractUser> findAll();

    @Query("DELETE FROM User u WHERE u.id = :id")
    void delete(Long id);

    AbstractUser save(AbstractUser user);
}
