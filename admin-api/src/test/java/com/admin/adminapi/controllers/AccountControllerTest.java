package com.admin.adminapi.controllers;

import com.admin.adminapi.TestBase;
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
public class AccountControllerTest extends TestBase {

    @Autowired
    protected TestRestTemplate restTemplate;

    @Autowired
    private AccountController controller;

    @Mock
    private AccountService serviceMock;


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

        ResponseEntity<ExtendedAccount> actual = restTemplate.exchange("/account/" + id,
                HttpMethod.GET, entity, ExtendedAccount.class);


        assertEquals(expected, actual.getBody());
    }

    @Test
    public void findAllAccounts() {
        List<AbstractAccount> expected = manager.createQuery("SELECT a FROM Account a", AbstractAccount.class)
                .getResultList();

        ResponseEntity<Account[]> actual = restTemplate.exchange("/account/all",
                HttpMethod.GET, entity, Account[].class);

        assertEquals(expected, Arrays.asList(actual.getBody()));
    }

    @Test
    public void testPagination() {
        List<AbstractAccount> expected = manager.createQuery("SELECT a FROM Account a", AbstractAccount.class)
                .setFirstResult(10)
                .setMaxResults(10)
                .getResultList();

        ResponseEntity<Account[]> actual = restTemplate.exchange("/account/all?skip=10&limit=10",
                HttpMethod.GET, entity, Account[].class);

        assertEquals(expected, Arrays.asList(actual.getBody()));
    }

    @Test
    public void countAccountsByType() {
        List<AbstractAccount> expected = manager.createNamedQuery("Account.countByType", AbstractAccount.class)
                .getResultList();

        ResponseEntity<Account[]> actual = restTemplate.exchange("/account/countByType",
                HttpMethod.GET, entity, Account[].class);

        assertEquals(expected, Arrays.asList(actual.getBody()));
    }

    @Test
    public void count() {
        Long expected = manager.createQuery("SELECT COUNT(a) FROM Account a", Long.class)
                .getSingleResult();

        ResponseEntity<Long> actual = restTemplate.exchange("/account/count",
                HttpMethod.GET, entity, Long.class);

        assertEquals(expected, actual.getBody());
    }
}
