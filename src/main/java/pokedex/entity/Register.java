package pokedex.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "register")
public class Register {

    @EmbeddedId
    private Register_key id;

    @ManyToOne
    @MapsId("pokemonId") // Specify the correct field name in Register_key
    @JoinColumn(name = "pokemon_id") // Name of the foreign key column in the Register table
    private Pokemon pokemon;

    @ManyToOne
    @MapsId("trainerId") // Specify the correct field name in Register_key
    @JoinColumn(name = "trainer_id") // Name of the foreign key column in the Register table
    private Trainer trainer;

    private int registered;

    public Register() {
    }

    public Register(Pokemon pokemon, Trainer trainer, int registered) {
        this.pokemon = pokemon;
        this.trainer = trainer;
        this.registered = registered;
        this.id = new Register_key(pokemon.getId(), trainer.getId());
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