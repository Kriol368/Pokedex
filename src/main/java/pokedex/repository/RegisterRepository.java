package pokedex.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pokedex.entity.Register;

@Repository // Use @Repository instead of @Component
public interface RegisterRepository extends CrudRepository<Register, Integer> {
}
