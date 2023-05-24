package ru.otus.finalProject.service;

import org.springframework.stereotype.Service;
import ru.otus.finalProject.dao.AvailabilityRepository;
import ru.otus.finalProject.domain.Availability;

@Service
public class AvailabilityServiceImpl implements AvailabilityService {
    AvailabilityRepository availabilityRepository;

    @Override
    public Availability getAvailabilityById(Long availabilityId) {
        return availabilityRepository.findById(availabilityId).get();
    }
}
