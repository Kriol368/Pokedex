package pokedex.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "register")
public class Register {

    @EmbeddedId
    Register_key id;

    @ManyToOne
    @MapsId("pokemon_id")
    @JoinColumn(name = "pokemon_id")
    Pokemon pokemon;

    @ManyToOne
    @MapsId("trainer_id")
    @JoinColumn(name = "trainer_id")
    Trainer trainer;

    int registered;

    public Register() {
    }

    public Register(Pokemon pokemon, Trainer trainer, int registered) {
        this.pokemon = pokemon;
        this.trainer = trainer;
        this.registered = registered;
    }
// standard constructors, getters, and setters


    public Register_key getId() {
        return id;
    }

    public void setId(Register_key id) {
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