package com.example.configurationservice.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "rule")
public class Rule extends BaseEntity{
    @Column(nullable = false, unique = true)
    private String code;
    private String name;
    private String description;
    @OneToMany(mappedBy = "rule", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Parameter> parameters;

}
