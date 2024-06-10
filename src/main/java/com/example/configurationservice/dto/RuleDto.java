package com.example.configurationservice.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RuleDto extends BaseDto{
    private String code;
    private String description;
    private List<ParameterDto> parameters;
    @Builder
    public RuleDto(Long id, LocalDateTime createdAt, LocalDateTime updateAt, String code, String description, List<ParameterDto> parameters) {
        super(id, createdAt, updateAt);
        this.code = code;
        this.description = description;
        this.parameters = parameters;
    }

}
