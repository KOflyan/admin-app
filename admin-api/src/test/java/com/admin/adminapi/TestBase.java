package com.admin.adminapi;

import com.admin.adminapi.utils.Utils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class TestBase {

    protected final Long MAX_VALUE = 20L;
    protected final Long MIN_VALUE = 1L;
    protected HttpEntity entity;

    @PersistenceContext
    protected EntityManager manager;


    protected TestBase() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", Utils.generateToken("admin", "admin"));
        entity = new HttpEntity(headers);
    }
}
