package ru.otus.homework6.dto.converter;

public interface DtoConverter<E, D> {
    D mapToDto(E entity);
    E mapToEntity(D dto);
}
