package com.example.configurationservice.service.impl;

import com.example.configurationservice.mapper.MapHelper;
import com.example.configurationservice.model.Parameter;
import com.example.configurationservice.repository.GenericRepository;
import com.example.configurationservice.repository.ParameterRepository;
import com.example.configurationservice.service.ParameterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ParameterServiceImpl extends GenericServiceImpl<Parameter> implements ParameterService {

    private final ParameterRepository parameterRepository;

    public ParameterServiceImpl(GenericRepository<Parameter> genericRepository, MapHelper mapHelper, ParameterRepository parameterRepository) {
        super(genericRepository, mapHelper);
        this.parameterRepository = parameterRepository;
    }

    @Override
    public List<Parameter> search(String name, String code, int page, int size) {
        return null;
    }

    @Override
    public Parameter findByCode(String code) {
        return null;
    }
}
