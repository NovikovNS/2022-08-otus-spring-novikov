package ru.otus.finalProject.rest.dto.converter;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.finalProject.domain.Availability;
import ru.otus.finalProject.rest.dto.wishlist.AvailabilityDto;

@Service
@AllArgsConstructor
public class AvailabilityConverter implements DtoConverter<Availability, AvailabilityDto>{
    @Override
    public AvailabilityDto mapToDto(Availability entity) {
        return AvailabilityDto.builder()
                .id(entity.getId())
                .definition(entity.getDefinition())
                .build();
    }

    @Override
    public Availability mapToEntity(AvailabilityDto dto) {
        return Availability.builder()
                .id(dto.getId())
                .definition(dto.getDefinition())
                .build();
    }
}
