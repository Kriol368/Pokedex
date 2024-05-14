package pokedex.entity;

import jakarta.persistence.Entity;
import org.springframework.data.annotation.Id;

import javax.siwng;

@Entity
public class PkmEnt {
    @Id
    private int id;
    private String identifier;
    private int speciesId;
    private int height;
    private int weight;
    private int order;

    // Getters and setters for each field here

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
