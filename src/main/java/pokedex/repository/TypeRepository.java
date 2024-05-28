package pokedex.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import pokedex.entity.Pokemon_types;
import pokedex.entity.Type;

import java.util.List;

@Repository
public interface TypeRepository extends CrudRepository<Type,Integer> {
}

