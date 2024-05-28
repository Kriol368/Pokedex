package pokedex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pokedex.entity.Register;

import java.util.List;

@Repository
public interface RegisterRepository extends JpaRepository<Register, Integer> {
    List<Register> findByTrainerId(int trainerId);
    Register findByTrainerIdAndPokemonId(int trainerId, int pokemonId);
}
