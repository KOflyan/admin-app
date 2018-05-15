package com.admin.adminapi.controllers;

import com.admin.adminapi.TestBase;
import com.admin.adminapi.impl.controller.AdminController;
import com.admin.adminapi.impl.dao.entities.Admin;
import com.admin.adminapi.impl.service.AdminService;
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
public class AdminControllerTest extends TestBase {

    @Autowired
    protected TestRestTemplate restTemplate;

    @Autowired
    private AdminController controller;

    @Mock
    private AdminService serviceMock;


    @Test
    public void contextLoads() {
        assertThat(controller).isNotNull();
    }

    @Test
    public void controllerUsesService() {
        Long id = ThreadLocalRandom.current().nextLong(MIN_VALUE, MAX_VALUE);

        AdminController controller = new AdminController(serviceMock);

        Admin expected = new Admin();
        expected.setName("Test admin");
        when(serviceMock.find(id)).thenReturn(expected);

        assertEquals(expected, controller.find(id));
    }

    @Test
    public void findSingleAdmin() {

        Long id = ThreadLocalRandom.current().nextLong(1, 3);

        Admin expected = manager.createQuery("SELECT a FROM Admin a WHERE a.id = :id", Admin.class)
                .setParameter("id", id)
                .getSingleResult();

        ResponseEntity<Admin> actual = restTemplate.exchange("/admin/" + id,
                HttpMethod.GET, entity, Admin.class);

        assertEquals(expected, actual.getBody());
    }

    @Test
    public void findAllAdmins() {
        List<Admin> expected = manager.createQuery("SELECT a FROM Admin a", Admin.class)
                .getResultList();

        ResponseEntity<Admin[]> actual = restTemplate.exchange("/admin/all",
                HttpMethod.GET, entity, Admin[].class);

        assertEquals(expected, Arrays.asList(actual.getBody()));
    }

}
