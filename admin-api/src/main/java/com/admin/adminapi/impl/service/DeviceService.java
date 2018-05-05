package com.admin.adminapi.impl.service;


import com.admin.adminapi.base.service.GenericService;
import com.admin.adminapi.impl.dao.DeviceDao;
import com.admin.adminapi.impl.dao.entities.Device;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class DeviceService extends GenericService<Device> {

    public DeviceService(DeviceDao dao) {
        super(dao);
    }

    public List<Device> countDevicesByFamily() {
        return ((DeviceDao) dao).countDevicesByFamily();
    }
}
