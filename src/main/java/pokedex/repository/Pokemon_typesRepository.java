package pokedex.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import pokedex.entity.Pokemon_types;

@Component
public interface Pokemon_typesRepository extends CrudRepository<Pokemon_types,Integer> {
}
