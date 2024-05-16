package pokedex.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import pokedex.entity.Team;

@Component
public interface TeamRepository extends CrudRepository<Team,Long> {
}
