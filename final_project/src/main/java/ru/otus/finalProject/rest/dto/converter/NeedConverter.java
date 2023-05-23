package ru.otus.finalProject.rest.dto.converter;

import org.springframework.stereotype.Service;
import ru.otus.finalProject.domain.Need;
import ru.otus.finalProject.rest.dto.wishlist.NeedDto;

@Service
public class NeedConverter implements DtoConverter<Need, NeedDto>{

    @Override
    public NeedDto mapToDto(Need entity) {
        return NeedDto.builder()
                .definition(entity.getDefinition())
                .build();
    }

    @Override
    public Need mapToEntity(NeedDto dto) {
        return Need.builder()
                .id(dto.getId())
                .definition(dto.getDefinition())
                .build();
    }
}
