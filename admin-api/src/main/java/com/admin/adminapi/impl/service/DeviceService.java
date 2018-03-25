package com.admin.adminapi.impl.service;


import com.admin.adminapi.base.service.GenericService;
import com.admin.adminapi.impl.dao.entities.Device;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class DeviceService extends GenericService<Device> {
}
