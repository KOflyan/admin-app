package com.admin.adminapi.controllers;

import com.admin.adminapi.TestBase;
import com.admin.adminapi.impl.controller.DeviceController;
import com.admin.adminapi.impl.dao.entities.Device;
import com.admin.adminapi.impl.service.DeviceService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@EnableAutoConfiguration
public class DeviceControllerTest extends TestBase {

    @Autowired
    protected TestRestTemplate restTemplate;

    @Autowired
    private DeviceController controller;

    @Mock
    private DeviceService serviceMock;


    @Test
    public void contextLoads() {
        assertThat(controller).isNotNull();
    }

    @Test
    public void controllerUsesService() {
        Long id = ThreadLocalRandom.current().nextLong(MIN_VALUE, MAX_VALUE);

        DeviceController controller = new DeviceController(serviceMock);

        Device expected = new Device();
        expected.setDeviceName("Test device");
        when(serviceMock.find(id)).thenReturn(expected);

        assertEquals(expected, controller.find(id));
    }

    @Test
    public void findSingleDevice() {

        Long id = ThreadLocalRandom.current().nextLong(MIN_VALUE, MAX_VALUE);

        Device expected = manager.createQuery("SELECT d FROM Device d WHERE d.id = :id", Device.class)
                .setParameter("id", id)
                .getSingleResult();

        ResponseEntity<Device> actual = restTemplate.exchange("/device/" + id,
                HttpMethod.GET, entity, Device.class);


        assertEquals(expected, actual.getBody());
    }

    @Test
    public void findAllDevices() {
        List<Device> expected = manager.createQuery("SELECT d FROM Device d", Device.class)
                .getResultList();

        ResponseEntity<Device[]> actual = restTemplate.exchange("/device/all",
                HttpMethod.GET, entity, Device[].class);

        assertEquals(expected, Arrays.asList(actual.getBody()));
    }

    @Test
    public void testCountByFamily() {
        List<Device> expected = manager.createNamedQuery("Device.countByFamily", Device.class)
                .getResultList();

        ResponseEntity<Device[]> actual = restTemplate.exchange("/device/countByFamily",
                HttpMethod.GET, entity, Device[].class);

        assertEquals(expected, Arrays.asList(actual.getBody()));
    }
}
