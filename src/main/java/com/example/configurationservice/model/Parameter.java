package com.example.configurationservice.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "parameter")
public class Parameter extends BaseEntity{
    @Column(nullable = false, unique = true)
    private String name;

    @Enumerated(EnumType.STRING)
    private String type;

    @Enumerated(EnumType.STRING)
    private String scope;

    @ManyToOne(fetch = FetchType.EAGER)
    private Rule rule;

    @Embedded
    private String parameterValue;


}
