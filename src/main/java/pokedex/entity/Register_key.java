package pokedex.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class Register_key implements Serializable {
    @Column(name = "pokemon_id")
    private Integer pokemonId;

    @Column(name = "trainer_id")
    private Integer trainerId;

    public Register_key() {
    }

    public Register_key(Integer pokemonId, Integer trainerId) {
        this.pokemonId = pokemonId;
        this.trainerId = trainerId;
    }

    // Getters and setters

    public Integer getPokemonId() {
        return pokemonId;
    }

    public void setPokemonId(Integer pokemonId) {
        this.pokemonId = pokemonId;
    }

    public Integer getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(Integer trainerId) {
        this.trainerId = trainerId;
    }
}
