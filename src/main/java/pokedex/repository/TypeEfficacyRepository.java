package pokedex.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pokedex.entity.TypeEfficacy;

@Repository
public interface TypeEfficacyRepository extends CrudRepository<TypeEfficacy, Integer> {
}
