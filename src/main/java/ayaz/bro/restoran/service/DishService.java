package ayaz.bro.restoran.service;

import ayaz.bro.restoran.entity.Dish;
import ayaz.bro.restoran.exception.DataNotFoundException;
import ayaz.bro.restoran.repository.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishService {
    private final DishRepository dishRepository;

    @Autowired
    public DishService(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    public Dish findById(int id) {
        return dishRepository.findById(id)
                .orElseThrow(DataNotFoundException::new);
    }

    public Dish findByName(String name) {
        return dishRepository.findDishByName(name)
                .orElseThrow(DataNotFoundException::new);
    }
    public List<Dish> findAll(String category) {
        return dishRepository.findAll(Sort.by(category));
    }
}
