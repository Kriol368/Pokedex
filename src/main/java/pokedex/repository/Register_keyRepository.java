package pokedex.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import pokedex.entity.Register_key;

@Component
public interface Register_keyRepository extends CrudRepository<Register_key,Integer> {
}
