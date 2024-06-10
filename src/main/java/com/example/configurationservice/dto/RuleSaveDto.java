package com.example.configurationservice.dto;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RuleSaveDto {
    private String code;
    private String name;
    private String description;


}
