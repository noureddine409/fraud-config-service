package com.example.configurationservice.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ParameterDto extends BaseDto{

    private String name;

    private String type;


    private String scope;

    private RuleDto rule;

    private ParameterValueDto parameterValue;
    @Builder
    public ParameterDto(Long id, LocalDateTime createdAt, LocalDateTime updateAt, String name, String type, String scope, RuleDto rule, ParameterValueDto parameterValue) {
        super(id, createdAt, updateAt);
        this.name = name;
        this.type = type;
        this.scope = scope;
        this.rule = rule;
        this.parameterValue = parameterValue;
    }

}
