package com.example.configurationservice.service.impl;

import com.example.configurationservice.mapper.MapHelper;
import com.example.configurationservice.model.Parameter;
import com.example.configurationservice.repository.GenericRepository;
import com.example.configurationservice.repository.ParameterRepository;
import com.example.configurationservice.service.ParameterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ParameterServiceImpl extends GenericServiceImpl<Parameter> implements ParameterService {

    private final ParameterRepository parameterRepository;

    public ParameterServiceImpl(GenericRepository<Parameter> genericRepository, MapHelper mapHelper, ParameterRepository parameterRepository) {
        super(genericRepository, mapHelper);
        this.parameterRepository = parameterRepository;
    }

}
