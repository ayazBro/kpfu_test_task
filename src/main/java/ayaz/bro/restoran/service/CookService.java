package ayaz.bro.restoran.service;

import ayaz.bro.restoran.entity.Cook;
import ayaz.bro.restoran.exception.DataNotFoundException;
import ayaz.bro.restoran.repository.CookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CookService {
    private final CookRepository cookRepository;

    @Autowired
    public CookService(CookRepository cookRepository) {
        this.cookRepository = cookRepository;
    }

    public Cook findById(int id) {
        return cookRepository.findById(id)
                .orElseThrow(DataNotFoundException::new);
    }

    public Cook findByName(String name) {
        return cookRepository.findCookByName(name)
                .orElseThrow(DataNotFoundException::new);
    }
    public List<Cook> findAll(String category) {
        return cookRepository.findAll(Sort.by(category));
    }
}