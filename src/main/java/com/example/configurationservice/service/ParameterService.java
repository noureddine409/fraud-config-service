package com.example.configurationservice.service;


import com.example.configurationservice.exception.ElementNotFoundException;
import com.example.configurationservice.model.Parameter;

import java.util.List;


public interface ParameterService extends GenericService<Parameter> {
    Parameter findByName(final String name) throws ElementNotFoundException;

    List<Parameter> findAll(String ruleCode, String name, String scope, int page, int size, String sortProperty, String sortDirection);
}
