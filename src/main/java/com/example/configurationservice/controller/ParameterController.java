package com.example.configurationservice.controller;


import com.example.configurationservice.dto.ParameterDto;
import com.example.configurationservice.dto.ParameterPatchDto;
import com.example.configurationservice.dto.ParameterQuery;
import com.example.configurationservice.dto.ParameterSaveDto;
import com.example.configurationservice.mapper.MapHelper;
import com.example.configurationservice.model.Parameter;
import com.example.configurationservice.model.Rule;
import com.example.configurationservice.service.ParameterService;
import com.example.configurationservice.service.RuleService;
import com.example.configurationservice.validation.ValidationUtils;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/parameters")
public class ParameterController {
    private final ParameterService parameterService;
    private final RuleService ruleService;
    private final MapHelper mapHelper;

    public ParameterController(ParameterService parameterService, RuleService ruleService, MapHelper mapHelper) {
        this.parameterService = parameterService;
        this.ruleService = ruleService;
        this.mapHelper = mapHelper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParameterDto> getById(@PathVariable("id") Long id) {
        final Parameter parameter = parameterService.findById(id);
        final ParameterDto parameterDto = mapHelper.convertToDto(parameter, ParameterDto.class);
        return ResponseEntity.ok(parameterDto);
    }

    @GetMapping("/search")
    public ResponseEntity<List<ParameterDto>> search(@ModelAttribute ParameterQuery parameterQuery) {
        ValidationUtils.validateQuery(parameterQuery);
        final int page = parameterQuery.getPage();
        final int size = parameterQuery.getSize();
        final String code = parameterQuery.getCode();
        final String ruleCode = parameterQuery.getRuleCode();

        List<Parameter> parameters = parameterService.search(ruleCode, code, page, size);
        return new ResponseEntity<>(mapHelper.convertListToDto(parameters, ParameterDto.class), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ParameterDto> save(@RequestBody @Valid ParameterSaveDto parameterDto) {
        final String ruleCode = parameterDto.getRuleCode();
        Rule rule = ruleService.findByCode(ruleCode);
        final Parameter parameterEntity = mapHelper.map(parameterDto, Parameter.class);
        parameterEntity.setRule(rule);
        final Parameter savedParameter = parameterService.save(parameterEntity);
        final ParameterDto parameterResponse = mapHelper.convertToDto(savedParameter, ParameterDto.class);
        return new ResponseEntity<>(parameterResponse, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ParameterDto> update(@PathVariable("id") Long id, @RequestBody @Valid ParameterPatchDto patch) {
        final Parameter updatedParameter = parameterService.partialUpdate(id, patch);
        final ParameterDto parameterResponse = mapHelper.convertToDto(updatedParameter, ParameterDto.class);
        return new ResponseEntity<>(parameterResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
        return new ResponseEntity<>(parameterService.delete(id), HttpStatus.NO_CONTENT);
    }
}
