package ru.otus.homework11.rest.dto.converter;

public interface DtoConverter<E, D> {
    D mapToDto(E entity);
    E mapToEntity(D dto);
}
