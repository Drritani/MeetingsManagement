package com.Meetings.Meetings.mapper;

public interface BaseMapper<E,D>{
    E toEntity(D dto);
    D toDto(E entity);
}
