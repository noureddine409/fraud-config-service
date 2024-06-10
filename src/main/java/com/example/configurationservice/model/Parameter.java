package com.example.configurationservice.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "parameter")
public class Parameter extends BaseEntity{
    private String name;

    @Column(nullable = false, unique = true)
    private String code;
    private String value;
    private String description;
    private String codeBank;
    private String countryCode;
    private String segment;

    @ManyToOne(fetch = FetchType.EAGER)
    private Rule rule;

}
