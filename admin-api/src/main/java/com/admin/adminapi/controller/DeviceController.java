package com.admin.adminapi.controller;

import com.admin.adminapi.controller.base.GenericController;
import com.admin.adminapi.entity.Device;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Controller
@EnableAutoConfiguration
@RequestMapping("/device")
public class DeviceController extends GenericController<Device> {
}
