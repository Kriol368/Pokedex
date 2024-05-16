package pokedex.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import pokedex.entity.Register;

@Component
public interface RegisterRepository extends CrudRepository<Register,Integer> {
}
