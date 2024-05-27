package pokedex.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;
import pokedex.entity.Trainer;

@Repository
public interface TrainerRepository extends CrudRepository<Trainer, Integer> {
    Trainer findByName(String name);

    @Query("SELECT COUNT(r) FROM Register r WHERE r.trainer.id = :trainerId AND r.registered = 1")
    long countRegisteredPokemons(@Param("trainerId") int trainerId);

    @Query("SELECT COUNT(r) FROM Register r WHERE r.trainer.id = :trainerId")
    long countTotalPokemons(@Param("trainerId") int trainerId);
}
