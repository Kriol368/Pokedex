package pokedex.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import pokedex.entity.Pokemon_types;

import java.util.List;

@Repository
public interface Pokemon_typesRepository extends CrudRepository<Pokemon_types,Integer> {
    List<Pokemon_types> findByPokemonId(int id);

}
