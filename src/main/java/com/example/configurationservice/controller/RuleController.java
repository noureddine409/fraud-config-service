package com.example.configurationservice.controller;


import com.example.configurationservice.dto.RuleDto;
import com.example.configurationservice.dto.RulePatchDto;
import com.example.configurationservice.dto.RuleQuery;
import com.example.configurationservice.dto.RuleSaveDto;
import com.example.configurationservice.mapper.MapHelper;
import com.example.configurationservice.model.Rule;
import com.example.configurationservice.service.RuleService;
import com.example.configurationservice.validation.ValidationUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rules")
@RequiredArgsConstructor
public class RuleController {

    private final RuleService ruleService;
    private final MapHelper mapHelper;

    @GetMapping("/search")
    public ResponseEntity<List<RuleDto>> search(@ModelAttribute RuleQuery ruleQuery) {
        ValidationUtils.validateQuery(ruleQuery);
        final int page = ruleQuery.getPage();
        final int size = ruleQuery.getSize();
        final String code = ruleQuery.getCode();
        final String name = ruleQuery.getName();

        List<Rule> rules = ruleService.search(name, code, page, size);

        return new ResponseEntity<>(mapHelper.convertListToDto(rules, RuleDto.class), HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<RuleDto> getById(@PathVariable("id") Long id) {
        final Rule rule = ruleService.findById(id);
        final RuleDto ruleDto = mapHelper.convertToDto(rule, RuleDto.class);
        return ResponseEntity.ok(ruleDto);
    }

    @PostMapping
    public ResponseEntity<RuleDto> save(@RequestBody RuleSaveDto ruleDto) {
        final Rule ruleEntity = mapHelper.map(ruleDto, Rule.class);
        final Rule savedRule = ruleService.save(ruleEntity);
        final RuleDto ruleResponse = mapHelper.convertToDto(savedRule, RuleDto.class);
        return new ResponseEntity<>(ruleResponse, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<RuleDto> getById(@PathVariable("id") Long id, RulePatchDto patch) {
        final Rule updatedRule = ruleService.partialUpdate(id, patch);
        final RuleDto ruleResponse = mapHelper.convertToDto(updatedRule, RuleDto.class);
        return new ResponseEntity<>(ruleResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
        return new ResponseEntity<>(ruleService.delete(id), HttpStatus.NO_CONTENT);
    }
}
