package pokedex.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import pokedex.entity.Pokemon;

@Component
public interface PokemonRepository extends CrudRepository<Pokemon,Integer> {
}
