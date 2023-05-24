package ru.otus.finalProject.service;

import org.springframework.stereotype.Service;
import ru.otus.finalProject.dao.NeedRepository;
import ru.otus.finalProject.domain.Need;

@Service
public class NeedServiceImpl implements NeedService {
    NeedRepository needRepository;

    @Override
    public Need getNeedById(Long needId) {
        return needRepository.findById(needId).get();
    }
}
