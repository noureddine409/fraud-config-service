package com.example.configurationservice.repository;

import com.example.configurationservice.model.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenericRepository<T extends BaseEntity> extends JpaRepository<T, Long> {
}
