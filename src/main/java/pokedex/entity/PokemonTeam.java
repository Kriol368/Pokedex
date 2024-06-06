package pokedex.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "pokemon_team")
public class PokemonTeam {

    @EmbeddedId
    private PokemonTeamKey id;

    @ManyToOne
    @JoinColumn(name = "pokemon_id", nullable = false)
    private Pokemon pokemon;

    @ManyToOne
    @MapsId("teamId")
    @JoinColumn(name = "team_id", insertable = false, updatable = false)
    private Team team;

    // No need to declare slot separately as it's part of the composite key
    // private int slot;

    // Constructors
    public PokemonTeam() {
        // Default constructor
    }

    public PokemonTeam(Pokemon pokemon, Team team, int slot) {
        this.pokemon = pokemon;
        this.team = team;
        this.id = new PokemonTeamKey(team.getId(), slot);
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
        if (this.id == null) {
            this.id = new PokemonTeamKey();
        }
        this.id.setTeamId(team.getId());
    }

    public int getSlot() {
        return id.getSlot();
    }

    public void setSlot(int slot) {
        if (this.id == null) {
            this.id = new PokemonTeamKey();
        }
        this.id.setSlot(slot);
    }
}
