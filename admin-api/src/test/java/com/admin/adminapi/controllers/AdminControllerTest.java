package com.admin.adminapi.controllers;

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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@EnableAutoConfiguration
public class AdminControllerTest {

    private final Long MAX_VALUE = 2L;
    private final Long MIN_VALUE = 1L;

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private AdminController controller;

    @Autowired
    private TestRestTemplate restTemplate;

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

        Long id = ThreadLocalRandom.current().nextLong(MIN_VALUE, MAX_VALUE);

        Admin expected = manager.createQuery("SELECT a FROM Admin a WHERE a.id = :id", Admin.class)
                .setParameter("id", id)
                .getSingleResult();

        ResponseEntity<Admin> actual = restTemplate.getForEntity("/admin/" + id, Admin.class);


        assertEquals(expected, actual.getBody());
    }

    @Test
    public void findAllAdmins() {
        List<Admin> expected = manager.createQuery("SELECT a FROM Admin a", Admin.class)
                .getResultList();

        ResponseEntity<Admin[]> actual = restTemplate.getForEntity("/admin/all", Admin[].class);

        assertEquals(expected, Arrays.asList(actual.getBody()));
    }

    @Test
    public void deleteNonExistentAccountShouldReturn404() {
        ResponseEntity responseEntity = controller.delete(Long.MAX_VALUE);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }
}
