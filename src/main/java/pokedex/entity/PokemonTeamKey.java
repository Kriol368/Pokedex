package pokedex.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PokemonTeamKey implements Serializable {

    @Column(name = "team_id")
    private int teamId;

    @Column(name = "slot")
    private int slot;

    // Default constructor
    public PokemonTeamKey() {}

    // Parameterized constructor
    public PokemonTeamKey(int teamId, int slot) {
        this.teamId = teamId;
        this.slot = slot;
    }

    // Getters and Setters
    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    // Override equals and hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PokemonTeamKey that = (PokemonTeamKey) o;
        return teamId == that.teamId && slot == that.slot;
    }

    @Override
    public int hashCode() {
        return Objects.hash(teamId, slot);
    }
}
