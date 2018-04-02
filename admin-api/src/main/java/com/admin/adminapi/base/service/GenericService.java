package com.admin.adminapi.base.service;

import com.admin.adminapi.base.dao.BaseRepository;
import com.admin.adminapi.base.dao.entities.AbstractEntity;
import com.admin.adminapi.base.dto.Dto;

import java.util.List;

public abstract class GenericService<T extends AbstractEntity> {

    protected BaseRepository<T> repository;

    public GenericService(BaseRepository<T> repository) {
        this.repository = repository;
    }

    public T getById(Long id) {
        return repository.findOne(id);
    }

//    public List<T> getAll(int skip, int limit) {
//        return repository.getAll(skip, limit);
//    }

    public List<T> getAll() {
        return repository.findAll();
    }

    public void delete(Long id) {
        repository.delete(id);
    }

    public void createOrUpdate(Dto<T> dto) {
        repository.save(dto.get());
    }
}
