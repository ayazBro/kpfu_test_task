package ayaz.bro.restoran.repository;

import ayaz.bro.restoran.entity.Cook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CookRepository extends JpaRepository<Cook,Integer> {
    Optional<Cook> findCookByName(String name);
}
