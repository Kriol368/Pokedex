package pokedex.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import pokedex.entity.Trainer;

@Component
public interface TrainerRepository extends CrudRepository<Trainer,Integer> {
}
