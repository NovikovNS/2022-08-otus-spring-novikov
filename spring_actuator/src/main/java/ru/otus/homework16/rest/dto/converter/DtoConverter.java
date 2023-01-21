package ru.otus.homework16.rest.dto.converter;

public interface DtoConverter<E, D> {
    D mapToDto(E entity);
    E mapToEntity(D dto);
}
