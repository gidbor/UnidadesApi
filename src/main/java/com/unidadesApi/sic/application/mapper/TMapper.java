package com.unidadesApi.sic.application.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TMapper<TEntity, TDto> {

    @Autowired
    private ModelMapper modelMapper;

    private Class<TEntity> entityClass;
    private Class<TDto> dtoClass;

    public void setClassTypes(Class<TEntity> entityClass, Class<TDto> dtoClass) {
        this.entityClass = entityClass;
        this.dtoClass = dtoClass;
    }

    // Entity -> DTO
    public TDto toDto(TEntity entity) {
        if (entity == null) {
            return null;
        }
        return modelMapper.map(entity, dtoClass);
    }

    // DTO -> Entity
    public TEntity toEntity(TDto dto) {
        if (dto == null) {
            return null;
        }
        return modelMapper.map(dto, entityClass);
    }

    // Lista de Entity -> lista de DTO
    public List<TDto> toDtoList(List<TEntity> entityList) {
        return entityList.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    // Lista de DTO -> lista de Entity
    public List<TEntity> toEntityList(List<TDto> dtoList) {
        return dtoList.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

}
