package com.example.configurationservice.repository;


import com.example.configurationservice.model.Parameter;

import java.util.Optional;

public interface ParameterRepository extends GenericRepository<Parameter> {

    Optional<Parameter> findByCode(String code);
}
