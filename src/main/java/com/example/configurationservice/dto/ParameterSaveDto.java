package com.example.configurationservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ParameterSaveDto {
    @NotBlank(message = "Code cannot be blank")
    @Size(min = 3, max = 50, message = "Code must be between 3 and 50 characters")
    @Pattern(regexp = "^[A-Z0-9_]+$", message = "Code must be uppercase alphanumeric with underscores")
    private String code;
    @NotBlank(message = "Value cannot be blank")
    private String value;
    @NotBlank(message = "Description cannot be blank")
    @Size(min = 10, max = 500, message = "Description must be between 10 and 500 characters")
    private String description;
    @NotBlank(message = "RuleCode cannot be blank")
    @Size(min = 3, max = 50, message = "RuleCode must be between 10 and 500 characters")
    @Pattern(regexp = "^[A-Z0-9_-]+$", message = "RuleCode must be uppercase alphanumeric with underscores or dashes")
    private String ruleCode;
    private String codeBank;
    private String segment;




}
