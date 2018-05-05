package com.admin.adminapi.impl.dao;


import com.admin.adminapi.base.dao.Dao;
import com.admin.adminapi.impl.dao.entities.Device;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DeviceDao extends Dao<Device> {


    public List<Device> countDevicesByFamily() {
        return em.createNamedQuery("Device.count", Device.class)
                .getResultList();

    }
}
