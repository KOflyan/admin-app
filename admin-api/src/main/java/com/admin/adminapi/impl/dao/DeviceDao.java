package com.admin.adminapi.impl.dao;


import com.admin.adminapi.base.dao.Dao;
import com.admin.adminapi.impl.dao.entities.Device;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashSet;
import java.util.Set;

@Repository
public class DeviceDao extends Dao<Device> {


    public Set<Device> countDevicesByFamily() {
        return new LinkedHashSet<>(em.createNamedQuery("Device.count", Device.class)
                .getResultList());

    }

    @Override
    public Set<Device> search(String searchText) {
        return new LinkedHashSet<>(em.createNamedQuery("Device.search", Device.class)
                .setParameter("searchText", searchText)
                .getResultList());
    }
}
