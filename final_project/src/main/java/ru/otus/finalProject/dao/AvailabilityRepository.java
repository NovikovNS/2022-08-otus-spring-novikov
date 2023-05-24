package ru.otus.finalProject.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.finalProject.domain.Availability;

public interface AvailabilityRepository extends JpaRepository<Availability, Long> {
}
