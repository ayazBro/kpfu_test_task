package ayaz.bro.restoran.repository;

import ayaz.bro.restoran.entity.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DishRepository extends JpaRepository<Dish,Integer> {
    Optional<Dish> findDishByName(String name);
}
