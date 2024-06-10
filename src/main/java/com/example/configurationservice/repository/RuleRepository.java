package com.example.configurationservice.repository;


import com.example.configurationservice.model.Rule;

import java.util.Optional;

public interface RuleRepository extends GenericRepository<Rule> {

    Optional<Rule> findByCode(String code);
}
