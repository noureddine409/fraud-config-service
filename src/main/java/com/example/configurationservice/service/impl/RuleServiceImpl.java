package com.example.configurationservice.service.impl;

import com.example.configurationservice.exception.ElementNotFoundException;
import com.example.configurationservice.mapper.MapHelper;
import com.example.configurationservice.model.Rule;
import com.example.configurationservice.repository.GenericRepository;
import com.example.configurationservice.repository.RuleRepository;
import com.example.configurationservice.service.RuleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.configurationservice.common.CoreConstant.ExceptionMessage.NOT_FOUND;

@Service
@Slf4j
public class RuleServiceImpl extends GenericServiceImpl<Rule> implements RuleService {

    private final RuleRepository ruleRepository;

    public RuleServiceImpl(GenericRepository<Rule> genericRepository, MapHelper mapHelper, RuleRepository ruleRepository) {
        super(genericRepository, mapHelper);
        this.ruleRepository = ruleRepository;
    }


    @Override
    public List<Rule> search(String name, String code, int page, int size) {
        final Pageable pageable = PageRequest.of(page, size);
        return ruleRepository.findByCodeContainingIgnoreCaseAndNameContainingIgnoreCase(code, name, pageable);
    }

    @Override
    public Rule findByCode(String code) {
        return ruleRepository.findByCode(code)
                .orElseThrow(() -> new ElementNotFoundException("no rule could be found with code {}", NOT_FOUND, new Object[]{code}));
    }
}
