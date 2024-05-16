package pokedex.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "type")
public class Type {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String identifier;

    @OneToMany(mappedBy = "type")
    Set<Pokemon_types> types;

    // Getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public Set<Pokemon_types> getTypes() {
        return types;
    }

    public void setTypes(Set<Pokemon_types> types) {
        this.types = types;
    }
}