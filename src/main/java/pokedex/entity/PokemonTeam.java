package pokedex.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "pokemon_team")
public class PokemonTeam {

    @EmbeddedId
    private PokemonTeamKey id;

    @ManyToOne
    @MapsId("pokemonId")
    @JoinColumn(name = "pokemon_id")
    Pokemon pokemon;

    @ManyToOne
    @MapsId("teamId")
    @JoinColumn(name = "team_id")
    Team team;

    // Constructors
    public PokemonTeam() {
        // Default constructor
    }

    public PokemonTeamKey getId() {
        return id;
    }

    public void setId(PokemonTeamKey id) {
        this.id = id;
    }

    public Pokemon getPokemon() {
        return pokemon;
    }

    public void setPokemon(Pokemon pokemon) {
        this.pokemon = pokemon;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
