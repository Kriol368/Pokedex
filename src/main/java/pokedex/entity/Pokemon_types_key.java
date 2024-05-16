package pokedex.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class Pokemon_types_key implements Serializable {
    @Column(name = "pokemon_id")
    Integer pokemon_id;

    @Column(name = "type_id")
    Integer type_id;
}
