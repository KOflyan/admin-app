package com.admin.adminapi.controllers;

import com.admin.adminapi.base.dao.entities.AbstractAccount;
import com.admin.adminapi.impl.controller.AccountController;
import com.admin.adminapi.impl.dao.entities.Account;
import com.admin.adminapi.impl.dao.entities.extended.ExtendedAccount;
import com.admin.adminapi.impl.service.AccountService;
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
public class AccountControllerTest {

    private final Long MAX_VALUE = 20L;
    private final Long MIN_VALUE = 1L;

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private AccountController controller;

    @Autowired
    private TestRestTemplate restTemplate;

    @Mock
    private AccountService serviceMock;

    public AccountControllerTest() {
    }

    @Test
    public void contextLoads() {
        assertThat(controller).isNotNull();
    }

    @Test
    public void controllerUsesService() {
        Long id = ThreadLocalRandom.current().nextLong(MIN_VALUE, MAX_VALUE);

        AccountController controller = new AccountController(serviceMock);

        AbstractAccount expected = new Account();
        expected.setName("Test account");
        when(serviceMock.find(id)).thenReturn(expected);

        assertEquals(expected, controller.find(id));
    }

    @Test
    public void findSingleAccount() {

        Long id = ThreadLocalRandom.current().nextLong(MIN_VALUE, MAX_VALUE);

        ExtendedAccount expected = manager.createNamedQuery("Account.getById", ExtendedAccount.class)
                .setParameter("id", id)
                .getSingleResult();

        ResponseEntity<ExtendedAccount> actual = restTemplate.getForEntity("/account/" + id, ExtendedAccount.class);


        assertEquals(expected, actual.getBody());
    }

    @Test
    public void findAllAccounts() {
        List<AbstractAccount> expected = manager.createQuery("SELECT a FROM Account a", AbstractAccount.class)
                .getResultList();

        ResponseEntity<Account[]> actual = restTemplate.getForEntity("/account/all", Account[].class);

        assertEquals(expected, Arrays.asList(actual.getBody()));
    }

    @Test
    public void deleteNonExistentAccountShouldReturn404() {
        ResponseEntity responseEntity = controller.delete(Long.MAX_VALUE);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    public void testPagination() {
        List<AbstractAccount> expected = manager.createQuery("SELECT a FROM Account a", AbstractAccount.class)
                .setFirstResult(10)
                .setMaxResults(10)
                .getResultList();

        ResponseEntity<Account[]> actual = restTemplate.getForEntity("/account/all?skip=10&limit=10", Account[].class);

        assertEquals(expected, Arrays.asList(actual.getBody()));
    }
}
