package pokedex.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import pokedex.entity.Type;

@Component
public interface TypeRepository extends CrudRepository<Type,Long> {
}

