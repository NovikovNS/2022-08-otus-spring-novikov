package ru.otus.homework14.domain.mappers;

public interface JpaMapper<E, D> {
    D mapToSql(E entity);
}
