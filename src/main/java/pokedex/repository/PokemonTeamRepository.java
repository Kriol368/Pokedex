package pokedex.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pokedex.entity.PokemonTeam;
import pokedex.entity.Team;

import java.util.List;

@Repository
public interface PokemonTeamRepository extends CrudRepository<PokemonTeam, Integer> {
    List<PokemonTeam> findAllByTeam(Team team);
}
