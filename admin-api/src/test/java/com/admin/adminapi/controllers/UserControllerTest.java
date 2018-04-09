package com.admin.adminapi.controllers;

import com.admin.adminapi.impl.controller.UserController;
import com.admin.adminapi.impl.dao.entities.User;
import com.admin.adminapi.impl.dao.entities.extended.ExtendedUser;
import com.admin.adminapi.impl.service.UserService;
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
public class UserControllerTest {
    private final Long MAX_VALUE = 20L;
    private final Long MIN_VALUE = 1L;

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private UserController controller;

    @Autowired
    private TestRestTemplate restTemplate;

    @Mock
    private UserService serviceMock;


    @Test
    public void contextLoads() {
        assertThat(controller).isNotNull();
    }

    @Test
    public void controllerUsesService() {
        Long id = ThreadLocalRandom.current().nextLong(MIN_VALUE, MAX_VALUE);

        UserController controller = new UserController(serviceMock);

        User expected = new User();
        expected.setName("Test user");
        when(serviceMock.find(id)).thenReturn(expected);

        assertEquals(expected, controller.find(id));
    }

    @Test
    public void findSingleUser() {

        Long id = ThreadLocalRandom.current().nextLong(MIN_VALUE, MAX_VALUE);

        ExtendedUser expected = manager.createNamedQuery("User.getById", ExtendedUser.class)
                .setParameter("id", id)
                .getSingleResult();

        ResponseEntity<ExtendedUser> actual = restTemplate.getForEntity("/user/" + id, ExtendedUser.class);


        assertEquals(expected, actual.getBody());
    }

    @Test
    public void findAllUsers() {
        List<ExtendedUser> expected = manager.createNamedQuery("User.getAll", ExtendedUser.class)
                .getResultList();

        ResponseEntity<ExtendedUser[]> actual = restTemplate.getForEntity("/user/all", ExtendedUser[].class);

        assertEquals(expected, Arrays.asList(actual.getBody()));
    }

    @Test
    public void deleteNonExistentAccountShouldReturn404() {
        ResponseEntity responseEntity = controller.delete(Long.MAX_VALUE);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }
}
