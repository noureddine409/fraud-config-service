package com.example.configurationservice.service;


import com.example.configurationservice.model.Rule;

import java.util.List;

public interface RuleService extends GenericService<Rule> {

    List<Rule> search(String name, String code, int page, int size);

    Rule findByCode(String code);
}
