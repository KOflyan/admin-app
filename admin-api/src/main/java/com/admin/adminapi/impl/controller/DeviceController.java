package com.admin.adminapi.impl.controller;

import com.admin.adminapi.base.controller.GenericController;
import com.admin.adminapi.impl.dao.entities.Device;
import com.admin.adminapi.impl.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@EnableAutoConfiguration
@RequestMapping("/device")
public class DeviceController extends GenericController<Device> {

    @Autowired
    public DeviceController(DeviceService service) {
        super(service);
    }
}
