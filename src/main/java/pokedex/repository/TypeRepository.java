package pokedex.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import pokedex.entity.Type;

@Repository
public interface TypeRepository extends CrudRepository<Type,Integer> {
}

