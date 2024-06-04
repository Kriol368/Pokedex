package pokedex.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pokedex.entity.Team;
import pokedex.entity.Trainer;

@Repository
public interface TeamRepository extends CrudRepository<Team,Integer> {
    Team findByTrainer(Trainer trainer);
}
