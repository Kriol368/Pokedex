package pokedex.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import pokedex.entity.Team;

@Repository
public interface TeamRepository extends CrudRepository<Team,Integer> {
}
