package pokedex.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import pokedex.entity.Pokemon_types_key;

@Component
public interface Pokemon_types_keyRepository extends CrudRepository<Pokemon_types_key,Integer> {
}
