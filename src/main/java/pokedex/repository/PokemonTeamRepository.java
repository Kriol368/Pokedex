package pokedex.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pokedex.entity.PokemonTeam;

@Repository
public interface PokemonTeamRepository extends CrudRepository<PokemonTeam, Integer> {
}
