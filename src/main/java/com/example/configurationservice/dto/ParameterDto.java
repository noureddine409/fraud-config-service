package com.example.configurationservice.dto;

import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class ParameterDto extends BaseDto{

    private String name;

    private String code;
    private String value;
    private String description;
    private String codeBank;
    private String countryCode;
    private String segment;
    private RuleDto rule;

}
