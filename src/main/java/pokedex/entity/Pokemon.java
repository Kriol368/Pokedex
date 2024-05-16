package pokedex.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Pokemon {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    private String identifier;
    private int speciesId;
    private int height;
    private int weight;
    private int order;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "pokemon_team",
            joinColumns = @JoinColumn(name = "pokemon_id"),
            inverseJoinColumns = @JoinColumn(name = "team_id"))
    private Set<Team> teams;

    @OneToMany(mappedBy = "pokemon")
    Set<Register> registers;

    @OneToMany(mappedBy = "pokemon")
    Set<Pokemon_types> types;

    // Getters and setters for each field here

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public int getSpeciesId() {
        return speciesId;
    }

    public void setSpeciesId(int speciesId) {
        this.speciesId = speciesId;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

}
