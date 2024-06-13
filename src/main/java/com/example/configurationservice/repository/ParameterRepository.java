package com.example.configurationservice.repository;


import com.example.configurationservice.model.Parameter;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ParameterRepository extends GenericRepository<Parameter> {
    List<Parameter> findByRule_CodeAndCodeContainingIgnoreCase(String RuleCode, String parameterCode, Pageable pageable);
}
