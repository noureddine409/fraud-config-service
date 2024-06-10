package com.example.configurationservice.mapper;


import com.example.configurationservice.dto.BaseDto;
import com.example.configurationservice.model.BaseEntity;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class MapHelper {

    private final ModelMapper modelMapper;

    public <T extends BaseEntity, D extends BaseDto> D convertToDto(T source, Class<D> targetClass) {
        return modelMapper.map(source, targetClass);
    }

    public <T extends BaseEntity, D extends BaseDto> List<D> convertListToDto(final List<T> entities, Class<D> targetClass) {
        return entities.stream()
                .map(entity -> convertToDto(entity, targetClass))
                .toList();
    }


    public <S, D> void mapWithSkipNull(S sourceObject, D destinationObject) {
        // Save the original skipNullEnabled value
        boolean originalSkipNullEnabled = modelMapper.getConfiguration().isSkipNullEnabled();

        // Set skipNullEnabled to true for this mapping operation
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        modelMapper.map(sourceObject, destinationObject);

        // Set skipNullEnabled back to its original value
        modelMapper.getConfiguration().setSkipNullEnabled(originalSkipNullEnabled);
    }


    public <S, D> void map(S sourceObject, D destinationObject) {
        modelMapper.map(sourceObject, destinationObject);
    }


    public <S, D> D map(S sourceObject, Class<D> targetClass) {
        return modelMapper.map(sourceObject, targetClass);
    }

}
