package com.example.demo.common.map;

import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

public interface EntityMapStruct<D, E> {
    E toEntity(D dto);

    D toDto(E entity);

    //DTO에 있는값을 기준으로 Entity에 덮어씌우기
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDto(D dto, @MappingTarget E entity);
}
