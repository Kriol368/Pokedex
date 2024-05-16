package pokedex.entity;

import jakarta.persistence.*;

@Entity
public class register {

    @EmbeddedId
    register_key id;

    @ManyToOne
    @MapsId("pokemonid")
    @JoinColumn(name = "pokemon_id")
    Pokemon pokemon;

    @ManyToOne
    @MapsId("trainerid")
    @JoinColumn(name = "trainer_id")
    Trainer trainer;

    int registered;

    // standard constructors, getters, and setters


    public register_key getId() {
        return id;
    }

    public void setId(register_key id) {
        this.id = id;
    }

    public Pokemon getPokemon() {
        return pokemon;
    }

    public void setPokemon(Pokemon pokemon) {
        this.pokemon = pokemon;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

    public int getRegistered() {
        return registered;
    }

    public void setRegistered(int registered) {
        this.registered = registered;
    }
}