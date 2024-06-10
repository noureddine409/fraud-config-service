package com.example.configurationservice.service;


import com.example.configurationservice.exception.ElementNotFoundException;
import com.example.configurationservice.model.Rule;

import java.util.List;

public interface RuleService extends GenericService<Rule> {
    Rule findByCode(String code) throws ElementNotFoundException;

    List<Rule> findAll(String code, int page, int size, String sortProperty, String sortDirection) ;
    }
