package pokedex.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pokedex.entity.Trainer;

@Repository // Use @Repository instead of @Component
public interface TrainerRepository extends CrudRepository<Trainer, Integer> {
    Trainer findByName(String name);
}
