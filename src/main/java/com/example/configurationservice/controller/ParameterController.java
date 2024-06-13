package com.example.configurationservice.controller;


import com.example.configurationservice.dto.*;
import com.example.configurationservice.mapper.MapHelper;
import com.example.configurationservice.model.Parameter;
import com.example.configurationservice.model.Rule;
import com.example.configurationservice.service.ParameterService;
import com.example.configurationservice.service.RuleService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
