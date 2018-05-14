package com.admin.adminapi.impl.controller;

import com.admin.adminapi.base.controller.GenericController;
import com.admin.adminapi.impl.dao.entities.Device;
import com.admin.adminapi.impl.service.DeviceService;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Set;

@Controller
@EnableAutoConfiguration
@RequestMapping("/device")
public class DeviceController extends GenericController<Device> {

    public DeviceController(DeviceService service) {
        super(service);
    }


    @RequestMapping(method = RequestMethod.GET, path = "/countByFamily")
    public @ResponseBody Set<Device> getUsers() {
        return ((DeviceService) service).countDevicesByFamily();
    }
}
