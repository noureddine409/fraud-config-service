package com.example.configurationservice.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ParameterPatchDto {
    @Size(min = 3, max = 50, message = "Code must be between 3 and 50 characters")
    @Pattern(regexp = "^[A-Z0-9_]+$", message = "Code must be uppercase alphanumeric with underscores")
    private String code;
    private String value;
    @Size(min = 10, max = 500, message = "Description must be between 10 and 500 characters")
    private String description;
}
