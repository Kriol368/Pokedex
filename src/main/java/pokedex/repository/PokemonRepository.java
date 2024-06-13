package pokedex.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pokedex.entity.Pokemon;

import java.util.List;

@Repository // Use @Repository instead of @Component
public interface PokemonRepository extends CrudRepository<Pokemon, Integer> {
    List<Pokemon> findAllByOrderByOrderAsc();
    Pokemon findByIdentifier(String identifier);
}
