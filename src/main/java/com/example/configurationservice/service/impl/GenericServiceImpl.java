package com.example.configurationservice.service.impl;

import com.example.configurationservice.exception.*;
import com.example.configurationservice.mapper.MapHelper;
import com.example.configurationservice.model.BaseEntity;
import com.example.configurationservice.repository.GenericRepository;
import com.example.configurationservice.service.GenericService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import static com.example.configurationservice.common.CoreConstant.ExceptionMessage.*;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class GenericServiceImpl<T extends BaseEntity> implements GenericService<T> {
    private final GenericRepository<T> genericRepository;

    private final MapHelper mapHelper;

    @Override
    public T update(Long id, @NotNull T entity) throws ElementNotFoundException {
        T existingEntity = this.findById(id);
        entity.setCreatedAt(existingEntity.getCreatedAt());
        mapHelper.map(entity, existingEntity);
        existingEntity.setId(id);
        return saveMergedEntity(id, existingEntity);
    }

    @Override
    public <S> T partialUpdate(Long id, S partialUpdatePatch) throws ElementNotFoundException {
        T entity = this.findById(id);
        mapHelper.mapWithSkipNull(partialUpdatePatch, entity);
        return saveMergedEntity(id, entity);
    }

    @Override
    public T findById(Long id) throws ElementNotFoundException {
        return genericRepository.findById(id)
                .orElseThrow(() -> new ElementNotFoundException("no element could be found with ID {}", NOT_FOUND, new Object[]{id}));
    }


    @Override
    public T save(T entity) throws ElementAlreadyExistsException {
        try {
            return genericRepository.save(entity);
        } catch (final DataIntegrityViolationException e) {
            e.printStackTrace();
            log.warn("Error saving entity: Data integrity violation {}", entity);
            throw new DataIntegrityException(e, DATA_INTEGRITY_VIOLATION_SAVE, new Object[]{entity.toString()});
        } catch (final DataAccessException e) {
            log.error("Error saving entity: Data access error - {}", e.getMessage());
            throw new BusinessException(e, SAVE_ELEMENT, new Object[]{entity.toString()});
        }
    }


    @Override
    public boolean delete(Long id) throws ElementNotFoundException {
        try {
            genericRepository.deleteById(id);
            return true;
        } catch (final EmptyResultDataAccessException e) {
            log.warn("Error deleting element with ID {}: Element not found", id);
            throw new ElementNotFoundException(e, NOT_FOUND, new Object[]{id});
        } catch (final DataAccessException e) {
            log.error("Error deleting element with ID {}: Data access error - {}", id, e.getMessage());
            throw new BusinessException(e, DELETE_ELEMENT, new Object[]{id});
        }
    }


    @Override
    public List<T> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        try {
            return genericRepository.findAll(pageable).toList();
        } catch (final DataAccessException e) {
            log.error("Error finding elements: Data access error - {}", e.getMessage());
            throw new BusinessException(e, FIND_ELEMENTS, null);
        }
    }

    private @NotNull T saveMergedEntity(Long id, T entity) {
        try {
            return genericRepository.save(entity);
        } catch (final DataIntegrityViolationException e) {
            log.error("Error saving merged entity: Data integrity violation - {}", e.getMessage());
            throw new DataIntegrityException(e, DATA_INTEGRITY_VIOLATION_UPDATE, new Object[]{id});

        } catch (final OptimisticLockingFailureException e) {
            log.error("Error saving merged entity: Optimistic locking failure - {}", e.getMessage());
            throw new EntityModificationConflictException(e, OPTIMISTIC_LOCKING_FAILURE, new Object[]{id});
        }

    }
}
