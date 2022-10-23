package ru.otus.homework6.dto.converter;

public interface DtoConverter<E, D> {
    D toDto(E entity);
    E fromDto(D dto);
}
