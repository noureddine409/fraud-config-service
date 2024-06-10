package com.example.configurationservice.repository;


import com.example.configurationservice.model.Rule;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface RuleRepository extends GenericRepository<Rule> {

    Optional<Rule> findByCode(String code);
    List<Rule> findByCodeContainingIgnoreCaseAndNameContainingIgnoreCase(String code, String name, Pageable pageable);

}
