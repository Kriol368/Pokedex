package pokedex.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class PokemonTeamKey implements Serializable {

    @Column(name = "pokemon_id")
    private int pokemonId;
    @Column(name = "team_id")
    private int teamId;

    // Constructors, getters, setters, equals, and hashCode methods
    // ...
}
