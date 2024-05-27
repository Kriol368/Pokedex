package pokedex.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import pokedex.entity.Pokemon_types;

@Repository
public interface Pokemon_typesRepository extends CrudRepository<Pokemon_types,Integer> {
}
