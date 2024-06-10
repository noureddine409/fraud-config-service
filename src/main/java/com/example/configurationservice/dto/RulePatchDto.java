package com.example.configurationservice.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RulePatchDto {
    private String code;
    private String name;
    private String description;

}
