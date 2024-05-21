package pokedex.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pokedex.entity.Map;

@Repository
public interface MapRepository extends CrudRepository<Map, Integer> {
    // You can define custom query methods here if needed
}
