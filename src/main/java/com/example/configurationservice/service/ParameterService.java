package com.example.configurationservice.service;


import com.example.configurationservice.model.Parameter;

import java.util.List;


public interface ParameterService extends GenericService<Parameter> {
    List<Parameter> search(String ruleCode, String parameterCode, int page, int size);
}
