package com.example.configurationservice.service;

import com.example.configurationservice.exception.ElementAlreadyExistsException;
import com.example.configurationservice.exception.ElementNotFoundException;
import com.example.configurationservice.model.BaseEntity;

import java.util.List;

public interface GenericService <T extends BaseEntity>{

    T update(final Long id, final T entity) throws ElementNotFoundException;

    <S extends Object> T partialUpdate(final Long id, final S patch) throws ElementNotFoundException;


    T findById(final Long id) throws ElementNotFoundException;

    T save(final T entity) throws ElementAlreadyExistsException;

    boolean delete(final Long id) throws ElementNotFoundException;

    List<T> findAll(final int page, final int size);
}
