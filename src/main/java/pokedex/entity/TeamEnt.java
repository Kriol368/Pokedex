package pokedex.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "team")
public class TeamEnt {
    @Id
    private int id;
    private String name;

    @ManyToMany(mappedBy = "teams")
    private Set<PkmEnt> pokemons;


    // Getters and setters for each field here


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}