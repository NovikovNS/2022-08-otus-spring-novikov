package ru.otus.homework7.dto.converter;

public interface DtoConverter<E, D> {
    D mapToDto(E entity);
    E mapToEntity(D dto);
}
